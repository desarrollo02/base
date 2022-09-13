/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import com.sun.faces.component.visit.FullVisitContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.cdi.GenericLazyListNew;
import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.cdi.UltimosSitiosVisitados;
import py.com.ping.administracionBase.ejb.BswAccesosGruposEJB;
import py.com.ping.administracionBase.jpa.*;
import py.com.ping.utilitarios.ejb.GenericoEJB;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.interfaces.GenericQuery;
import py.com.ping.utilitarios.jpa.Entidad;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.PersistenceContext;
import py.com.ping.administracionBase.util.ComponentesVarios;

public abstract class AbstractControllerGenerico<Tipo extends Entidad> implements Serializable {

    /**
     * @return the mantenerABMPanel
     */
    public boolean isMantenerABMPanel() {
        return mantenerABMPanel;
    }

    /**
     * @param mantenerABMPanel the mantenerABMPanel to set
     */
    public void setMantenerABMPanel(boolean mantenerABMPanel) {
        this.mantenerABMPanel = mantenerABMPanel;
    }

    @PersistenceContext
    private EntityManager em;
    public static final Logger log = LoggerFactory.getLogger(AbstractControllerGenerico.class);
    private @Inject
    LoginControlador loginControlador;
    @EJB
    protected GenericoEJB<Tipo> genericEJB;
    protected String formName;
    private String userNombre;
    protected Tipo actual;
    protected Tipo detalleAeliminar;
    protected Tipo actualViejo;
    private Tipo objetoAgregado;
    private boolean esDialogoNewObject;
    protected Class<?> clase;
    protected String parametro;
    protected String idGrillaPrincipal;
    private boolean noMostrarMensajeExito;
    @EJB
    private GenericQuery genericQuery;
    protected HashMap camposQuery = new HashMap();
    protected BswUsuarios usuarioLogueado;
    protected BswEmpresas empresaLogueada;
    private Integer tabActiveIndexGrillaPrincipal;
    protected BswSucursales sucursalActual;
    protected boolean puedeVer;
    @Inject
    protected FuncionesVarias funcionesVarias;
    private boolean puedeInsertar;
    private boolean puedeBorrar;
    private boolean puedeConsultar;
    private boolean puedeActualizar;
    @EJB
    BswAccesosGruposEJB accesosGruposEJB;

    protected void antesBuscar() {
    }

    protected void despuesBuscar() {
    }
    protected Class<?> claseDetalle;
    protected ArrayList<String> ayuda = new ArrayList();
    protected ArrayList<String> acercaDe = new ArrayList();
    protected FacesMessage facesMsg;
    protected String paginaActual;
    private boolean nuevo;

    public AbstractControllerGenerico() {

        setIniciar();
    }

    private void setIniciar() {

        setParametros();

    }

    public void almacenaUltimosSitios() {
        try {
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            if (ctx != null) {
                Object request = ctx.getRequest();
                if (request instanceof HttpServletRequest) {
                    String url = ((HttpServletRequest) request).getRequestURL().toString();
                    UltimosSitiosVisitados ultimoSitio = new UltimosSitiosVisitados(paginaActual, url);
                    loginControlador.addUltimoSitio(ultimoSitio);
                    //       System.out.println("******************* ultimo sitio" + ultimoSitio.getLink());
                }
            }
        } catch (Exception e) {

            log.error("Error al almacenar ultimos sitios", e);
        }
    }

    @PostConstruct
    public void InicializaValores() {
        componentes.cargarDatos(em, getEmpresaLogueada(), getSucursalActual(),
                getUserNombre(), getUsuarioLogueado());
        inicializaVariablesLocales();
    }

    public void inicializaVariablesLocales() {

    }

    public abstract void setParametros();

    public boolean isPuedeVer(String parametro) {
        puedeVer = accesosGruposEJB.permiso(formName, parametro);
        return puedeVer;
        // return true;
    }

    public void setPuedeVer(boolean puedeVer) {
        this.puedeVer = puedeVer;
    }

    /**
     * Metodo que se ejecuta antes de realizar cualquier accion ya sea insertar
     * un nuevo registro o actualizar uno existente, es para asignar valores
     * antes de guardar y antes de hacer las validaciones. Ej: Asignar a actual
     * las referencias a otras clases que se encuentran seteadas en
     * componentesVarios (getComponentes)
     */
    protected void antesABM() {
    }

    public void grabar() {

        try {
            esLimpiarBoton = true;
            antesABM();
            if (!esModificarDetalle) {

                if (validaciones()) {
                    antesInsertar();
                    insertarRegistro();
                    despuesInsertar();
                    esModificarDetalle = false;
                    if (!mantenerABMPanel) {
                        esABMPanel = false;
                        mantenerABMPanel = false;
                    }

                } else {
                    return;
                }
            } else {
                if (validaciones()) {
                    antesActualizar();
                    actualizarRegistro();
                    despuesActualizar();
                    esModificarDetalle = false;
                    esABMPanel = false;
//                    limpiar();
                } else {
                    return;
                }
            }
            despuesABM();
            limpiar();

        } catch (EJBException e) {
            Throwable cause = findExceptionRootCause(e);
            String[] msn = cause.getMessage() != null ? cause.getMessage().split("\n") : new String[1];
            mensajeError("Ocurrio un error al intentar insertar el registro..." + msn[0]);
            log.error("Error al intentar insertar registro.", e);
            procesarError();
        } catch (Exception ex) {
            log.error("Error al intentar modificar registro.", ex);
            mensajeError("Error al intentar guardar el registro.");
            procesarError();
        }
    }

    public void procesarError() {
        esABMPanel = true;

    }

    public void despuesABM() {
        //  limpiar();
    }

    public void insertarRegistro() throws Exception {
//        actual.setUserAuditID(getUsuarioLogueado());
        genericEJB.insertar(actual);
    }

    public void actualizarRegistro() throws Exception {
//        actual.setUserAuditID(getUsuarioLogueado());
        genericEJB.actualizar(actual);
    }

    public void limpiaTabla() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            DataTable table = (DataTable) findComponent(
                    facesContext.getViewRoot(), getIdGrillaPrincipal());
            FilterMeta.Builder nuevosFiltros = FilterMeta.builder();
//            table.getFilterBy().clear();
            table.setFilterBy(nuevosFiltros);
            table.reset();
            tabActiveIndexGrillaPrincipal = 0;

        } catch (Exception e) {
        }

    }

    public void limpiarDatosLocales() {
    }

    public void limpiaTabla(String id) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            DataTable table = (DataTable) findComponent(
                    facesContext.getViewRoot(), id);
            FilterMeta.Builder nuevosFiltros = FilterMeta.builder();
//            table.getFilterBy().clear();
            table.setFilterBy(nuevosFiltros);
            table.reset();
        } catch (Exception e) {
        }

    }

    public UIComponent findComponent(UIViewRoot root, final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        // UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });

        return found[0];

    }

    public void limpiarTodosAddNew() {
        try {
            actual = (Tipo) clase.newInstance();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        actual = null;
        limpiaTabla();

        try {
            actual = (Tipo) clase.newInstance();
        } catch (Exception e) {
            log.error("Error en limpiar los datos", e);
        }
        limpiarDatosLocales();

    }

    public void cacelar() {
        actual = null;
    }

    protected void antesInsertar() throws Exception {
    }

    protected boolean validaciones() throws Exception {
        Set<ConstraintViolation<Tipo>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(actual, AbstractControlerValidationGroups.class);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Tipo> constraintViolation : constraintViolations) {
                mensajeAlerta(constraintViolation.getMessage());

            }
            esABMPanel = true;
            return procesaFallo();

        } else {
            return validacionesLocales();
        }

    }

    protected boolean validacionesLocales() {
        return true;
    }

    protected boolean procesaFallo() {
        return false;
    }

    protected void despuesInsertar() throws Exception {
        if (esDialogoNewObject) {
            objetoAgregado = actual;
            cargarDatosNuevoObjeto();
        }
        if (!noMostrarMensajeExito) {
            mensajeExito("Los datos fueron guardados correctamente!");
        }
    }

    protected void antesActualizar() throws Exception {
    }

    protected void antesEliminar() throws Exception {
    }

    protected void cargarDatosNuevoObjeto() {

    }

    protected void despuesActualizar() throws Exception {
        actualViejo = null;
        if (!noMostrarMensajeExito) {
            mensajeExito("Los datos fueron actualizados correctamente!");
        }
    }

    public void delete() throws Exception {
        genericEJB.eliminar((Tipo) (getEm().find(clase, detalleAeliminar.getPK())));
    }

    public void eliminarRegistro() {
        try {
            if (puedeEliminar()) {
                delete();
                limpiar();
                if (!noMostrarMensajeExito) {
                    mensajeExito("Registro eliminado con éxito!");
                }
                despuesEliminar();
                setDetalleAeliminar(null);
            }
        } catch (EJBException e) {
            Throwable cause = findExceptionRootCause(e);
            String[] msn = cause.getMessage().split("\n");
            mensajeError("Ocurrio un error al eliminar  el registro..." + msn[0]);
            log.error("Error al intentar eliminar registro.", e);
        } catch (Exception ex) {
            log.error("Error al intentar eliminar registro.", ex);
            mensajeError("Error al intentar eliminar el registro.");
        }
    }

    protected boolean puedeEliminar() {
        return true;
    }

    public Tipo getActual() {

        try {
            if (this.actual == null) {
                this.setActual((Tipo) this.clase.newInstance());
                this.setNuevo(true);
            } else {
                this.setNuevo(false);
            }

        } catch (InstantiationException ex) {
            log.error("error", ex);
        } catch (IllegalAccessException ex) {
            log.error("error", ex);
        }

        return this.actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(Tipo actual) {
        this.actual = actual;
    }

    /**
     * @return the acercaDe
     */
    public ArrayList<String> getAcercaDe() {
        return acercaDe;
    }

    /**
     * @param acercaDe the acercaDe to set
     */
    public void setAcercaDe(ArrayList<String> acercaDe) {
        this.acercaDe = acercaDe;
    }

    /**
     * @return the ayuda
     */
    public ArrayList<String> getAyuda() {
        return ayuda;
    }

    /**
     * @param ayuda the ayuda to set
     */
    public void setAyuda(ArrayList<String> ayuda) {
        this.ayuda = ayuda;
    }

    /**
     * @return the actualViejo
     */
    public Tipo getActualViejo() {
        try {
            if (this.actualViejo == null) {
                this.setActualViejo((Tipo) this.clase.newInstance());
            }
        } catch (InstantiationException ex) {
            log.error("error", ex);
        } catch (IllegalAccessException ex) {
            log.error("error", ex);
        }

        return actualViejo;
    }

    /**
     * @param actualViejo the actualViejo to set
     */
    public void setActualViejo(Tipo actualViejo) {
        this.actualViejo = actualViejo;
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

    /**
     * @return the nuevo
     */
    public boolean isNuevo() {
        return nuevo;
    }

    /**
     * @param nuevo the nuevo to set
     */
    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    /**
     * @return the paginaActual
     */
    public String getPaginaActual() {
        return paginaActual;
    }

    /**
     * @param paginaActual the paginaActual to set
     */
    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    protected void despuesAgregarDetalle() throws Exception {
    }

    protected void despuesEliminar() throws Exception {
    }
    //<editor-fold defaultstate="collapsed" desc="Administracion de Pantallas">
    protected boolean esModificarDetalle;
    protected boolean esABMPanel;
    private boolean mantenerABMPanel;
    private boolean noMostrarBotonesABM;

    protected void antesAccionABMDetalle() {
    }

    protected void despuesAccionABMDetalle() {
    }

    /**
     * Este metodo se ejecuta justo despues de presionar el boton Nuevo. Este
     * metodo debe ser sobreescrio si se necesita inicializar valores en
     * seccionAbm Ej: Si seccionAbm tiene un campo fecha y esa fecha debe ser
     * inicializada al dia actual.
     */
    public void accionABMDetalle() {
        antesAccionABMDetalle();
        setEsABMPanel(true);
        if (!esModificarDetalle) {
            limpiar();
        }
        despuesAccionABMDetalle();
    }

    public void cancelarAccionABM() {
        setEsABMPanel(false);
        limpiar();
    }

    /**
     * @return the esModificarDetalle
     */
    public boolean isEsModificarDetalle() {
        return esModificarDetalle;
    }

    /**
     * @param esModificarDetalle the esModificarDetalle to set
     */
    public void setEsModificarDetalle(boolean esModificarDetalle) {
        this.esModificarDetalle = esModificarDetalle;
    }

    /**
     * @return the esABMPanel
     */
    public boolean isEsABMPanel() {
        return esABMPanel;
    }

    /**
     * @param esABMPanel the esABMPanel to set
     */
    public void setEsABMPanel(boolean esABMPanel) {
        this.esABMPanel = esABMPanel;
    }

    /**
     * Precarga los datos para la edicion del objeto principal seleccionado en
     * la lista principal de la vista.
     */
    public void cargaDatosParaEditar() {

        actual = actualViejo;
        setEsABMPanel(true);
        cargarCamposRelacionados();
    }

    public void cargarCamposRelacionados() {
    }

    //</editor-fold>
    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the camposQuery
     */
    public HashMap getCamposQuery() {
        if (camposQuery == null) {
            camposQuery = new HashMap();
        }
        return camposQuery;
    }

    /**
     * @param camposQuery the camposQuery to set
     */
    public void setCamposQuery(HashMap camposQuery) {
        this.camposQuery = camposQuery;
    }

    /**
     * @return the genericQuery
     */
    public GenericQuery getGenericQuery() {

        return genericQuery;
    }

    /**
     * @param genericQuery the genericQuery to set
     */
    public void setGenericQuery(GenericQuery genericQuery) {
        this.genericQuery = genericQuery;
    }

    /**
     * @return the idGrillaPrincipal
     */
    public String getIdGrillaPrincipal() {
        return idGrillaPrincipal;
    }

    /**
     * @param idGrillaPrincipal the idGrillaPrincipal to set
     */
    public void setIdGrillaPrincipal(String idGrillaPrincipal) {
        this.idGrillaPrincipal = idGrillaPrincipal;
    }

    /**
     * @return the detalleAeliminar
     */
    public Tipo getDetalleAeliminar() {
        try {
            if (detalleAeliminar == null) {
                detalleAeliminar = (Tipo) this.clase.newInstance();
            }

        } catch (InstantiationException ex) {
            log.error("error", ex);
        } catch (IllegalAccessException ex) {
            log.error("error", ex);
        }
        return detalleAeliminar;
    }

    /**
     * @param detalleAeliminar the detalleAeliminar to set
     */
    public void setDetalleAeliminar(Tipo detalleAeliminar) {
        this.detalleAeliminar = detalleAeliminar;
    }

    /**
     * @return the userNombre
     */
    public String getUserNombre() {
        userNombre = getLoginControlador().getUsername();
        return userNombre;
    }

    /**
     * @param userNombre the userNombre to set
     */
    public void setUserNombre(String userNombre) {
        this.userNombre = userNombre;
    }

    //<editor-fold defaultstate="collapsed" desc="Datos Comunes">
    public BswUsuarios getUsuarioLogueado() {
        this.usuarioLogueado = loginControlador.getUsuario();
        return usuarioLogueado;
    }

    public String getIpCliente() {
        return getLoginControlador().getIpCliente();
    }

    public void setUsuarioLogueado(BswUsuarios usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public BswEmpresas getEmpresaLogueada() {
        empresaLogueada = getLoginControlador().getEmpresaSeleccionada();
        return this.empresaLogueada;
    }

    public void setEmpresaLogueada(BswEmpresas empresaLogueada) {
        getLoginControlador().setEmpresaSeleccionada(empresaLogueada);
        this.empresaLogueada = empresaLogueada;
    }

    public BswSucursales getSucursalActual() {
        sucursalActual = getLoginControlador().getSucursal();
        return sucursalActual;
    }

    /**
     * @return the verResumenComponente
     */
    public Boolean getVerResumenComponente() {
        return getLoginControlador() != null ? getLoginControlador().getVerResumenComponente() : false;
    }

    /**
     * @param verResumenComponente the verResumenComponente to set
     */
    public void setVerResumenComponente(Boolean verResumenComponente) {
        getLoginControlador().setVerResumenComponente(verResumenComponente);
    }

    public void setSucursalActual(BswSucursales sucursalActual) {
        getLoginControlador().setSucursal(sucursalActual);
        this.sucursalActual = sucursalActual;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Permisos Pagina">

    public boolean isPuedeInsertar() {
        this.puedeInsertar = accesosGruposEJB.puedeInsertar(formName);
        return this.puedeInsertar;
        //  return true;
    }

    public void setPuedeInsertar(boolean puedeInsertar) {
        this.puedeInsertar = puedeInsertar;
    }

    public boolean isPuedeActualizar() {
        this.puedeActualizar = accesosGruposEJB.puedeActualizar(formName);
        return puedeActualizar;
        // return true;
    }

    public void setPuedeActualizar(boolean puedeActualizar) {
        this.puedeActualizar = puedeActualizar;
    }

    public boolean isPuedeBorrar() {
        this.puedeBorrar = accesosGruposEJB.puedeBorrar(formName);
        return puedeBorrar;
        //   return true;
    }

    public void setPuedeBorrar(boolean puedeBorrar) {
        this.puedeBorrar = puedeBorrar;
    }

    public boolean isPuedeConsultar() {
        this.puedeConsultar = accesosGruposEJB.puedeConsultar(formName);
        return puedeConsultar;
        // return true;
    }

    public void setPuedeConsultar(boolean puedeConsultar) {
        this.puedeConsultar = puedeConsultar;
    }

//</editor-fold>
    private GenericLazyListNew genericLazyListaActual;
    private String filtroQuery;

    /**
     * @return the filtroQuery
     */
    public String getFiltroQuery() {
        if (filtroQuery == null) {
            filtroQuery = "";
        }
        return filtroQuery;
    }

    /**
     * @param filtroQuery the filtroQuery to set
     */
    public void setFiltroQuery(String filtroQuery) {
        this.filtroQuery = filtroQuery;
    }

    public GenericLazyListNew getGenericLazyListaActual() {
        if (genericLazyListaActual == null) {
            genericLazyListaActual = new GenericLazyListNew(em, clase);
        }
        return genericLazyListaActual;
    }

    public void setGenericLazyListaActual(GenericLazyListNew genericLazyListaActual) {
        this.genericLazyListaActual = genericLazyListaActual;
    }

    /**
     * /**
     * Obtiene la lista de los registros almacenados del Tipo (Actual)
     *
     * @return List<Tipo>
     */
    public List<Tipo> getListaTodosActual() {
        try {
            getGenericQuery().setEm(getEm());
            return getGenericQuery().getSearchResultList(camposQuery, this.clase);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Obtiene la lista de los registros almacenados del Tipo (Actual)
     *
     * @return List<Tipo>
     */
    public LazyDataModel getLazyListaTodosActual() {
        try {
            System.err.println("filtooooo" + filtroQuery);
            getGenericQuery().setEm(getEm());
            camposQuery.put("WHERE", "1=1 " + getFiltroQuery());
            return getGenericLazyListaActual().getLazyDataModel(this.clase, camposQuery);
            // return getGenericQuery().getSearchResultList(camposQuery, this.clase);
        } catch (Exception e) {
        }
        return null;
    }

    public LazyDataModel getListaTodosActualLazy() {

        return null;
    }

    public void dummy() {

    }
    //<editor-fold defaultstate="collapsed" desc="Componentes">
    @Inject
    private ComponentesVarios componentes;

    /**
     * @return the componentes
     */
    public ComponentesVarios getComponentes() {
        return componentes;
    }

    /**
     * @param componentes the componentes to set
     */
    public void setComponentes(ComponentesVarios componentes) {
        this.componentes = componentes;
    }

//</editor-fold>
    /**
     * ***************************SECCION PARA PANTALLS
     * CABECERA/DETALLE**************************
     */
    // <editor-fold defaultstate="collapsed" desc="Seccion Nuevo/Busqueda">
    protected boolean esBusqueda;
    protected boolean esNuevaBusqueda;
    protected boolean esLimpiarBoton = false;

    public void nuevoVal() {
        setEsBusqueda(false);
        setEsLimpiarBoton(true);
        limpiar();
        esModificarDetalle = false;
        getGenericQuery().setRegistroInicial(null);
        getGenericQuery().setCantidadReg(null);
        mensajeExito("Puede realizar carga de nuevos limpiarcomprobantes");
    }

    public void buscar() {
        setEsBusqueda(true);
        setEsLimpiarBoton(true);
        limpiar();
        esModificarDetalle = true;
        mensajeExito("Puede realizar busqueda de comprobantes");
    }

    /**
     * @return the esBusqueda
     */
    public boolean isEsBusqueda() {
        return esBusqueda;
    }

    /**
     * @param esBusqueda the esBusqueda to set
     */
    public void setEsBusqueda(boolean esBusqueda) {
        this.esBusqueda = esBusqueda;
    }

    /**
     * @return the esNuevaBusqueda
     */
    public boolean isEsNuevaBusqueda() {
        return esNuevaBusqueda;
    }

    /**
     * @param esNuevaBusqueda the esNuevaBusqueda to set
     */
    public void setEsNuevaBusqueda(boolean esNuevaBusqueda) {
        this.esNuevaBusqueda = esNuevaBusqueda;
    }

    /**
     * @return the esLimpiarBoton
     */
    public boolean isEsLimpiarBoton() {
        return esLimpiarBoton;
    }

    /**
     * @param esLimpiarBoton the esLimpiarBoton to set
     */
    public void setEsLimpiarBoton(boolean esLimpiarBoton) {
        this.esLimpiarBoton = esLimpiarBoton;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Busqueda Registros">
    private List<Tipo> listaComprobantesCab;
    protected Tipo compCabecera;
    private BigDecimal indiceMAX = null;
    private BigDecimal indiceMIN = null;
    private BigDecimal indiceActual = null;
    private Integer cantidadRegistros = null;
    private Integer registroInicial = null;
    private Integer inicial = null;
    private Integer maxResult = 100;
    private Integer indice = 0;
    private boolean inicializaValores;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfh = new SimpleDateFormat("dd-MM-yyyy_HH:mm");

    protected HashMap queryBusquedaFiltros;

    public void traerSiguiente() throws InstantiationException, IllegalAccessException {
        if (listaComprobantesCab != null && !listaComprobantesCab.isEmpty()) {
            if (indice < listaComprobantesCab.size() - 1) {
                indice = indice + 1;
                compCabecera = listaComprobantesCab.get(indice);
                cargaComprobante();
            } else if (maxResult + inicial >= cantidadRegistros) {

                inicializaValores = true;
                buscarComprobantes();

            } else {
                inicial = inicial + maxResult;
                indice = 0;
                buscarComprobantes();
            }
        }
    }

    public void traerAnterior() throws InstantiationException, IllegalAccessException {
        if (listaComprobantesCab != null && !listaComprobantesCab.isEmpty()) {
            if (0 < indice) {
                indice = indice - 1;
                compCabecera = listaComprobantesCab.get(indice);
                cargaComprobante();
            } else if (registroInicial == inicial) {
                inicial = cantidadRegistros - maxResult;
                indice = maxResult - 1;
                buscarComprobantes();
            } else if (inicial >= maxResult) {
                inicial = inicial - maxResult;
                indice = maxResult - 1;
                buscarComprobantes();
            } else {
                maxResult = maxResult - inicial;
                inicial = 0;
                indice = maxResult - 1;
                buscarComprobantes();
            }
        }
    }

    public void inicializarValores() {
        try {
            getGenericQuery().setEm(getEm());
            Long result = getGenericQuery().getSearchCount(queryBusquedaFiltros, clase);
            cantidadRegistros = result.intValue();
            inicial = 0;
            indice = 0;
        } catch (Exception e) {
            log.error("Error al cargar Indices", e);

        }
    }

    public void obtieneFiltros() {
    }

    public void buscarComprobantes() {
        try {

            if (isEsNuevaBusqueda()) {
                obtieneFiltros();
                inicializaValores = true;
            }
            if (inicializaValores) {
                inicializarValores();
                registroInicial = inicial;
                setEsNuevaBusqueda(false);
                inicializaValores = false;
                maxResult = 100;
                if (maxResult > cantidadRegistros) {
                    maxResult = cantidadRegistros;
                }
                indice = 0;
            }
            //em.getEntityManagerFactory().getCache().evict(clase);
            getGenericQuery().setEm(getEm());
            getGenericQuery().setRegistroInicial(inicial);
            getGenericQuery().setCantidadReg(maxResult);
            listaComprobantesCab = getGenericQuery().getSearchResultList(queryBusquedaFiltros, clase);

            if (listaComprobantesCab != null && !listaComprobantesCab.isEmpty()) {
                compCabecera = listaComprobantesCab.get(indice);

                if (compCabecera != null) {
                    cargaComprobante();
                } else {
                    mensajeAlerta("La consulta no produjo ningun resultado");
                }
            } else {
                mensajeAlerta("La consulta no produjo ningun resultado");
            }
        } catch (Exception e) {
            log.error("Error al buscar comprobantes", e);
            mensajeAlerta("Ocurrio un error al realizar la busqueda.");
        }
    }

    public void cargaComprobante() {
        if (compCabecera != null) {
            try {
                limpiar();
                actual = compCabecera;
            } catch (Exception e) {
                log.error("error al cargar comprobantes", e);
            }
        }
    }

    /**
     * @return the compCabecera
     */
    public Tipo getCompCabecera() {
        return compCabecera;
    }

    /**
     * @param compCabecera the compCabecera to set
     */
    public void setCompCabecera(Tipo compCabecera) {
        this.compCabecera = compCabecera;
    }
// </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Filtro Por Fecha">
    /**
     * Metodo que recibe una fecha y retorna el valor de la fecha en formato
     * dd/MM/yyyy tipo String
     *
     * @param fecha
     * @return
     */
    public String filtroDate(Date fecha) {
        if (fecha != null) {
            funcionesVarias.setFormato("dd/MM/yyyy");
            return funcionesVarias.getSdf().format(fecha);
        }
        return "fecHabilitacion";

    }

    public String convirteFechaFiltro(Date fec) {
        if (fec != null) {
            return sdf.format(fec);
        } else {
            return null;
        }
    }

    //</editor-fold>
    public void ejecutarSeleccion() {

    }

    public static void removeViewScopedBean() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    /**
     * @return the esDialogoNewObject
     */
    public boolean isEsDialogoNewObject() {
        return esDialogoNewObject;
    }

    /**
     * @param esDialogoNewObject the esDialogoNewObject to set
     */
    public void setEsDialogoNewObject(boolean esDialogoNewObject) {
        this.esDialogoNewObject = esDialogoNewObject;
    }

    /**
     * @return the objetoAgregado
     */
    public Tipo getObjetoAgregado() {
        return objetoAgregado;
    }

    /**
     * @param objetoAgregado the objetoAgregado to set
     */
    public void setObjetoAgregado(Tipo objetoAgregado) {
        this.objetoAgregado = objetoAgregado;
    }

    /**
     * @return the loginControlador
     */
    public LoginControlador getLoginControlador() {
        return loginControlador;
    }

    /**
     * @param loginControlador the loginControlador to set
     */
    public void setLoginControlador(LoginControlador loginControlador) {
        this.loginControlador = loginControlador;
    }

    

    /**
     * @return the noMostrarMensajeExito
     */
    public boolean isNoMostrarMensajeExito() {
        return noMostrarMensajeExito;
    }

    /**
     * @param noMostrarMensajeExito the noMostrarMensajeExito to set
     */
    public void setNoMostrarMensajeExito(boolean noMostrarMensajeExito) {
        this.noMostrarMensajeExito = noMostrarMensajeExito;
    }

    /**
     * @return the noMostrarBotonesABM
     */
    public boolean isNoMostrarBotonesABM() {
        return noMostrarBotonesABM;
    }

    /**
     * @param noMostrarBotonesABM the noMostrarBotonesABM to set
     */
    public void setNoMostrarBotonesABM(boolean noMostrarBotonesABM) {
        this.noMostrarBotonesABM = noMostrarBotonesABM;
    }

    /**
     * @return the genericEJB
     */
    public GenericoEJB<Tipo> getGenericEJB() {
        return genericEJB;
    }

    /**
     * @param genericEJB the genericEJB to set
     */
    public void setGenericEJB(GenericoEJB<Tipo> genericEJB) {
        this.genericEJB = genericEJB;
    }

    public Integer getTabActiveIndexGrillaPrincipal() {
        return tabActiveIndexGrillaPrincipal;
    }

    public void setTabActiveIndexGrillaPrincipal(Integer tabActiveIndexGrillaPrincipal) {
        this.tabActiveIndexGrillaPrincipal = tabActiveIndexGrillaPrincipal;
    }

    public static Throwable findExceptionRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
    
  
      public BswModulos getModulo(String codModulo) {
        HashMap camposFiltro = new HashMap();
        camposFiltro.put("codModulo", codModulo);
        camposFiltro.put("bswEmpresas.id", getEmpresaLogueada().getId());
        getGenericQuery().setEm(getEm());
        return ((BswModulos) getGenericQuery().getSearchSingleResult(camposFiltro, BswModulos.class, null));
    }

    public FilterMeta crearFiltro(String campo, String tipoFiltro, List valores) {
        return FilterMeta.builder()
                .field(campo)
                .filterValue(valores)
                .matchMode(MatchMode.valueOf(tipoFiltro))
                .build();
    }

    public String formateaFechaHora(Date fec) {
        if (fec != null) {
            return sdfh.format(fec);
        } else {
            return null;
        }
    }
}
