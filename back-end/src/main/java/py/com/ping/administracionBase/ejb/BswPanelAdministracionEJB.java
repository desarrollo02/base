/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.utilitarios.interfaces.GenericQuery;

/**
 *
 * @author Administrador
 */
@Stateless
public class BswPanelAdministracionEJB {
    private static final Logger log =
            LoggerFactory.getLogger(BswPanelAdministracionEJB.class);

    @PersistenceContext
    private EntityManager entidadManager;
    protected FacesMessage facesMsg;
    @EJB
    private GenericQuery genericQuery;

    public void insertar(BswFormas entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswFormas entidadNueva) {
        entidadManager.merge(entidadNueva);
    }

    public void eliminar(BswFormas entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

    public List<BswFormas> listarTodos() {
        return entidadManager.createNamedQuery("BswFormas.findAll").getResultList();
    }

    public BswFormas getForma(String nombreForma) {
        log.debug("Query nombreForma= " + nombreForma);
        String sql = "Select f from BswFormas f where f.nomForma = :nomF "
                + " ";
        Query q = entidadManager.createQuery(sql, BswFormas.class);

        q.setParameter("nomF", nombreForma);
        log.debug("Query q= " + q);
        try {
            return (BswFormas) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public void insertar(BswGruposUsuarios entidad, BswEmpresas empresaD) {
        if (existeGrupo(entidad.getCodGrupo(), empresaD.getId())) {
            try {
                entidadManager.persist(new BswGruposUsuarios(entidad, empresaD));
                mensajeExito("Los datos fueron guardados correctamente!");
            } catch (Exception ex) {
                mensajeError("Error al intentar guardar el registro.");
                log.error("Error al intentar modificar registro.", ex);
            }
        }
    }

    public BswGruposUsuarios getGrupo(BswGruposUsuarios entidad, BswEmpresas empresaD) {
        HashMap camposQuery = new HashMap();
        camposQuery.put("codGrupo", entidad.getCodGrupo());
        camposQuery.put("bswEmpresas.id", empresaD.getId());
        genericQuery.setEm(entidadManager);
        return (BswGruposUsuarios) genericQuery.getSearchSingleResult(camposQuery, BswGruposUsuarios.class, null);
    }

    public boolean existeGrupo(String Codigo, Long emp) {
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        BswGruposUsuarios aux;
        try {
            aux = (BswGruposUsuarios) entidadManager.createNativeQuery("select * from BSW_GRUPOS_USUARIOS where COD_GRUPO='" + Codigo + "' and ID_EMPRESA = " + emp, BswGruposUsuarios.class).getSingleResult();

        } catch (NoResultException e) {
            return true;

        }
        return false;

    }

    public void insertarAccesoGrupo(BswAccesosGrupos entidad, BswEmpresas empresaD, BswEmpresas empresaO) {
        BswModulos bswModulos = getModulo(entidad, empresaD);
        BswGruposUsuarios gruposUsuario = getGrupo(entidad.getBswGruposUsuarios(), empresaD);
        if (bswModulos != null && bswModulos.getId() != null && gruposUsuario != null && gruposUsuario.getId() != null) {
            if (existeAccesoGrupo(bswModulos.getId(), entidad.getBswFormas().getId(), gruposUsuario.getId(), empresaD.getId())) {
                try {
                    entidadManager.persist(new BswAccesosGrupos(entidad, empresaD, bswModulos, gruposUsuario));
                    mensajeExito("Los datos fueron guardados correctamente!");

                } catch (Exception ex) {
                    mensajeError("Error al intentar guardar el registro.");
                    log.error("Error al intentar modificar registro.", ex);
                }
            }
        } else {
            mensajeError("Empresa destino debe tener al menos un Modulo o Grupos Usuario");
        }
    }

    public BswModulos getModulo(BswAccesosGrupos entidad, BswEmpresas empresaD) {
        HashMap camposQuery = new HashMap();
        camposQuery.put("codModulo", entidad.getBswModulos().getCodModulo());
        camposQuery.put("bswEmpresas.id", empresaD.getId());
        genericQuery.setEm(entidadManager);
        return (BswModulos) genericQuery.getSearchSingleResult(camposQuery, BswModulos.class, null);
    }

    public boolean existeAccesoGrupo(Long mod, Long forma, Long grupo, Long emp) {
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        BswAccesosGrupos aux;
        try {
            aux = (BswAccesosGrupos) entidadManager.createNativeQuery("select * from BSW_ACCESOS_GRUPOS where id_modulo=? and id_forma= ? and id_grupo= ? and ID_EMPRESA = ?", BswAccesosGrupos.class).
                    setParameter(1, mod)
                    .setParameter(2, forma)
                    .setParameter(3, grupo)
                    .setParameter(4, emp)
                    .getSingleResult();

        } catch (NoResultException e) {
            return true;

        }
        return false;

    }

    public void insertarBswModulos(BswModulos entidad, BswEmpresas empresaD) {
        if (existeBswModulos(entidad.getCodModulo(), empresaD.getId())) {
            try {
                entidadManager.persist(new BswModulos(entidad, empresaD));
                mensajeExito("Los datos fueron guardados correctamente!");
            } catch (Exception ex) {
                mensajeError("Error al intentar guardar el registro.");
                log.error("Error al intentar modificar registro.", ex);
            }
        }
    }
    
    public boolean existeBswModulos(String Codigo, Long emp) {
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        BswModulos aux;
        try {
            aux = (BswModulos) entidadManager.createNativeQuery("select * from BSW_MODULOS where COD_MODULO='" + Codigo + "' and ID_EMPRESA = " + emp, BswModulos.class).getSingleResult();

        } catch (NoResultException e) {
            return true;

        }
        return false;

    }

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    
    

}
