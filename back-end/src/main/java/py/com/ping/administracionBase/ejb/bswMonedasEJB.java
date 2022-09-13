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
import py.com.ping.administracionBase.jpa.BswMonedas;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class bswMonedasEJB {

    @PersistenceContext
    private EntityManager entidadManager;

    public void insertar(BswMonedas entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswMonedas entidad) {
        entidadManager.merge(entidad);
    }

    public void eliminar(BswMonedas entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

   public Long totalPorFechaModif(Date fecha) {
        Query q;

        if (fecha == null) {
            q = entidadManager.createQuery("Select count(a) FROM BswMonedas a");
        } else {
            q = entidadManager.createQuery("Select count(a) FROM BswMonedas a where a.fechaModificacion > :fecha");
            q.setParameter("fecha", fecha);
        }
        return (Long) q.getSingleResult();
    }

    public List<BswMonedas> listarPorFechaModif(Date fecha, Integer inicio, Integer cantidad) {
        Query q;
        //TODO : VER PARA QUITAR ESTO MAS ADELANTE
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        if (fecha == null) {
            q = entidadManager.createQuery("Select a FROM BswMonedas a order by a.codMoneda");
        } else {
            q = entidadManager.createQuery("Select a FROM BswMonedas a where a.fechaModificacion > :fecha order by a.codMoneda");
            q.setParameter("fecha", fecha);
        }

        q.setFirstResult(inicio);
        q.setMaxResults(cantidad);

        return q.getResultList();
    }

    /**
	 * Get moneda by sigla  
	 * @param sigla value 
	 * @return {@link BswMonedas} object   
	 */
	public BswMonedas findBySiglas(String siglas) {
		return (BswMonedas)entidadManager.createNamedQuery("BswMonedas.findBySiglas")
				.setParameter("siglas", siglas)
				.getSingleResult();
	}

}
