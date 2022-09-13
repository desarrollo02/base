package py.com.ping.administracionBase.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.freya.view.GuestPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.ejb.BswAccesosGruposEJB;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.administracionBase.ejb.LoginEJB;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal;
import py.com.ping.administracionBase.jpa.BswSession;

import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.util.ApplicationConfigPing;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.administracionBase.util.BaseLecturaArchivos;
import py.com.ping.utilitarios.ejb.GenericoEJB;
import py.com.ping.utilitarios.interfaces.GenericQuery;
import py.com.ping.utilitarios.utils.FileManager;

@Named
@SessionScoped
public class LoginControlador implements Serializable {

    /**
     * @return the verResumenComponente
     */
    public Boolean getVerResumenComponente() {
        return verResumenComponente;
    }

    /**
     * @param verResumenComponente the verResumenComponente to set
     */
    public void setVerResumenComponente(Boolean verResumenComponente) {
        this.verResumenComponente = verResumenComponente;
    }
    private static final Logger logger
            = LoggerFactory.getLogger(LoginControlador.class);

    private BswUsuarios usuario;
    private String username = "";
    private BswEmpresas empresaSeleccionada;
    private List<BswEmpresas> empresasList;
    private List<String> empresasDescripcion;
    private String descripcionSeleccionada;
    private BswSucursales sucursalLogueada;
    private List<BswSucursales> sucursalesList;
    private List<BswGruposUsuarios> gruposUsuariosList;
    private List<BswAccesosGrupos> accesosGruposList;
    @EJB
    BswAccesosGruposEJB accesosGruposEJB;
    private String password;
    protected FacesMessage facesMsg;
    @PersistenceContext
    private EntityManager em;
    private Integer tiempoEsperaMensaje;
    @EJB
    LoginEJB loginEJB;
    @EJB
    GenericoEJB genericoEJB;
    private List<UltimosSitiosVisitados> ultimosSitiosVisitados;

    private String cantidadMensajeUsuario;
    private Long cantidadProcesosPendientes;
    @EJB
    private GenericQuery genericQuery;
    private String menus;
    private BswSession session;
    private BswRolUsuarioSucursal userRol;
    private String redireccion;

    private Boolean accesoViaToken = false;
    public boolean puedeConsultarForma() {
        return usuario.getBswGruposUsuarios().getCodGrupo().equals("GEO");

    }
    public LoginControlador() {
        tiempoEsperaMensaje = 30000;
        empresasList = new ArrayList<BswEmpresas>();
        empresasDescripcion = new ArrayList<String>();
    }

    public Integer getTiempoEsperaMensaje() {
        return tiempoEsperaMensaje;
    }

    public void setTiempoEsperaMensaje(Integer tiempoEsperaMensaje) {
        this.tiempoEsperaMensaje = tiempoEsperaMensaje;
    }

    public void setSucursal(BswSucursales bswSucursales) {
        this.sucursalLogueada = bswSucursales;
    }

    public BswSucursales getSucursal() {
        if (sucursalLogueada == null) {
            sucursalLogueada = new BswSucursales();
        }
        return sucursalLogueada; // sucursal por defecto del usuario
    }

    public boolean getLogueado() {
        return getUsuario() != null;
    }

    public String logout() {
        setUsuario(null);
        return "/index";
    }

    public BswEmpresas getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }
    @EJB
    FileManager fileManager;
    @Inject
    GuestPreferences guestPreferences;

    public void setEmpresaSeleccionada(BswEmpresas emp) {

        if (emp != null && emp.getId() != null) {
            BswGruposUsuarios grupoU = getGruposUsuariosEmpresa(emp);
            if (grupoU != null && grupoU.getId() != null) {
                BswAccesosGrupos Acceso = getAccesosGruposEmpresa(emp);
                if (Acceso != null && Acceso.getId() != null) {
                    BswSucursales suc = getSucursalesEmpresa(emp);
                    if (suc != null && suc.getId() != null) {
                        empresaSeleccionada = emp;
                        setSucursal(suc);
                        mensajeExito("Accedio a la empresa: " + empresaSeleccionada.getDescripcion() + " - Sucursal: " + sucursalLogueada.getDescripcion());
                    } else {
                        mensajeAlerta("No es posible cambiar de empresa, la empresa seleccionada no posee ninguna sucursal asignada");
                        return;
                    }

                } else {
                    mensajeAlerta("No es posible cambiar de empresa, la empresa seleccionada no posee ningun Grupo Acceso asignada");
                    return;
                }

            } else {
                mensajeAlerta("No es posible cambiar de empresa, la empresa seleccionada no posee ningun Grupo de Usuario asignada");
                return;
            }

        }

        if (empresaSeleccionada != null && empresaSeleccionada.getFoto() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(empresaSeleccionada.getFoto(), empresaSeleccionada.getNombreImagen());
            } catch (Exception ex) {

            }
        }
        if (empresaSeleccionada != null && empresaSeleccionada.getLogoDocumentoElectronico() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(empresaSeleccionada.getLogoDocumentoElectronico(), empresaSeleccionada.getNombreImagenLogoDocumentoElectronico());
            } catch (Exception ex) {

            }
        }
    }

    private Boolean verResumenComponente;


    public String login() {
        ExternalContext ctx;
        if(FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null){
            ctx = FacesContext.getCurrentInstance().getExternalContext();
        }else{
            ctx = getCtx();
        };
        HttpSession hSession = null;

        try {
            this.setUsuario(loginEJB.login(username));

            System.err.println("***********getUsuario().getClave()6" + getUsuario().getClave());
            logger.trace("***********getUsuario().getClave()6" + getUsuario().getClave());
            if (getUsuario() != null && (getUsuario().getClave().equals(password) || accesoViaToken.equals(true))) // estaba usuario....
            {
                setAccesoViaToken(false);
                logger.trace("***********getUsuario().getClave()7" + getUsuario().getBswPersonas());
//                Descomentar esta para activar registros de auditoria
                session = loginEJB.getSessionActiva(usuario);
                if (session != null) {
                    muestraDialogCierreCesion = true;
                }

                setEmpresaSeleccionada(getUsuario().getBswSucursales().getBswEmpresas()); // pone en la sesion la empresa por defecto del usuario
                setSucursal(getUsuario().getBswSucursales());
                hSession = (HttpSession) ctx.getSession(false);
                hSession.setAttribute(ApplicationConstant.USERNAME, getUsuario().getCodUsuario());
                hSession.setAttribute("user", getUsuario().getId());
                hSession.setAttribute("ipCliente", getIpCliente());
                hSession.setAttribute("idSession", hSession.getId());
                registrarSession(hSession, getUsuario());
                setUserRol(accesosGruposEJB.obtieneUsuarioRol(usuario, sucursalLogueada));                
                verResumenComponente = false;
                
                cargaPreferencias();

                redireccion = getRedireccion();
                System.err.println("redireccion: " + redireccion);
                if (redireccion != null && !redireccion.isEmpty()) {
                    ctx.redirect(redireccion);
                }
                return "inicio";
            } else {
                this.username = "";
                facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o password incorrectoss", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                return "login"; //return "login" ;
            }
        } catch (Exception ex) {
            if (hSession != null) {
                hSession.invalidate();
            }
            this.username = "";
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o password incorrectos", ex.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            logger.info("Error al intentar ingresar a la aplicacion", ex);
            return "login";
        }
    }

    public void cargaPreferencias() {
        boolean cargoComponentTheme = false, cargoMenuMode = false, cargoDarkMode = false, cargoTopbarTheme = false,
                cargoMenuTheme = false, cargoInputStyle = false;

        // primero intenta obtener propiedades desde el usuario
        if (usuario.getComponentTheme() != null && !usuario.getComponentTheme().isEmpty()) {
            setComponentTheme(usuario.getComponentTheme());
            cargoComponentTheme = true;
        }
        if (usuario.getDarkMode() != null && !usuario.getDarkMode().isEmpty()) {
            setDarkMode(usuario.getDarkMode());
            cargoDarkMode = true;
        }
        if (usuario.getMenuMode() != null && !usuario.getMenuMode().isEmpty()) {
            setMenuMode(usuario.getMenuMode());
            cargoMenuMode = true;
        }
        if (usuario.getTopbarTheme() != null && !usuario.getTopbarTheme().isEmpty()) {
            setTopbarTheme(usuario.getTopbarTheme());
            cargoTopbarTheme = true;
        }
        if (usuario.getMenuTheme() != null && !usuario.getMenuTheme().isEmpty()) {
            setMenuTheme(usuario.getMenuTheme());
            cargoMenuTheme = true;
        }
        if (usuario.getInputStyle() != null && !usuario.getInputStyle().isEmpty()) {
            setInputStyle(usuario.getInputStyle());
            cargoTopbarTheme = true;
        }

        // si no encuentra la propiedad en el usuario, busca en la empresa
//        boolean cargoComponentTheme = false, cargoMenuMode = false, cargoDarkMode = false, cargoTopbarTheme = false,
//                cargoMenuTheme = false, cargoInputStyle = false;
        if (empresaSeleccionada.getComponentTheme() != null && !empresaSeleccionada.getComponentTheme().isEmpty() && !cargoComponentTheme) {
            setComponentTheme(empresaSeleccionada.getComponentTheme());
            cargoComponentTheme = true;
        }
        if (empresaSeleccionada.getMenuMode() != null && !empresaSeleccionada.getMenuMode().isEmpty() && !cargoMenuMode) {
            setMenuMode(empresaSeleccionada.getMenuMode());
            cargoMenuMode = true;
        }
        if (empresaSeleccionada.getDarkMode() != null && !empresaSeleccionada.getDarkMode().isEmpty() && !cargoDarkMode) {
            setDarkMode(empresaSeleccionada.getDarkMode());
            cargoDarkMode = true;
        }
        if (empresaSeleccionada.getTopbarTheme() != null && !empresaSeleccionada.getTopbarTheme().isEmpty() && !cargoTopbarTheme) {
            setTopbarTheme(empresaSeleccionada.getTopbarTheme());
            cargoTopbarTheme = true;
        }
        if (empresaSeleccionada.getMenuTheme() != null && !empresaSeleccionada.getMenuTheme().isEmpty() && !cargoMenuTheme) {
            setMenuTheme(empresaSeleccionada.getMenuTheme());
            cargoMenuTheme = true;
        }
        if (empresaSeleccionada.getInputStyle() != null && !empresaSeleccionada.getInputStyle().isEmpty() && !cargoInputStyle) {
            setInputStyle(empresaSeleccionada.getInputStyle());
            cargoInputStyle = true;
        }

    }

    /**
     * @return the usuario
     */
    public BswUsuarios getUsuario() //@Produces @Logeado esto hace q actue como un singleton y no debe ser asi
    {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(BswUsuarios usuario) {
        this.usuario = usuario;
        if (usuario != null && usuario.getFoto() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(usuario.getFoto(), usuario.getNombreImagen());
            } catch (Exception ex) {

            }
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;

    }

    public List<BswEmpresas> getEmpresasList() throws Exception {
        empresasList = new ArrayList<BswEmpresas>();
        obtieneListEmpresas();
        return empresasList;
    }

    public void obtieneListEmpresas() {
        HashMap campos = new HashMap();
        if (!getUsuario().isSuperUsuario()) {
            if (getEmpresaSeleccionada().getBswGrupoEmpresas() != null && getUsuario().getIndVerificaMenu() != null && getUsuario().getIndVerificaMenu().equals("S")) {
                campos.put("WHERE", "1=1 and (b.bswGrupoEmpresas.id=" + getEmpresaSeleccionada().getBswGrupoEmpresas().getId() + ")");
            } else {
                campos.put("WHERE", "1=1 and (b.id=" + getEmpresaSeleccionada().getId() + ")");
            }
        }
        getGenericQuery().setEm(em);

        empresasList = getGenericQuery().getSearchResultList(campos, BswEmpresas.class);

    }

    public void setEmpresasList(List<BswEmpresas> empresasList) {
        this.empresasList = empresasList;
    }

    public List<String> getEmpresasDescripcion() {
        try {
            empresasDescripcion = new ArrayList<String>(); // para evitar q se carguen valores repetidos
            for (BswEmpresas empresaActual : getEmpresasList()) {
                empresasDescripcion.add(empresaActual.getDescripcion());
            }
            return empresasDescripcion;
        } catch (Exception ex) {
            logger.error("Error al intentar cargar datos.", ex);
            return null;
        }
    }

    public void setEmpresasDescripcion(List<String> empresasDescripcion) {
        this.empresasDescripcion = empresasDescripcion;
    }

    public String getDescripcionSeleccionada() {
        return descripcionSeleccionada;
    }

    public void setDescripcionSeleccionada(String descripcionSeleccionada) {
        try {
            this.descripcionSeleccionada = descripcionSeleccionada;
        } catch (Exception ex) {
            logger.error("Error al intentar cargar los datos.", ex);
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    BaseLecturaArchivos labels = new BaseLecturaArchivos();

    public String getTimeOut() {
        try {
//            labels.setNombreArchivo(ApplicationConstant.ARCHIVO_PARAMETROS_GENERALES);
            labels.setNombreArchivo(ApplicationConfigPing.getArchivoParametrosGenerales());
            return labels.getPropiedad(ApplicationConstant.SESSIONTIMEOUT);
        } catch (Exception e) {
            return "3500";
        }

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

//<editor-fold defaultstate="collapsed" desc="Ultimos sitios visitados">
    /**
     * @return the ultimosSitiosVisitados
     */
    public List<UltimosSitiosVisitados> getUltimosSitiosVisitados() {
        if (ultimosSitiosVisitados == null) {
            ultimosSitiosVisitados = new ArrayList<UltimosSitiosVisitados>();
        }
        return ultimosSitiosVisitados;
    }

    /**
     * @param ultimosSitiosVisitados the ultimosSitiosVisitados to set
     */
    public void setUltimosSitiosVisitados(List<UltimosSitiosVisitados> ultimosSitiosVisitados) {
        this.ultimosSitiosVisitados = ultimosSitiosVisitados;
    }

    public void addUltimoSitio(UltimosSitiosVisitados ultimoSitio) {
        if (ultimoSitio != null && ultimoSitio.getNombre() != null && !ultimoSitio.getNombre().isEmpty() && !getUltimosSitiosVisitados().contains(ultimoSitio) && permiteAgregar(ultimoSitio.getLink())) {

            getUltimosSitiosVisitados().add(0, ultimoSitio);
            if (getUltimosSitiosVisitados().size() > 5) {
                getUltimosSitiosVisitados().remove(5);
            }
        }
    }

    public boolean permiteAgregar(String link) {
        return link != null && (!link.contains("inicioAdministracionBase.xhtml") && !link.contains("inicioStock.xhtml")
                && !link.contains("inicioVentas.xhtml") && !link.contains("inicioCuentasCobrar.xhtml"));

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Mensajes Usuarios">
    private boolean esLeerMensaje;
    private boolean poseeMensajes;

    /**
     * @return the cantidadMensajeUsuario
     */
    public String getCantidadMensajeUsuario() {

        return cantidadMensajeUsuario;
    }

    /**
     * @param cantidadMensajeUsuario the cantidadMensajeUsuario to set
     */
    public void setCantidadMensajeUsuario(String cantidadMensajeUsuario) {
        this.cantidadMensajeUsuario = cantidadMensajeUsuario;
    }

    

    public void leerMensaje() {

    }


    /**
     * @return the esLeerMensaje
     */
    public boolean isEsLeerMensaje() {
        return esLeerMensaje;
    }

    /**
     * @param esLeerMensaje the esLeerMensaje to set
     */
    public void setEsLeerMensaje(boolean esLeerMensaje) {
        this.esLeerMensaje = esLeerMensaje;
    }

    /**
     * @return the poseeMensajes
     */
    public boolean isPoseeMensajes() {
        return poseeMensajes;
    }

    /**
     * @param poseeMensajes the poseeMensajes to set
     */
    public void setPoseeMensajes(boolean poseeMensajes) {
        this.poseeMensajes = poseeMensajes;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sucursales List">
    /**
     * @return the sucursalesList
     */
    public List<BswSucursales> getSucursalesList() {
        //  if (sucursalesList == null) {

        try {
            sucursalesList = em.createNamedQuery("BswSucursales.findAllByEmpresa").setParameter("emp", empresaSeleccionada).getResultList();
        } catch (Exception e) {
            logger.error("Error al listar sucursales", e);
            setSucursalesList(new ArrayList<BswSucursales>());
        }
        //     }
        return sucursalesList;
    }

    public BswSucursales getSucursalesEmpresa(BswEmpresas emp) {
        try {
            sucursalesList = em.createNamedQuery("BswSucursales.findOneByEmpresa").setParameter("emp", emp).setMaxResults(1).getResultList();

            return sucursalesList.get(0);

        } catch (Exception e) {
            logger.error("Error al listar sucursales", e);

        }
        return null;
    }

    public BswGruposUsuarios getGruposUsuariosEmpresa(BswEmpresas emp) {
        try {
            gruposUsuariosList = em.createNamedQuery("BswGruposUsuarios.findByBswEmpresas").setParameter("idEmpresa", emp.getId()).setMaxResults(1).getResultList();

            return gruposUsuariosList.get(0);

        } catch (Exception e) {
            logger.error("Error al listar GruposUsuarios", e);
        }
        return null;
    }

    public BswAccesosGrupos getAccesosGruposEmpresa(BswEmpresas emp) {
        try {
            accesosGruposList = em.createNamedQuery("BswAccesosGrupos.findByBswEmpresas").setParameter("idEmpresa", emp.getId()).setMaxResults(1).getResultList();

            return accesosGruposList.get(0);

        } catch (Exception e) {
            logger.error("Error al listar AccesosGrupos", e);

        }
        return null;
    }

    public List<BswAccesosGrupos> getAccesosGruposList() {
        return accesosGruposList;
    }

    public void setAccesosGruposList(List<BswAccesosGrupos> accesosGruposList) {
        this.accesosGruposList = accesosGruposList;
    }

    public List<BswGruposUsuarios> getGruposUsuariosList() {
        return gruposUsuariosList;
    }

    public void setGruposUsuariosList(List<BswGruposUsuarios> gruposUsuariosList) {
        this.gruposUsuariosList = gruposUsuariosList;
    }

    /**
     * @param sucursalesList the sucursalesList to set
     */
    public void setSucursalesList(List<BswSucursales> sucursalesList) {
        this.sucursalesList = sucursalesList;
    }

    //</editor-fold>
    public static void removeViewScopedBean() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public String submit() {
        return "";
    }

    public void actualizarPresupuesto() {

    }

    //<editor-fold defaultstate="collapsed" desc="Cuenta Usuario">
    private String email;
    private Boolean recibeEmail;

    /**
     * @return the email
     */
    public String getEmail() {
        if (email == null && email.isEmpty()) {
            email = getUsuario().getBswPersonas().getDirecElectronica();
        }
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the recibeEmail
     */
    public boolean isRecibeEmail() {
        if (recibeEmail == null) {
            recibeEmail = usuario.isRecibeEmail();
        }
        return recibeEmail;
    }

    /**
     * @param recibeEmail the recibeEmail to set
     */
    public void setRecibeEmail(boolean recibeEmail) {
        this.recibeEmail = recibeEmail;
    }

    public void guardarConfig() {
        //usuario.setRecibeEmail(recibeEmail);
        //usuario.getBswPersonas().setDirecElectronica(email);
        loginEJB.guardarConfiguracion(usuario);
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Ip cliente">
    public String getIpCliente() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;

    }

    public void addRemoteInfo(BswSession session) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        session.setIpCliente(ipAddress);
        session.setHostName(request.getRemoteHost());

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Session">
    private boolean muestraDialogCierreCesion;

    private void registrarSession(HttpSession hSession, BswUsuarios usuario) throws Exception {
        BswSession session = new BswSession();
        session.setIdSession(hSession.getId());
//        session.setIpCliente(getIpCliente());
        addRemoteInfo(session);
        session.setFechaLogin(new Date());
        if (usuario != null) {
            session.setIdUser(usuario.getId());
            session.setCodUser(usuario.getCodUsuario());
            if (usuario.getBswPersonas() != null) {
                session.setNombreUser(usuario.getBswPersonas().getNombre());
            }
        }
        genericoEJB.insertar(session);

    }

    public BswSession getSession() {
        return session;
    }

    public void setSession(BswSession session) {
        this.session = session;
    }

    public void cerrarCesionesUsuario() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession hSession = (HttpSession) ctx.getSession(false);
        loginEJB.cerrarSessionesUsuario(getUsuario(), hSession);
        muestraDialogCierreCesion = false;
    }

    public boolean isMuestraDialogCierreCesion() {
        return muestraDialogCierreCesion;
    }

    public void setMuestraDialogCierreCesion(boolean muestraDialogCierreCesion) {
        this.muestraDialogCierreCesion = muestraDialogCierreCesion;
    }

    public void cancelLogin() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();

        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            HttpSession hSession = ((HttpSession) ctx.getSession(false));
            loginEJB.actualizarSession(hSession);
            hSession.invalidate();
            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/py.com.ping.administracionBase/login.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//</editor-fold>

    /**
     * @return the userRol
     */
    public BswRolUsuarioSucursal getUserRol() {
        return userRol;
    }

    /**
     * @param userRol the userRol to set
     */
    public void setUserRol(BswRolUsuarioSucursal userRol) {
        this.userRol = userRol;
    }

    //<editor-fold defaultstate="collapsed" desc="Preferencias de usuario">
    private String menuMode = "layout-sidebar";
    private String darkMode = "light";
    private String componentTheme = "purple";
    private String topbarTheme = "light";
    private String menuTheme = "light";
    private String inputStyle = "outlined";
    private boolean lightLogo = false;

    public String getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public String getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
        this.menuTheme = darkMode;
        this.topbarTheme = darkMode;
        this.lightLogo = !this.topbarTheme.equals("light");
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public String getTopbarTheme() {
        return topbarTheme;
    }

    public void setTopbarTheme(String topbarTheme) {
        this.topbarTheme = topbarTheme;
        this.lightLogo = !this.topbarTheme.equals("light");
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public String getInputStyleClass() {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
    }

    public String getLayout() {
        return "layout-" + this.darkMode;
    }

    public String getTheme() {
        return this.componentTheme + '-' + this.darkMode;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public boolean isLightLogo() {
        return lightLogo;
    }

    public void setLightLogo(boolean lightLogo) {
        this.lightLogo = lightLogo;
    }

    //</editor-fold>

//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mensajes">
    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
//</editor-fold>

    public String getRedireccion() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return (String) request.getSession(false).getAttribute("redireccion");
    }


    ExternalContext ctx;

    public ExternalContext getCtx() {
        return ctx;
    }

    public void setCtx(ExternalContext ctx) {
        this.ctx = ctx;
    }

    public Boolean getAccesoViaToken() {
        return accesoViaToken;
    }

    public void setAccesoViaToken(Boolean accesoViaToken) {
        this.accesoViaToken = accesoViaToken;
    }
}
