/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.ejb.BswPanelAdministracionEJB;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author gdb
 */
@Named
@ViewScoped
public class BswPanelAdministracionControlador extends AbstractControllerGenerico<BswEmpresas> {
    private static final Logger log =
            LoggerFactory.getLogger(BswPanelAdministracionControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswEmpresas.class;
        this.formName = ApplicationConstant.nombreFormaEmpresaBase;
        this.paginaActual = "BswEmpresas.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSEMPRES");
            getAcercaDe().add("- Controlador: BswEmpresasControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswEmpresas.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Modulos.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }

    }

    @EJB 
    private BswPanelAdministracionEJB administracionEJB;
    
    
     
}
