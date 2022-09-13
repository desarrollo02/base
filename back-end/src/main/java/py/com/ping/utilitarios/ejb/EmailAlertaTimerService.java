/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.ejb;

import javax.ejb.Singleton;

/**
 *
 * @author Rudy
 */

@Singleton
public class EmailAlertaTimerService {
    
  //  @EJB AlertasEjb alertasEjb;
    
//     @Schedule(second = "0", minute = "0", hour = "19", persistent = false)
    public synchronized void enviarMensajes() {
    //    alertasEjb.envioAutomaticoAlertas();
    }
}
