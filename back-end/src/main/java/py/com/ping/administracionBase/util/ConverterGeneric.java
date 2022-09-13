/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.utilitarios.interfaces.GenericQuery;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.PersistenceContext;

/**
 * @author gdb <dominbla@gmail.com>
 */
public class ConverterGeneric<T> implements Serializable, Converter {
    private static final Logger logger =
            LoggerFactory.getLogger(ConverterGeneric.class);
    
    @PersistenceContext
    private EntityManager em;
    private HashMap campos = new HashMap();

    private GenericQuery genericQuery = new GenericQueryImpl();
    private boolean noLimpiaCampos;
    private Class<T> clase;
    private String claveBusqueda;

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the campos
     */
    public HashMap getCampos() {
        if (campos == null)
            campos = new HashMap();
        return campos;
    }

    /**
     * @param campos the campos to set
     */
    public void setCampos(HashMap campos) {
        this.campos = campos;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("") || value.equals("null")) {
            return null;
        }
        try {
            genericQuery.setEm(em);
            if (!noLimpiaCampos) {
                getCampos().clear();
                getCampos().put("WHERE", "UPPER(b." + claveBusqueda + ")='" + value.toUpperCase() + "'");
            }
            T a = (T) genericQuery.getSearchSingleResult(campos, getClase(), null);
            return a;
        } catch (Exception e) {
            // mensajeAlerta("Codigo introducido no existe");
            logger.error("Error al recuperar objeto", e);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("") || value.equals("null")) {
            return "";
        } else {
            try {

                Method getter = (value).getClass().getMethod("get" + String.valueOf(claveBusqueda.charAt(0)).toUpperCase() + claveBusqueda.substring(1));

                return (String) getter.invoke(value, new Object[0]);
            } catch (Exception ex) {
                logger.error("Error al getAsString", ex);
            }

            return "";
        }
    }


    public List<T> completeCodProfesion(String query) {
        List<T> lista = new ArrayList<T>();
        try {

            genericQuery.setEm(em);
            getCampos().put("-1.operador:" + claveBusqueda + ": like ", "\'" + query.toUpperCase() + "%\'");
            genericQuery.setCantidadReg(30);
            //                    getCampos().put(claveBusqueda, query);
            lista = genericQuery.getSearchResultList(campos, getClase());
                            
                            /*
				lista = em.createNativeQuery(
						"select * from SICAP.NIVEL where NIVEL.IDEJERCICIO=:idEjercicio " +
						" and  NIVEL.CODNIVEL like '"+query+"%' and rownum <=50 "
						,CbNivel.class).setParameter("idEjercicio",ejercicio.getIdEjercicio()).getResultList();
				*/

        } catch (Exception ex) {
            // mensajeAlerta("Codigo introducido no existe");
            logger.error("Error al recuperar objeto", ex);
        }

        return lista;
    }


    /**
     * @return the claveBusqueda
     */
    public String getClaveBusqueda() {
        return claveBusqueda;
    }

    /**
     * @param claveBusqueda the claveBusqueda to set
     */
    public void setClaveBusqueda(String claveBusqueda) {
        this.claveBusqueda = claveBusqueda;
    }

    /**
     * @return the clase
     */
    public Class<T> getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(Class<T> clase) {
        this.clase = clase;
    }

}
