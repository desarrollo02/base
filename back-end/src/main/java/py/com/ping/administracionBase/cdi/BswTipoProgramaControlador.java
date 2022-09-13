package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswTipoPrograma;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

/**
 *
 * @author gdb
 */
@Named
@ViewScoped

public class BswTipoProgramaControlador extends AbstractControllerGenerico<BswTipoPrograma> {
    private static final Logger log =
            LoggerFactory.getLogger(BswTipoProgramaControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswTipoPrograma.class;
        this.formName = ApplicationConstant.NOMBRE_FORMA_TIPO_PROGRAMA_BASE;
        this.paginaActual = "BswTipoPrograma.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:BswTipoPrograma.xhtml");
            getAcercaDe().add("- Controlador: BswTipoProgramaControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswTipoPrograma.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Tipos de Programa.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }

    }

    @PostConstruct
    public void inicializaValores() {
        this.idGrillaPrincipal = "grilla";
        camposQuery.put("bswEmpresas.id", getEmpresaLogueada().getId());
    }

    @Override
    protected void antesABM() {
        getActual().setBswEmpresas(getEmpresaLogueada());
        getActual().setCodUsuarioAud(getUserNombre());
    }

}
