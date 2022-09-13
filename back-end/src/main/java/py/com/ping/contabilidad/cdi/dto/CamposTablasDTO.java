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
public class CamposTablasDTO implements Serializable{
    private Long id;
    private String column_name;
    private String data_type;
    private String is_nullable;
    public CamposTablasDTO(){
    }
    public CamposTablasDTO(Object[] o){
        this.column_name=(String) o[0];
        this.data_type=(String) o[1];
        this.is_nullable=(String) o[2];
        
    }
    
    
   

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getIs_nullable() {
        return is_nullable;
    }

    public void setIs_nullable(String is_nullable) {
        this.is_nullable = is_nullable;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
