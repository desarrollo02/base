/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswBarrios;
import py.com.ping.administracionBase.jpa.BswCiudades;
import py.com.ping.administracionBase.jpa.BswPaises;
import py.com.ping.administracionBase.jpa.BswProvincias;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

/**
 *
 * @author Ariel Talavera
 */
@Named
@ViewScoped
public class BswBarriosControlador extends AbstractControllerGenerico<BswBarrios> {

    private static final Logger log =
            LoggerFactory.getLogger(BswModulosControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswBarrios.class;
        this.formName = ApplicationConstant.nombreFormaBarriosBase;
        this.paginaActual = "BswBarrios.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSBARRIO");
            getAcercaDe().add("- Controlador: BswBarriosControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswBarrios.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Modulos.");
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
        getComponentes().setBswPaisesABM(new BswPaises());
        getComponentes().setBswProvinciasABM(new BswProvincias());
        getComponentes().setBswCiudadesABM(new BswCiudades());
    }

    public LazyDataModel getListaTodosActualLazy() {
        return getComponentes().getLazyListBarrio();
    }

    @Override
    protected void antesABM() {
        if (getComponentes().getBswCiudadesABM() != null
                && getComponentes().getBswCiudadesABM().getId() != null) {
            this.actual.setBswCiudades(getComponentes().getBswCiudadesABM());
        }
        if (getComponentes().getBswPaisesABM() != null
                && getComponentes().getBswPaisesABM().getId() != null) {
            this.actual.setBswPaises(getComponentes().getBswPaisesABM());
        }
        if (getComponentes().getBswProvinciasABM() != null
                && getComponentes().getBswProvinciasABM().getId() != null) {
            this.actual.setBswProvincias(getComponentes().getBswProvinciasABM());
        }
        getComponentes().setBswProvinciasABM(new BswProvincias());
        getComponentes().setBswPaisesABM(new BswPaises());
        getComponentes().setBswCiudadesABM(new BswCiudades());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswProvinciasABM(getActual().getBswProvincias());
        getComponentes().setBswPaisesABM(getActual().getBswPaises());
        getComponentes().setBswCiudadesABM(getActual().getBswCiudades());
    }

    @Override
    public void limpiar() {
        actual = new BswBarrios();
        getComponentes().setBswPaisesABM(new BswPaises());
        getComponentes().setBswProvinciasABM(new BswProvincias());
        getComponentes().setBswCiudadesABM(new BswCiudades());
        limpiaTabla();
    }
}
