/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.contabilidad.cdi.dto;

import java.io.Serializable;

/**
 *
 * @author gdb
 */
public class OperadorDTO implements Serializable{
    private String codigo;
    private String valor;

    
    public OperadorDTO(String codigo, String valor){
        this.codigo=codigo;
        this.valor=valor;
    
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
