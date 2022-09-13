/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswCiudades;
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
public class BswCiudadesControlador extends AbstractControllerGenerico<BswCiudades> {

    private static final Logger log =
            LoggerFactory.getLogger(BswPaisControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswCiudades.class;
        this.formName = ApplicationConstant.nombreFormaCiudadBase;
        this.paginaActual = "BswCiudades.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSCIUDAD");
            getAcercaDe().add("- Controlador: BswCiudadesControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswCiudades.java");
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
    }

    public LazyDataModel getListaTodosActualLazy() {
        return getComponentes().getLazyListCiudad();
    }

    @Override
    protected void antesABM() {
        if(getComponentes().getBswPaisesABM() != null && 
                getComponentes().getBswPaisesABM().getId() != null){
                this.actual.setBswPaises(getComponentes().getBswPaisesABM());
        }
        if(getComponentes().getBswProvinciasABM() != null && 
                getComponentes().getBswProvinciasABM().getId() != null){
          this.actual.setBswProvincias(getComponentes().getBswProvinciasABM());
        }
        getComponentes().setBswProvinciasABM(new BswProvincias());
        getComponentes().setBswPaisesABM(new BswPaises());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswProvinciasABM(getActual().getBswProvincias());
        getComponentes().setBswPaisesABM(getActual().getBswPaises());
    }

    @Override
    public void limpiar() {
        actual = new BswCiudades();
        getComponentes().setBswPaisesABM(new BswPaises());
        getComponentes().setBswProvinciasABM(new BswProvincias());
        limpiaTabla();
    }
    
      @Override
    public void cargarDatosNuevoObjeto() {
        getComponentes().setBswCiudadesABM(getObjetoAgregado());
    }
    
    
}
