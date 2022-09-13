/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.omnifaces.util.Faces.getExternalContext;
import org.primefaces.model.menu.DefaultMenuItem;
import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.ejb.BswAccesosGruposEJB;
import py.com.ping.administracionBase.ejb.LoginEJB;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;

@Named
@SessionScoped
public class MenuControlador extends AbstractControllerGenerico<BswModulos> implements Serializable {

    private @Inject
    LoginControlador loginControlador;
    private MenuModel modulos;
    List<BswModulos> listaModulos;
    private String moduloActual;
    List<BswEmpresas> listaEmpresas;
    BswEmpresas empresaSeleccionada;
    @PersistenceContext
    protected EntityManager em;
    private String userNombre;
    private HttpSession hSession;
    
    @EJB LoginEJB loginEJB;

    public MenuControlador() {
        super();
        listaEmpresas = new ArrayList<BswEmpresas>();
        moduloActual = "Pagina de Inicio";
    }

    public void logout() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();

        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            hSession = ((HttpSession) ctx.getSession(false));
            loginEJB.actualizarSession(hSession);
            hSession.invalidate();
            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/administracionBase/login.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setModulos(MenuModel modulos) {
        this.modulos = modulos;
    }

    private String getUrl(String codMod) {
        String url = "#";
        if (codMod.equalsIgnoreCase("BS")) {
            url = getPathHome() + "faces/administracionBase/inicioAdministracionBase.xhtml";
        } else if (codMod.equalsIgnoreCase("FV")) {
            //url = getPathHome()+"faces/fuerzaVentas/inicioFuerzaVentas.xhtml";
        } else if (codMod.equalsIgnoreCase("RP")) {
            url = getPathHome() + "faces/gestionDistribucion/inicioGestionDistribucion.xhtml";
        } else if (codMod.equalsIgnoreCase("AF")) {
            url = getPathHome() + "faces/activoFijo/inicioActivoFijo.xhtml";
        }
        if (codMod.equalsIgnoreCase("VT")) {
            url = getPathHome() + "faces/ventas/inicioVentas.xhtml";
        }
        if (codMod.equalsIgnoreCase("ST")) {
            url = getPathHome() + "faces/controlStock/inicioStock.xhtml";
        }
        if (codMod.equalsIgnoreCase("CC")) {
            url = getPathHome() + "faces/cuentasCobrar/inicioCuentasCobrar.xhtml";
        }
        if (codMod.equalsIgnoreCase("TS")) {
            url = getPathHome() + "faces/tesoreria/inicioTesoreria.xhtml";
        }
        if (codMod.equalsIgnoreCase("CM")) {
            url = getPathHome() + "faces/importaciones/inicioImportaciones.xhtml";
        }
        if (codMod.equalsIgnoreCase("CO")) {
            url = getPathHome() + "faces/contabilidad/inicioContabilidad.xhtml";
        }
        if (codMod.equalsIgnoreCase("CP")) {
            url = getPathHome() + "faces/cuentasPagar/inicioCuentasPagar.xhtml";
        }
        if (codMod.equalsIgnoreCase("CR")) {
            url = getPathHome() + "faces/importacionesRef/inicioImportacionesRef.xhtml";
        }

        if (codMod.equalsIgnoreCase("RH")) {
            url = getPathHome() + "faces/recursosHumanos/inicioRecursosHumanos.xhtml";
        }

        if (codMod.equalsIgnoreCase("SM")) {
            url = getPathHome() + "faces/serviciosMecanicos/inicioServiciosMecanicos.xhtml";
        }

        return url;
    }

    public String getPathHome() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestScheme() + "://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName() + ":" + FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort()
                + //FacesContext.getCurrentInstance().getExternalContext().getRequestServerName() + ":8081" +
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/";
    }

    public String getImagen() {
        return getPathHome() + "faces/recursos/img/logo2.png";
    }
    @EJB
    private BswAccesosGruposEJB accesosGruposEJB;

    public List<BswModulos> getLista() {
        List<BswModulos> modulosAutorizados = new ArrayList<BswModulos>();
        List<BswModulos> modulosTodos = new ArrayList<BswModulos>();
        Query query = em.createNativeQuery("select * from bsw_modulos order by id", BswModulos.class);
        modulosTodos = (List<BswModulos>) query.getResultList();//super.genericEJB.getTodos(clase);
        for (BswModulos moduloActual : modulosTodos) {
            if (accesosGruposEJB.poseeModulo(moduloActual.getCodModulo())) {
                modulosAutorizados.add(moduloActual);
            }
        }

        return modulosAutorizados;
    }

    public MenuModel getModulos() {

        listaModulos = getLista();
        modulos = new DefaultMenuModel();

        for (BswModulos moduloActual : listaModulos) {
            if (moduloActual.getCodModulo().equals("CC") || moduloActual.getCodModulo().equals("VT") || moduloActual.getCodModulo().equals("FVW")
                    || moduloActual.getCodModulo().equals("BS") || moduloActual.getCodModulo().equals("ST")
                    || moduloActual.getCodModulo().equals("TS")) {
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue(moduloActual.getDescripcion());
                item.setUrl(getUrl(moduloActual.getCodModulo()));
                item.setParam("codModulo", moduloActual.getCodModulo());
                modulos.getElements().add(item);
            }
        }

        return modulos;
    }

    public BswEmpresas getEmpresaSeleccionada() throws InstantiationException, IllegalAccessException {
        if (empresaSeleccionada == null) {
            empresaSeleccionada = BswEmpresas.class.newInstance();
        }
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(BswEmpresas empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
        if (empresaSeleccionada != null) {
            setEmpresaLogueada(empresaSeleccionada); // setea en el controlador del login la suc. seleccionada
        }
    }

    public List<BswEmpresas> getListaEmpresas() {
        Query query = em.createNamedQuery("BswEmpresas.findAll");
        listaEmpresas = new ArrayList<BswEmpresas>();
        listaEmpresas = (List<BswEmpresas>) query.getResultList();
        return listaEmpresas;
    }

    public void setListaEmpresas(List<BswEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    @Override
    public void setParametros() {
        this.formName = "BSMENU";
        this.clase = BswModulos.class;
    }

    /**
     * @return the moduloActual
     */
    public String getModuloActual() {
        try {
            HttpServletRequest paramMod = (HttpServletRequest) getExternalContext().getRequest();
            String codModulo = (String) paramMod.getAttribute("codModulo");
            if (codModulo != null && codModulo.equals("BS")) {
                moduloActual = "Sistema Base";
            }
        } catch (Exception e) {
            log.error("Error al obtener parametro", e);
        }
        return moduloActual;
    }

    /**
     * @param moduloActual the moduloActual to set
     */
    public void setModuloActual(String moduloActual) {
        this.moduloActual = moduloActual;
    }
    
    
    public BswGruposUsuarios getGrupoUsuario(){
       return loginControlador.getUserRol()!=null?loginControlador.getUserRol().getBswGruposUsuarios():null;
    }
}
