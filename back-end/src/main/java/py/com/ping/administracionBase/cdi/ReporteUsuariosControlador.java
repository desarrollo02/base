/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.administracionBase.util.ImprimirReporte;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
/**
 *
 * @author inv
 */
@Named
@ViewScoped
public class ReporteUsuariosControlador extends AbstractControllerGenerico<BswUsuarios> {
    private static final Logger log =
            LoggerFactory.getLogger(ReporteUsuariosControlador.class);

    private BswUsuarios usuarioSeleccionado;
    private String estado;
    private String codUsuario;
    private String nombreUsuario;
    private String condicion;
    private ImprimirReporte imprimirreporte;
    //private String inputPresionado;
    private String paginaActual;
    private @Inject
    LoginControlador loginControlador;
    @PersistenceContext
    protected EntityManager em;

    public ReporteUsuariosControlador() {
        super();
        imprimirreporte = new ImprimirReporte(getEmpresaLogueada());
    }

    public BswUsuarios getUsuarioSeleccionado() {
        if (usuarioSeleccionado == null) {
            usuarioSeleccionado = new BswUsuarios();
        }
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(BswUsuarios usuarioSeleccionado) {

        this.usuarioSeleccionado = usuarioSeleccionado;

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    @Override
    public void setParametros() {
        this.formName = "BSRUSR";
        this.clase = BswUsuarios.class;
        this.paginaActual = "reporteUsuarios.xhtml";


        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:reporteUsuarios.xhtml");
            getAcercaDe().add("- Forma: BSRPERMI");
            getAcercaDe().add("- Controlador: ReporteUsuariosControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswUsuarios.java");
            getAcercaDe().add("- Reporte: rptBswUsuarios.jrxml");
        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para imprimir la lista de usuarios del sistema");
        }
    }

    public void limpiarCampos() {
        setCodUsuario("");
        setNombreUsuario("");
        setEstado("T");
       usuarioSeleccionado=null;
        condicion = "";
    }
    //<editor-fold defaultstate="collapsed" desc="Usuarios">
    private BswUsuarios usuariosListaSeleccionado;

    /**
     * @return the personaListaSeleccionado
     */
    public BswUsuarios getUsuariosListaSeleccionado() {
        return usuariosListaSeleccionado;
    }

    /**
     * @param personaListaSeleccionado the personaListaSeleccionado to set
     */
    public void setUsuariosListaSeleccionado(BswUsuarios usuariosListaSeleccionado) {
        if (usuariosListaSeleccionado != null) {
            usuarioSeleccionado = usuariosListaSeleccionado;
        }
        this.usuariosListaSeleccionado = usuariosListaSeleccionado;
    }
    //<editor-fold defaultstate="collapsed" desc="Lazy Usuarios">
    private LazyDataModel lazyListUsuarios;
    private GenericLazyListNew gLL;

    public LazyDataModel getLazyListUsuarios() {

        HashMap camposReservaArt = new HashMap();
        camposReservaArt.put("bswEmpresas.id", this.getEmpresaLogueada().getId().toString());
        return lazyListUsuarios = this.gLL.getLazyDataModel(BswUsuarios.class, camposReservaArt);
    }

    public void setLazyListUsuarios(LazyDataModel lpb) {
        this.lazyListUsuarios = lpb;
    }
    //</editor-fold>

    public void usuariosChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                usuarioSeleccionado = (BswUsuarios) em.createNativeQuery("select * from bsw_usuarios where id_empresa = ?  and cod_usuario= ? ",
                        BswUsuarios.class).setParameter(1, getEmpresaLogueada().getId()).setParameter(2, event.getNewValue()).getSingleResult();

            } catch (Exception ex) {
                usuarioSeleccionado = null;
                mensajeAlerta("Codigo de usuario especificado no existe...");

                log.error("Error al consultar sucursales", ex);
            }
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Imprimir Reportes">

    public void imprimirReporte() {
        try {
            condicion = "";

            if (usuarioSeleccionado != null && usuarioSeleccionado.getId()!=null) {
                System.out.println("ENTRE!!");
                condicion = "and usuario.cod_usuario = '" + usuarioSeleccionado.getCodUsuario() + "'";
            }
            if (this.getEstado().equals("I") || this.getEstado().equals("A")) {
                condicion = condicion + "and usuario.estado = '" + getEstado() + "'";
            }

            imprimirreporte.setNombreReporte("rptBswUsuarios");
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("parametros", condicion);
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
            log.error("Error al imprimir reporte", e);
        }
    }
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

}
