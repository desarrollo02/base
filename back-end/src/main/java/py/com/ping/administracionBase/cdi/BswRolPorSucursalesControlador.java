/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal;
import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Ariel Talavera
 */
@Named
@ViewScoped
public class BswRolPorSucursalesControlador extends AbstractControllerGenerico<BswRolUsuarioSucursal> {
    private static final Logger log =
            LoggerFactory.getLogger(BswRolUsuarioSucursal.class);

    @Override
    public void setParametros() {
        this.clase = BswRolUsuarioSucursal.class;
        this.formName = ApplicationConstant.nombreFormaRolSucursal;
        this.paginaActual = "BswRolPorSucursales.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSWROLSUC");
            getAcercaDe().add("- Pantalla:BswRolUsuarioSucursal.xhtml");
            getAcercaDe().add("- Controlador: BswRolUsuarioSucursalControlador.java");
            getAcercaDe().add("- JPA: BswRolUsuarioSucursal.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Roles de Sucursales del sistema.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }

    }

    @Override
    @PostConstruct
    public void InicializaValores() {
        super.InicializaValores(); //To change body of generated methods, choose Tools | Templates.
        getComponentes().setBswGruposUsuariosABM(new BswGruposUsuarios());
        getComponentes().setUsuariosABM(new BswUsuarios());
        getComponentes().setSucursalABM(new BswSucursales());
    }

    public LazyDataModel getListaTodosActualLazy() {
        return getComponentes().getLazyListRolUsuarioSucursal();
    }

    @Override
    protected void antesABM() {
        if (getComponentes().getBswGruposUsuariosABM()!= null
                && getComponentes().getBswGruposUsuariosABM().getId() != null) {
            this.actual.setBswGruposUsuarios(getComponentes().getBswGruposUsuariosABM());
        }
        if (getComponentes().getUsuariosABM()!= null
                && getComponentes().getUsuariosABM().getId() != null) {
            this.actual.setBswUsuarios(getComponentes().getUsuariosABM());
        }
        if (getComponentes().getSucursalABM()!= null
                && getComponentes().getSucursalABM().getId() != null) {
            this.actual.setBswSucursales(getComponentes().getSucursalABM());
        }
        if(getActual().getFirmante() == null){
            getActual().setFirmante(false);
        }
        getComponentes().setBswGruposUsuariosABM(new BswGruposUsuarios());
        getComponentes().setUsuariosABM(new BswUsuarios());
        getComponentes().setSucursalABM(new BswSucursales());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswGruposUsuariosABM(getActual().getBswGruposUsuarios());
        getComponentes().setUsuariosABM(getActual().getBswUsuarios());
        getComponentes().setSucursalABM(getActual().getBswSucursales());
    }

    @Override
    public void limpiar() {
        actual = new BswRolUsuarioSucursal();
        getComponentes().setBswGruposUsuariosABM(new BswGruposUsuarios());
        getComponentes().setUsuariosABM(new BswUsuarios());
        getComponentes().setSucursalABM(new BswSucursales());
        limpiaTabla();
    }
}
