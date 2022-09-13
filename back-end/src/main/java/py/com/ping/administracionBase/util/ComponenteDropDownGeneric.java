/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import java.util.HashMap;
import java.util.List;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import py.com.ping.utilitarios.interfaces.GenericQuery;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
public class ComponenteDropDownGeneric<T> {
    private T objeto;
    private ConverterGeneric<T> objetoConverter=new ConverterGeneric<T>();
  
    private GenericQuery genericQuery=new GenericQueryImpl();
   
    public Converter getObjetoConverter(Class<T> clase,EntityManager em, String claveBusqueda){
        objetoConverter.setClase(clase);
        objetoConverter.setEm(em);
        objetoConverter.setClaveBusqueda(claveBusqueda);
        
        return objetoConverter;
    }
    
    
    /**
     * @return the objeto
     */
    public T getObjeto() {
        if(objeto==null)
            objeto=(T) new Object();
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
    
     public List<T> getObjetoList(Class<T> clase, HashMap campos, EntityManager em) {
        if(campos==null)
            campos=new HashMap();
        genericQuery.setEm(em);
        return genericQuery.getSearchResultList(campos, clase);
    }

    
}
