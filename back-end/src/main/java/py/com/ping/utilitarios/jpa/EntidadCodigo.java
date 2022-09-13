/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.utilitarios.jpa;

import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswUsuarios;


/**
 *
 * @author Diaz
 */
public interface EntidadCodigo extends Entidad {

    public String getCodigo();
    public void setCodigo(String codigo);
    public void setEmpresa(BswEmpresas emp);
    public BswEmpresas getEmpresa();
    public void setUsuario(BswUsuarios user);
    public BswUsuarios getUsuario();
}
