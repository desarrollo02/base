package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswNivelEstudios;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

@Named
@ViewScoped
public class BswNivelEstudioControlador extends AbstractControllerGenerico<BswNivelEstudios>{
    
    @Override
    public void setParametros() {
        this.clase = BswNivelEstudios.class;
        this.formName = ApplicationConstant.nombreFormaNivelEstudioBase;
        this.paginaActual = "BswNivelEstudio.xhtml";
    }
}
