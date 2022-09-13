/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal;
import py.com.ping.administracionBase.jpa.BswUsuarios;

/**
 *
 * @author Administrador
 */
@Stateless
public class BswUsuariosEJB {

    @PersistenceContext
    private EntityManager entidadManager;
    @Inject
    LoginControlador loginControlador;

    String queryString;
    Query query;

    public void insertar(BswUsuarios entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswUsuarios entidadNueva) {
        entidadManager.merge(entidadNueva);
    }

    public void actualizarPersona(BswUsuarios entidadNueva) {
        entidadManager.merge(entidadNueva.getBswPersonas());
        entidadManager.merge(entidadNueva);
    }

    public void eliminar(BswUsuarios entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

    public List<BswUsuarios> listarTodos() {
        return entidadManager.createNamedQuery("BswUsuarios.findAll").getResultList();
    }

    public List<BswUsuarios> listarTodosPorEmpresa() {
        return entidadManager.createNamedQuery("BswUsuarios.findAllByEmpresa").setParameter("empresa", loginControlador.getEmpresaSeleccionada()).getResultList();
    }

    public String getCi(Long usuarioId, String codTipoDoc) {
        queryString = "SELECT numero "
                + "  FROM bsw_personas p, bsw_identificaciones i, bsw_ident_personas ip, bsw_usuarios u "
                + "  where u.id_persona = p.id "
                + "  and p.id = ip.id_persona "
                + "  and ip.id_identificaciones = i.id "
                + "  and i.cod_ident = ?  "
                + "  and u.id = ? ";
        query = entidadManager.createNativeQuery(queryString);
        query.setParameter(1, codTipoDoc);
        query.setParameter(2, usuarioId);
        try {
            return (String) query.getSingleResult();
        } catch (Exception e) {
            return "";
        }

    }

    public BswRolUsuarioSucursal getRolUsuarioSucursal(Long usuarioId, Long sucursalId) {
        queryString = "SELECT rus.* "
                + "  FROM bsw_rol_usuario_sucursal rus "
                + "  where rus.id_usuario = ? "
                + "  and rus.id_sucursal = ? ";
        query = entidadManager.createNativeQuery(queryString, BswRolUsuarioSucursal.class);
        query.setParameter(1, usuarioId);
        query.setParameter(2, sucursalId);
        try {
            return (BswRolUsuarioSucursal) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public void actualizarRolUsuarioSucursal(BswRolUsuarioSucursal rolUsuarioSucursal) {
        entidadManager.merge(rolUsuarioSucursal);
    }

    public BswUsuarios getUserById(Integer id) {
        queryString = "SELECT u.* "
                + "  FROM bsw_usuarios u "
                + "  where u.id =? ";
        query = entidadManager.createNativeQuery(queryString, BswUsuarios.class);
        query.setParameter(1, id);
        try {
            return (BswUsuarios)query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
       public BswUsuarios getUsuarioByCodigo(String codigo) {
        return (BswUsuarios) entidadManager.createNamedQuery("BswUsuarios.findByCodUsuario")
                .setParameter("codUsuario", codigo)
                .getSingleResult();
    }
}
