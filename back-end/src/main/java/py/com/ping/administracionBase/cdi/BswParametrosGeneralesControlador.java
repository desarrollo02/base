/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.jpa.BswParametrosGenerales;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Named
@ViewScoped
public class BswParametrosGeneralesControlador extends AbstractControllerGenerico<BswParametrosGenerales> {
    private static final Logger log =
            LoggerFactory.getLogger(BswParametrosGeneralesControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswParametrosGenerales.class;
        this.formName = ApplicationConstant.nombreFormaPramaetrosBase;
        this.paginaActual = "BswParametroGeneral.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSPARAMETRO");
            getAcercaDe().add("- Controlador: BswParametrosGeneralesControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswParametrosGenerales.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Parametros del Sistema.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, seleccionar en la grilla con un click, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, seleccionar en la grilla con un click, luego presionar eliminar.");
        }
    }
    
    

   
    @Override
    protected void antesABM() {
        getActual().setCodUsuarioAud(getUserNombre());
        if (getComponentes().getBswModulosABM().getId() != null) {
            actual.setBswModulos(getComponentes().getBswModulosABM());
        }
        else{
            actual.setBswModulos(null);
        }
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswModulosABM(getActual().getBswModulos());
    }

    @Override
    public void limpiarDatosLocales() {
        getComponentes().setBswModulosABM(new BswModulos());
        
    }
}
