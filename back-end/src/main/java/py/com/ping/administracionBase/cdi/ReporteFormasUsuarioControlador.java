/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.util.ImprimirReporte;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author inv
 */
@Named
@ViewScoped
public class ReporteFormasUsuarioControlador extends AbstractControllerGenerico<BswFormas> {
    private static final Logger logger =
            LoggerFactory.getLogger(ReporteFormasUsuarioControlador.class);

    private ImprimirReporte imprimirreporte;
    private @Inject
    LoginControlador loginControlador;
    @PersistenceContext
    protected EntityManager em;

    public ReporteFormasUsuarioControlador() {
        super();

        imprimirreporte = new ImprimirReporte(getEmpresaLogueada());
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
    //</editor-fold>

    public void imprimirReporte() {
        if (formasABM != null && formasABM.getId() != null) {
            try {
                imprimirreporte.setNombreReporte("/jasper/otros/formasPorUsuario");
                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("nomForma", formasABM.getNomForma());
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
        } else {
            mensajeAlerta("Para imprimir el reporte debe especificar un formulario...");
        }
    }

    @Override
    public void setParametros() {
        this.formName = "BSRACCFOR";
        this.clase = BswFormas.class;
        this.paginaActual = "reporteFormasUsuario.xhtml";

        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:reporteFormasUsuarios.xhtml");
            getAcercaDe().add("- Controlador: ReporteFormasUsuariosControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswFormas.java");
        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para imprimir el reporte de Accesos de Usuarios a las Formas");
        }
    }

    public void limpiarCampos() {
        formasABM = null;

    }
    //<editor-fold defaultstate="collapsed" desc="Formas">
    private BswFormas formasABM;
    private BswFormas formasListaSeleccionado;

    /**
     * @return the formasABM
     */
    public BswFormas getFormasABM() {
        if (formasABM == null) {
            formasABM = new BswFormas();
        }
        return formasABM;
    }

    /**
     * @param formasABM the formasABM to set
     */
    public void setFormasABM(BswFormas formasABM) {
        this.formasABM = formasABM;
    }

    /**
     * @return the formasListaSeleccionado
     */
    public BswFormas getFormasListaSeleccionado() {
        return formasListaSeleccionado;
    }

    /**
     * @param formasListaSeleccionado the formasListaSeleccionado to set
     */
    public void setFormasListaSeleccionado(BswFormas formasListaSeleccionado) {
        if (formasListaSeleccionado != null) {
            formasABM = formasListaSeleccionado;
        }
        this.formasListaSeleccionado = formasListaSeleccionado;
    }

    public void formasChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                formasABM = (BswFormas) em.createNamedQuery("BswFormas.findByNomForma", BswFormas.class).setParameter("nomForma", event.getNewValue().toString()).getSingleResult();

            } catch (Exception ex) {
                formasABM = null;
                mensajeAlerta("Codigo de forma especificado no existe...");

                logger.error("Error al consultar formas", ex);
            }
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Lazy List Formas">
    private HashMap campos = new HashMap();
    
    private GenericLazyListNew genericDetalleFormasList;
    private LazyDataModel lazyListDetalleFormas;

    public LazyDataModel getLazyListDetalleFormas() {
        campos = new HashMap();
        lazyListDetalleFormas = this.genericDetalleFormasList.getLazyDataModel(BswFormas.class, campos);

        return lazyListDetalleFormas;
    }
    //</editor-fold>
    //</editor-fold>
}
