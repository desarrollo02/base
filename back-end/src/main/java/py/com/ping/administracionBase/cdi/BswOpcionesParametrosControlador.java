/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import py.com.ping.administracionBase.jpa.BswOpcionesParametros;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

/**
 *
 * @author gdb
 */
@Named
@ViewScoped
public class BswOpcionesParametrosControlador extends AbstractControllerGenerico<BswOpcionesParametros>{
    private static final Logger log =
            LoggerFactory.getLogger(BswOpcionesParametrosControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswOpcionesParametros.class;
        this.formName = ApplicationConstant.nombreFormaOpcionParamBase;
        this.paginaActual = "BswOpcionesParametros.xhtml";

        if(acercaDe.isEmpty()){
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSPERMIS");
            getAcercaDe().add("- Controlador: BswOpcionesParametrosControlador.java");
            getAcercaDe().add("- EJB: BswPermisosOpcionesEJB.java");
            getAcercaDe().add("- JPA: BswPermisosOpciones.java");
        }

        if(ayuda.isEmpty()){
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Permisos especiales por Usuario.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, seleccionar en la grilla con un click, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, seleccionar en la grilla con un click, luego presionar eliminar.");
        }
    }
       
    
    @Override
    public void inicializaVariablesLocales() {
       
    }

    @Override
    protected void antesABM() {
        actual.setCodUsuarioAud(getUserNombre());
        
        if (getComponentes().getBswFormasABM().getId() != null) {
            this.actual.setBswFormas(getComponentes().getBswFormasABM());
        }else{
                this.actual.setBswFormas(null);
        
        }
       
     
    }

    @Override
    public void cargarCamposRelacionados() {
       getComponentes().setBswFormasABM(actual.getBswFormas());
    }

    @Override
    public void limpiarDatosLocales() {
       getComponentes().setBswFormasABM(null);
      
       
    }
}
