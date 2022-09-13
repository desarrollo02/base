/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.ping.utilitarios.jpa.Entidad;

/**
 *
 * @author gdb
 */
@Named
@ViewScoped
public class UtilControler extends AbstractControllerGenerico<Entidad> {

    @Override
    public void setParametros() {
    }
    
}
