/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswPaises;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

/**
 *
 * @author Ariel Talavera
 */
@Named
@ViewScoped
public class BswPaisControlador extends AbstractControllerGenerico<BswPaises> {

    private static final Logger log =
            LoggerFactory.getLogger(BswPaisControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswPaises.class;
        this.formName = ApplicationConstant.nombreFormaPaisBase;
        this.paginaActual = "BswPaises.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSPAIS");
            getAcercaDe().add("- Controlador: BswPaisControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswPaises.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Modulos.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }

    }

    public LazyDataModel getListaTodosActualLazy() {
        return getComponentes().getLazyListPais();
    }

}
