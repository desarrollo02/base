/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.cdi;

import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author guillermo dominguez
 *
 */
@Named
@ViewScoped
public class BswModulosControlador extends AbstractControllerGenerico<BswModulos> {
    private static final Logger log =
            LoggerFactory.getLogger(BswModulosControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswModulos.class;
        this.formName = ApplicationConstant.nombreFormaModuolosBase;
        this.paginaActual = "BswModulos.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSMODULO");
            getAcercaDe().add("- Controlador: BswModulosControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswModulos.java");
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
    public boolean isPuedeConsultar() {
        if(getUsuarioLogueado().isSuperUsuario())
            return true;
        return super.isPuedeConsultar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPuedeInsertar() {
        if(getUsuarioLogueado().isSuperUsuario())
            return true;
        return super.isPuedeInsertar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPuedeActualizar() {
         if(getUsuarioLogueado().isSuperUsuario())
            return true;
        return super.isPuedeActualizar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPuedeBorrar() {
        if(getUsuarioLogueado().isSuperUsuario())
            return true;
        return super.isPuedeBorrar(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
    
    @Override
    public List<BswModulos> getListaTodosActual() {
        return getComponentes().obtieneListaBswModulos();
    }

    @Override
    protected void antesABM() {
        this.actual.setCodUsuarioAud(getUserNombre());
        if (actual.getBswEmpresas() == null || actual.getBswEmpresas().getId() == null) {
            this.actual.setBswEmpresas(getEmpresaLogueada());
        }

    }

}
