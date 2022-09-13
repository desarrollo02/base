/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;
import java.util.HashMap;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswMonedas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Rudy
 */
@Named
@ViewScoped
public class BswMonedasControlador extends AbstractControllerGenerico<BswMonedas> {
    private static final Logger log =
            LoggerFactory.getLogger(BswMonedas.class);
    @Override
    public void setParametros() {
        this.clase = BswMonedas.class;
        this.formName = ApplicationConstant.nombreFormaMonedasBase;
        this.paginaActual = "BswMonedas.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("-Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSMONEDAS");
            getAcercaDe().add("- Controlador: BswMonedasControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswMonedas.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar MOnedas del Sistema.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, seleccionar en la grilla con un click, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, seleccionar en la grilla con un click, luego presionar eliminar.");
        }
    }


    @Override
    protected void antesABM() {
        if (getComponentes().getBswPaisesABM() != null && getComponentes().getBswPaisesABM().getId() != null) {
            getActual().setBswPaises(getComponentes().getBswPaisesABM());
        }
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswPaisesABM(getActual().getBswPaises());
        HashMap camposFiltro = new HashMap();
        camposFiltro.put("bswMonedas.id", getActual().getId());
       
        getGenericQuery().setEm(getEm());
    }

    @Override
    public void limpiar() {
        super.limpiar();
        getComponentes().setLazyListMoneda(null);
        getComponentes().setBswPaisesABM(null);
    }
}
