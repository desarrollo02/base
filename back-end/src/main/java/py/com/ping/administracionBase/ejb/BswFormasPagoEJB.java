/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class BswFormasPagoEJB {

    @PersistenceContext
    private EntityManager entidadManager;

    
//preguntar
    public Long totalPorFechaModif(Date fecha) {
        Query q;
        if (fecha == null) {
            q = entidadManager.createQuery("Select count(a) FROM BswPersonas a");
        } else {
            q = entidadManager.createQuery("Select count(a) FROM BswPersonas a where a.fechaModificacion > :fecha");
            q.setParameter("fecha", fecha);
        }
        return (Long) q.getSingleResult();
    }

}
