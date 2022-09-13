/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.ejb;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rudy
 */

@Stateless
public class FuncionesEJB {

    @PersistenceContext
    private EntityManager em;

    public String numberToText(BigDecimal monto) {
        String queryString;
        Query query;
        if (monto != null) {

            queryString = "select num_letras(?)";
            query = getEm().createNativeQuery(queryString);
            query.setParameter(1, monto);

            return (String) query.getSingleResult();
        } else {
            return "";
        }

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
