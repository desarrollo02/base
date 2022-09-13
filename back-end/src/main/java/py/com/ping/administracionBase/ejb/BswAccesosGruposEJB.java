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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.jpa.BswPermisosOpciones;
import py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal;
import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.jpa.BswUsuarios;

/**
 *
 * @author Administrador
 */
@Stateless
public class BswAccesosGruposEJB {
    private static final Logger log =
            LoggerFactory.getLogger(BswAccesosGruposEJB.class);

    @PersistenceContext
    private EntityManager entidadManager;

    BswUsuarios userLogueado; //@Inject @Logeado

    BswEmpresas empresaLogueada;
    @Inject
    private BswAccesosGrupos ag;
    private @Inject
    LoginControlador loginControlador;

    public void insertar(BswAccesosGrupos entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswAccesosGrupos entidadNueva) {
        entidadManager.merge(entidadNueva);
    }

    public void eliminar(BswAccesosGrupos entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

    public List<BswAccesosGrupos> listarTodos() {
        return entidadManager.createNamedQuery("BswAccesosGrupos.findAll").getResultList();
    }

    public boolean puedeInsertar(String nom_Forma) {
        userLogueado = loginControlador.getUsuario();
        BswUsuarios usuario = userLogueado;
        BswAccesosGrupos ag;
        List<BswAccesosGrupos> lag;
        lag = entidadManager.createNamedQuery("BswAccesosGrupos.findByNomFormaAndCodGrupo").setParameter("nomForma", nom_Forma).setParameter("codGrupo", usuario.getBswGruposUsuarios().getCodGrupo()).setParameter("idEmpresa", getEmpresaLogueada().getId()).getResultList();
        if (!lag.isEmpty()) {
            ag = lag.get(0);
            entidadManager.refresh(ag);
            if (ag.getPuedeInsertar().equals("S") && usuario.getEstado().equals("A")) {
                return true;
            }
        }
        return false;
    }

    public boolean puedeBorrar(String nom_Forma) { 

        userLogueado = loginControlador.getUsuario();
        List<BswAccesosGrupos> lag;
        lag = entidadManager.createNamedQuery("BswAccesosGrupos.findByNomFormaAndCodGrupo").setParameter("nomForma", nom_Forma).setParameter("codGrupo", userLogueado.getBswGruposUsuarios().getCodGrupo()).setParameter("idEmpresa", getEmpresaLogueada().getId()).getResultList();
        if (!lag.isEmpty()) {
            ag = lag.get(0);
            entidadManager.refresh(ag);
            if (ag.getPuedeBorrar().equals("S") && userLogueado.getEstado().equals("A")) {
                return true;
            }
        }
        return false;
    }

    public boolean puedeConsultar(String nom_Forma) {
        BswAccesosGrupos ag;
        userLogueado = loginControlador.getUsuario();
        List<BswAccesosGrupos> lag;
        lag = entidadManager.createNamedQuery("BswAccesosGrupos.findByNomFormaAndCodGrupo", BswAccesosGrupos.class)
                .setParameter("nomForma", nom_Forma).setParameter("codGrupo", userLogueado.getBswGruposUsuarios().getCodGrupo())
                .setParameter("idEmpresa", getEmpresaLogueada().getId()).getResultList();
        if (!lag.isEmpty()) {
            ag = lag.get(0);
            entidadManager.refresh(ag);
            if (ag.getPuedeConsultar().equals("S") && userLogueado.getEstado().equals("A")) {
                return true;
            }
        }
        return false;
    }

    public BswRolUsuarioSucursal obtieneUsuarioRol(BswUsuarios user, BswSucursales suc) {
        try {
            return (BswRolUsuarioSucursal) entidadManager.createNativeQuery("select ru.* from bsw_rol_usuario_sucursal ru \n"
                    + " \n"
                    + "where ru.id_usuario=?\n"
                    + " and ru.id_sucursal=?"
                    + "  ", BswRolUsuarioSucursal.class
            ).setParameter(1, user.getId()).setParameter(2, suc.getId()).getSingleResult();

        } catch (Exception e) {
            return null;
        }

    }

    public BswGruposUsuarios getObtieneRolSucursal(BswUsuarios user, BswSucursales suc) {
        BswRolUsuarioSucursal rolSuc = obtieneUsuarioRol(user, suc);
        return rolSuc != null ? rolSuc.getBswGruposUsuarios() : null;
    }

    public boolean puedeActualizar(String nom_Forma) {
        userLogueado = loginControlador.getUsuario();

        BswAccesosGrupos ag;
        List<BswAccesosGrupos> lag;
        lag = entidadManager.createNamedQuery("BswAccesosGrupos.findByNomFormaAndCodGrupo").setParameter("nomForma", nom_Forma).setParameter("codGrupo", userLogueado.getBswGruposUsuarios().getCodGrupo()).setParameter("idEmpresa", getEmpresaLogueada().getId()).getResultList();
        if (lag != null && !lag.isEmpty()) {
            ag = lag.get(0);
            entidadManager.refresh(ag);
            if (ag.getPuedeActualizar().equals("S") && userLogueado.getEstado().equals("A")) {
                return true;
            }
        }
        return false;
    }

    public boolean poseeModulo(String cod_mod) {
        userLogueado = loginControlador.getUsuario();
    //    userLogueado = loginControlador.getUsuario();

        if (userLogueado != null) {

            List<BswAccesosGrupos> lag;
            try {
                lag = entidadManager.createNamedQuery("BswAccesosGrupos.findByCodGrupoAndCodModAndbswEmpresas", BswAccesosGrupos.class)
                        .setParameter("codGrupo", userLogueado.getBswGruposUsuarios().getCodGrupo())
                        .setParameter("codModulo", cod_mod).setParameter("puedeConsultar", "S").
                        setParameter("idEmpresa", getEmpresaLogueada().getId())
                        .getResultList();

                if (lag != null && !lag.isEmpty())// quiere decir q encontro un registro acceso grupo para el modulo y grupo dado.
                {
                    return true;
                }
            } catch (Exception e) {
                //log.error("Error en poseeModulo",e);
            }
        }
        return false;

    }

    public String getUserNombre() {
        userLogueado = loginControlador.getUsuario();
        return userLogueado.getCodUsuario();
    }

    public BswUsuarios getUserLogueado() {
        userLogueado = loginControlador.getUsuario();
        return userLogueado;
    }

    public boolean puedeConsultarEmpresasVarias() {
        userLogueado = loginControlador.getUsuario();
        if (userLogueado != null && userLogueado.getIndVerificaMenu() != null && userLogueado.getIndVerificaMenu().equals("S")) {
            return true;
        } else {
            return false;
        }
    }

    public BswEmpresas getEmpresaLogueada() {
        empresaLogueada = loginControlador.getEmpresaSeleccionada();
        return empresaLogueada;
    }

    public void setEmpresaLogueada(BswEmpresas empresaLogueada) {
        this.empresaLogueada = getEmpresaLogueada();
    }

    public boolean permiso(String formName, String parametro) {
        userLogueado = loginControlador.getUsuario();
        String permiso = "N";
        try {
            Query q = entidadManager.createNamedQuery("BswPermisosOpciones.findByEmpUsuParam");
            q.setParameter("empresa", userLogueado.getBswEmpresas());
            q.setParameter("usuario", userLogueado);
            q.setParameter("parametro", parametro);
            //q.setParameter("nomForma", formName);
            BswPermisosOpciones permisoOpciones = (BswPermisosOpciones) q.getSingleResult();
            permiso = permisoOpciones.getPermiso();
        } catch (Exception e) {
            //e.printStackTrace(); //TODO cambiar por el log despues
            //log.error("Error al buscar permiso",e);
            return false;
        }
        if (permiso.equals("S")) {
            return true;
        }
        return false;
    }

    public BswModulos getModulo(String param) {
        try {

            return (BswModulos) entidadManager.createNamedQuery("BswModulos.findByCodModulo").setParameter("codModulo", param).getSingleResult();

        } catch (Exception ex) {
            log.error("Error al cargar modulos ", ex);
        }
        return null;
    }

    public boolean puedeConsultarPreguntas() {
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_pregunta where estado is not true and id_usuario_respuesta=? "
                + " or id_usuario_respuesta_asistente=?")
                .setParameter(1, loginControlador.getUsuario().getId()).setParameter(2, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }

    public boolean puedeConsultarPendientePublicacion() {
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato p,conw_rda_cab r,conw_pasos_contratos pc where pendiente_publicacion is true and r.id=p.rda_id \n"
                + "and p.paso_id=pc.id and pc.nro_paso=11"
                + " and r.id_usuario=?")
                .setParameter(1, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }

    public boolean puedeConsultarPendienteFirma() {
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato p,conw_rda_cab r,conw_pasos_contratos pc where pendiente_publicacion is true and r.id=p.rda_id \n"
                + "and p.paso_id=pc.id and pc.nro_paso=12"
                + " and r.id_usuario=?")
                .setParameter(1, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }

    public boolean puedeConsultarFinContratos() {
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato p,conw_rda_cab r,conw_pasos_contratos pc where pendiente_publicacion is true and r.id=p.rda_id \n"
                + "and p.paso_id=pc.id and pc.nro_paso=13"
                + " and r.id_usuario=?")
                .setParameter(1, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }

    public boolean puedeConsultarAdendas() {
//        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato p, "
//                + " conw_rda_cab r  where  adenda_solicitada is true and p.rda_id=r.id and (r.coordinador_id=? or r.preparado_por=?)")
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato p, "
                + " conw_rda_cab r  where  adenda_solicitada is true and p.rda_id=r.id ").getSingleResult();
//                .setParameter(1, loginControlador.getUsuario().getId()).setParameter(2, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }

    public boolean puedeConsultarEvaluacion() {
        Long cant = (Long) entidadManager.createNativeQuery("select count(*) from conw_proceso_contrato c, conw_pasos_contratos pc, conw_evaluadores eval  \n"
                + " where c.rda_id=eval.conw_rda_cab_id and c.paso_id=pc.id and pc.nro_paso=9 and bsw_usuarios_id=?")
                .setParameter(1, loginControlador.getUsuario().getId()).getSingleResult();
        if (cant != null && cant > 0) {
            return true;
        }
        return false;
    }
}
