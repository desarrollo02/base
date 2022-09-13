/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
public class BswFormasControlador extends AbstractControllerGenerico<BswFormas> {

    private static final Logger log =
            LoggerFactory.getLogger(BswFormasControlador.class);

    @Override
    public void setParametros() {
        this.formName = ApplicationConstant.nombreFormaFormasBase;
        this.clase = BswFormas.class;
        this.paginaActual = "BswFormas.xhtml";

        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma: BSFORMAS");
            getAcercaDe().add("- Controlador: BswFormasControlador.java");
            getAcercaDe().add("- EJB: BswFormasEJB.java");
            getAcercaDe().add("- JPA: BswFormas.java");
        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Formas del sistema.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacer un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }
    }

    @Override
    protected void antesABM() {
        getActual().setCodUsuarioAud(getUsuarioLogueado().getCodUsuario());
        if (getComponentes().getBswModulosABM().getId() != null) {
            getActual().setBswModulos(getComponentes().getBswModulosABM());

        }else{
            getActual().setBswModulos(null);
        }
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswModulosABM(getActual().getBswModulos());
    }

    

    @Override
    public void limpiarDatosLocales() {
        getComponentes().setBswModulosABM(null);
    }

}
