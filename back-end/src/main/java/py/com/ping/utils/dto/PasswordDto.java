/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utils.dto;

/**
 *
 * @author Rudy
 */
public class PasswordDto {
    private boolean valido;
    private String msg;

    public PasswordDto() {
    }
    

    public PasswordDto(boolean valido, String msg) {
        this.valido = valido;
        this.msg = msg;
    }
    

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
