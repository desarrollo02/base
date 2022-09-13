/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

/**
 *
 * @author inv
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.util.ImprimirReporte;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ViewScoped
public class ReporteUsuariosGrupoControlador extends AbstractControllerGenerico<BswGruposUsuarios> {

    private static final Logger logger =
            LoggerFactory.getLogger(ReporteUsuariosControlador.class);
    private ImprimirReporte imprimirreporte;
    private @Inject
    LoginControlador loginControlador;
    @PersistenceContext
    protected EntityManager em;

    public ReporteUsuariosGrupoControlador() {
        super();
        imprimirreporte = new ImprimirReporte(getEmpresaLogueada());
    }

    @Override
    public void setParametros() {
        this.formName = "BSRGRUSU";
        this.clase = BswGruposUsuarios.class;
        this.paginaActual = "reporteUsuarioGrupos.xhtml";

        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:reporteUsuarioGrupos.xhtml");
            getAcercaDe().add("- Forma: BSRGRUSU");
            getAcercaDe().add("- Controlador: ReporteUsuariosGrupoControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswGruposUsuarios.java");
            getAcercaDe().add("- Reporte: usuarioGrupo.jrxml");

        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para imprimir el reporte de acceso grupo.");
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Imprimir Reporte">
    private String formato;

    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void imprimirReporte() {
   //     if (gruposUsuariosABM != null && gruposUsuariosABM.getId() != null) {
            try {
                imprimirreporte.setNombreReporte("usuarioGrupo");
                Map<String, Object> parametros = new HashMap<String, Object>();
                if(gruposUsuariosABM != null && gruposUsuariosABM.getId() != null){
                    parametros.put("codGrupo", gruposUsuariosABM.getCodGrupo());
                    parametros.put("descripcionGrupo", gruposUsuariosABM.getDescripcion());
                }
                parametros.put("impresoPor", getUserNombre());
                imprimirreporte.setParametros(parametros);
                setFormato(getFormato());
                if (getFormato().equals("PDF")) {
                    imprimirreporte.imprimir();
                } else if (getFormato().equals("XLS")) {
                    imprimirreporte.imprimirXLS();
                }
                limpiarCampos();
            } catch (Exception e) {
                mensajeAlerta("No fue posible imprimir el reporte...");
                logger.error("Error al imprimir reporte", e);
            }
  /*      } else {
            mensajeAlerta("Para imprimir el reporte debe especificar un grupo de usuario...");
        }*/
    }
    //</editor-fold>

    public void limpiarCampos() {
        grupoUsuarioListaSeleccionado = null;
        gruposUsuariosABM = null;

    }
    //<editor-fold defaultstate="collapsed" desc="Grupo Usuario">
    private BswGruposUsuarios gruposUsuariosABM;
    private BswGruposUsuarios grupoUsuarioListaSeleccionado;
    private List<BswGruposUsuarios> grupoUsuariosList;

    /**
     * @return the gruposUsuariosABM
     */
    public BswGruposUsuarios getGruposUsuariosABM() {
        if (gruposUsuariosABM == null) {
            gruposUsuariosABM = new BswGruposUsuarios();
        }
        return gruposUsuariosABM;
    }

    /**
     * @param gruposUsuariosABM the gruposUsuariosABM to set
     */
    public void setGruposUsuariosABM(BswGruposUsuarios gruposUsuariosABM) {
        this.gruposUsuariosABM = gruposUsuariosABM;
    }

    /**
     * @return the grupoUsuarioListaSeleccionado
     */
    public BswGruposUsuarios getGrupoUsuarioListaSeleccionado() {
        return grupoUsuarioListaSeleccionado;
    }

    /**
     * @param grupoUsuarioListaSeleccionado the grupoUsuarioListaSeleccionado to
     * set
     */
    public void setGrupoUsuarioListaSeleccionado(BswGruposUsuarios grupoUsuarioListaSeleccionado) {
        if (grupoUsuarioListaSeleccionado != null) {
            gruposUsuariosABM = grupoUsuarioListaSeleccionado;
        }
        this.grupoUsuarioListaSeleccionado = grupoUsuarioListaSeleccionado;
    }

    /**
     * @return the grupoUsuarioesList
     */
    public List<BswGruposUsuarios> getGrupoUsuarioesList() {
        try {
            grupoUsuariosList = em.createNamedQuery("BswGruposUsuarios.findAll").getResultList();
        } catch (Exception e) {
            logger.error("Error al listar grupoUsuarioes", e);
            setgrupoUsuarioesList(new ArrayList<BswGruposUsuarios>());
        }
        return grupoUsuariosList;
    }

    public void grupoUsuarioesChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                gruposUsuariosABM = (BswGruposUsuarios) em.createNamedQuery("BswGruposUsuarios.findByCodGrupo", BswGruposUsuarios.class).setParameter("codGrupo", event.getNewValue().toString()).getSingleResult();

            } catch (Exception ex) {
                gruposUsuariosABM = null;
                mensajeAlerta("Codigo de grupo Usuario especificado no existe...");

                logger.error("Error al consultar grupoUsuarioes", ex);
            }
        }
    }

    /**
     * @return the grupoUsuarioesList
     */
    public List<BswGruposUsuarios> getGrupoUsuariosList() {
        return grupoUsuariosList;
    }

    /**
     * @param grupoUsuarioesList the grupoUsuarioesList to set
     */
    public void setgrupoUsuarioesList(List<BswGruposUsuarios> grupoUsuarioesList) {
        this.grupoUsuariosList = grupoUsuarioesList;
    }
    //</editor-fold>
}
