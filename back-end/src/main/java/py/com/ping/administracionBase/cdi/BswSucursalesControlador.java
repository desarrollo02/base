/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gdb
 */
@Named
@ViewScoped
public class BswSucursalesControlador extends AbstractControllerGenerico<BswSucursales> {
    private static final Logger log =
            LoggerFactory.getLogger(BswSucursales.class);
    @Override
    public void setParametros() {
        this.clase = BswSucursales.class;
        this.formName = ApplicationConstant.nombreFormaSucursalBase;
        this.paginaActual = "BswSucursales.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSSUCURS");
            getAcercaDe().add("- Controlador: BswSucursalesControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswSucursalesEJB.java");
        }

    }

    @Override
    public void inicializaVariablesLocales() {
        getComponentes().setEmpresasAbm(getEmpresaLogueada());
        cargaArbolPrincipal();
    }

    @Override
    protected void antesABM() {
        this.actual.setCodUsuarioAud(getUserNombre());
        this.actual.setBswEmpresas(getEmpresaLogueada());
        if (getComponentes().getEmpresasAbm().getId() != null) {
            this.actual.setBswEmpresas(getComponentes().getEmpresasAbm());
        }
        if (getComponentes().getSucursalABM().getId() != null) {
            this.actual.setBswSucursalPadre(getComponentes().getSucursalABM());
        } else {
            this.actual.setBswSucursalPadre(null);
        }
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setEmpresasAbm(actual.getBswEmpresas());
        getComponentes().setSucursalABM(actual.getBswSucursalPadre());
        //getDepositoABMGeneric().cargaLista(getActualViejo().getBswDepositoList());
    }

    @Override
    public void limpiarDatosLocales() {
        getComponentes().setEmpresasAbm(getEmpresaLogueada());
        getComponentes().setSucursalABM(null);
    }

    /**
     * Obtiene la lista de los registros almacenados del Tipo (Actual)
     *
     * @return List<BswSucursales>
     */
    @Override
    public List<BswSucursales> getListaTodosActual() {
        try {
            getGenericQuery().setEm(getEm());
            camposQuery = new HashMap();
            camposQuery.put("bswEmpresas.id", getEmpresaLogueada().getId());
            return getGenericQuery().getSearchResultList(camposQuery, BswSucursales.class
            );
        } catch (Exception e) {
        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="TswRecibos">

    //</editor-fold>
    @Override
    public void despuesEliminar() {
        cargaArbolPrincipal();
    }

    @Override
    public void despuesInsertar() throws Exception {
        mensajeExito("Los datos fueron guardados correctamente!");
        cargaArbolPrincipal();
    }

    @Override
    public void despuesActualizar() throws Exception {
        actualViejo = null;
        mensajeExito("Los datos fueron actualizados correctamente!");
        cargaArbolPrincipal();
    }

    @Override
    public void antesInsertar() {
        if (esAgregarSubMenu && menuPadreSeleccionado != null) {
            actual.setBswSucursalPadre(menuPadreSeleccionado);
            menuPadreSeleccionado = null;
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Tree Node">
    public void cargaArbolPrincipal() {
        root = new DefaultTreeNode("root", null);
        root.clearParent();
        HashMap camposMenu = new HashMap();
        camposMenu.put("where", "b.bswSucursalPadre is null");
        camposMenu.put("bswEmpresas.id", getEmpresaLogueada().getId());
        camposMenu.put("-1.operador:codSucursal: BY ", " asc");
        getGenericQuery().setEm(getEm());
        //getEm().getEntityManagerFactory().getCache().evict(BswSucursales.class);
        List<BswSucursales> listaMenus = getGenericQuery().getSearchResultList(camposMenu, BswSucursales.class
        );
        if (listaMenus != null) {
            for (BswSucursales menu : listaMenus) {
                TreeNode menus = new DefaultTreeNode(menu, root);
                if (menu.getBswSucursalesHijas() != null && !menu.getBswSucursalesHijas().isEmpty()) {
                    recorreSubMenus(menu, menu.getBswSucursalesHijas(), menus);
                }
            }
        }
    }

    public void recorreSubMenus(BswSucursales menuPadre, List<BswSucursales> menuListHijos, TreeNode nodoPadre) {
        Collections.sort(menuListHijos, (BswSucursales o1, BswSucursales o2) -> o1.getCodigo().compareToIgnoreCase(o2.getCodigo()));
        for (BswSucursales menu : menuListHijos) {

            TreeNode subMmenus = new DefaultTreeNode(menu, nodoPadre);
            if (menu.getBswSucursalesHijas() != null && !menu.getBswSucursalesHijas().isEmpty()) {
                recorreSubMenus(menu, menu.getBswSucursalesHijas(), subMmenus);
            } else {
                //return;
            }
        }
    }

    private TreeNode root;

    /**
     * @return the root
     */
    public TreeNode getRoot() {

        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Add SubMenu">
    private BswSucursales menuPadreSeleccionado;
    private boolean esAgregarSubMenu;

    /**
     * @return the esAgregarSubMenu
     */
    public boolean isEsAgregarSubMenu() {
        return esAgregarSubMenu;
    }

    /**
     * @param esAgregarSubMenu the esAgregarSubMenu to set
     */
    public void setEsAgregarSubMenu(boolean esAgregarSubMenu) {
        this.esAgregarSubMenu = esAgregarSubMenu;
    }

    //</editor-fold>
    public BswSucursales getMenuPadreSeleccionado() {
        if (menuPadreSeleccionado == null) {
            menuPadreSeleccionado = new BswSucursales();
        }
        return menuPadreSeleccionado;
    }

    public void setMenuPadreSeleccionado(BswSucursales menuPadreSeleccionado) {
        this.menuPadreSeleccionado = menuPadreSeleccionado;
    }
}
