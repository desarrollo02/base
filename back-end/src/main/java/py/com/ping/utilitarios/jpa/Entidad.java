/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.utilitarios.jpa;

/**
 *
 * @author inv
 */
public interface Entidad {

    public Object getPK();
    public void setPK(Object id);
    public void setUserAuditID(Integer userAuditID);
    public void setIpCliente(String ipCliente);
    public void setIdSession(String idSession);
   
}
