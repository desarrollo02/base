/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.cdi;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
public class UltimosSitiosVisitados implements  Serializable{
    private String nombre;
    private String link;

    public UltimosSitiosVisitados(String nombre, String link){
        this.link=link;
        this.nombre=nombre;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
     @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UltimosSitiosVisitados)) {
            return false;
        }
        UltimosSitiosVisitados other = (UltimosSitiosVisitados) object;
        return (this.nombre != null || other.nombre == null) && (this.nombre == null || this.nombre.equals(other.nombre));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
}
