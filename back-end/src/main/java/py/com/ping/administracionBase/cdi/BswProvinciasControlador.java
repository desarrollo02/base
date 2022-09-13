/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswPaises;
import py.com.ping.administracionBase.jpa.BswProvincias;
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
public class BswProvinciasControlador extends AbstractControllerGenerico<BswProvincias> {
    private static final Logger log =
            LoggerFactory.getLogger(BswProvinciasControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswProvincias.class;
        this.formName = ApplicationConstant.nombreFormaDptoBase;
        this.paginaActual = "BswProvincias.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSPROVIN");
            getAcercaDe().add("- Controlador: BswProvinciasControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswProvincias.java");
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
    }

    public LazyDataModel getListaTodosActualLazy() {
        return getComponentes().getLazyListProvincia();
    }

    @Override
    protected void antesABM() {
        if (getComponentes().getBswPaisesABM() != null
                && getComponentes().getBswPaisesABM().getId() != null) {
            this.actual.setBswPaises(getComponentes().getBswPaisesABM());
        }
        getComponentes().setBswPaisesABM(new BswPaises());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswPaisesABM(getActual().getBswPaises());
    }

    @Override
    public void limpiar() {
        actual = new BswProvincias();
        getComponentes().setBswPaisesABM(new BswPaises());
        limpiaTabla();
    }
}
