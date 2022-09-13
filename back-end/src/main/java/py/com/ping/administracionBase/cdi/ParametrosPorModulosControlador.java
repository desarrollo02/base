/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswParametrosGenerales;
import py.com.ping.administracionBase.jpa.ParametrosPorModulos;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author josema
 */
@Named
@ViewScoped
public class ParametrosPorModulosControlador extends AbstractControllerGenerico<BswParametrosGenerales> {
    private static final Logger log =
            LoggerFactory.getLogger(ParametrosPorModulosControlador.class);

    @Override
    public void setParametros() {
        this.clase = ParametrosPorModulos.class;
        this.formName = "";
        this.paginaActual = "";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:BswPersonas.xhtml");
            getAcercaDe().add("- Controlador: BswPersonasControlador.java");
            getAcercaDe().add("- EJB: BswPersonasEJB.java");
            getAcercaDe().add("- JPA: BswPersonas.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Personas.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }
    }

}
