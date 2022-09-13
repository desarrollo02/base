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
import py.com.ping.administracionBase.jpa.BswPersonas;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class BswBancosEJB {

    @PersistenceContext
    private EntityManager entidadManager;

    public void insertar(BswPersonas entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswPersonas entidad) {
        entidadManager.merge(entidad);
    }

    public void eliminar(BswPersonas entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }
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

    public List<BswPersonas> listarPorFechaModif(Date fecha, Integer inicio, Integer cantidad) {
        Query q;
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        q = entidadManager.createQuery("Select a FROM BswPersonas a where a.bswSectoresEcon.id = :idSector");
        q.setParameter("idSector", 10);
        q.setFirstResult(inicio);
        q.setMaxResults(cantidad);

        return q.getResultList();
    }

}
