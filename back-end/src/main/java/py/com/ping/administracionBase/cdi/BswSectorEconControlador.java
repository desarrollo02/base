package py.com.ping.administracionBase.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswSectoresEcon;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

@Named
@ViewScoped
public class BswSectorEconControlador extends AbstractControllerGenerico<BswSectoresEcon>{
    
    @Override
    public void setParametros() {
        this.clase = BswSectoresEcon.class;
        this.formName = ApplicationConstant.nombreFormaBswSectorEcon;
        this.paginaActual = "BswSectorEcon.xhtml";
    }
}
