package py.com.ping.utilitarios.interfaces;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;

import javax.persistence.EntityManager;

/**
 * @author Guillermo Dominguez
 */
@Local
public interface GenericQuery {

    public void setEm(EntityManager em);

    public void setCantidadReg(Integer n);

    public void setRegistroInicial(Integer registroInicial);

    public <T> List getSearchResultList(Map<String, Object> campos, Class<T> clase);

    public <T> Object getSearchSingleResult(Map<String, Object> campos, Class<T> clase, String paramSelect);
    public <T> List<T> getSearchResultList(Map<String, Object> campos, Class<T> clase, String paramSelect);

    public <T> Long getSearchCount(Map<String, Object> campos, Class<T> clase);

    public EntityManager getEm();
}
