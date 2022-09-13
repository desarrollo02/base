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
import py.com.ping.administracionBase.jpa.BswSucursales;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class BswSucursalesEJB {

    @PersistenceContext
    private EntityManager em;
    
     private Query q;
    private List<BswSucursales> list;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void eliminar(BswSucursales entidad) {
        em.remove(em.contains(entidad) ? entidad : em.merge(entidad));
    }

    public Long totalPorFechaModif(Date fecha) {
        Query q;

        if (fecha == null) {
            q = em.createQuery("Select count(a) FROM BswSucursales a");
        } else {
            q = em.createQuery("Select count(a) FROM BswSucursales a where a.fechaModificacion > :fecha");
            q.setParameter("fecha", fecha);
        }
        return (Long) q.getSingleResult();
    }

    public List<BswSucursales> listarPorFechaModif(Date fecha, Integer inicio, Integer cantidad) {
        Query q;
        //TODO : VER PARA QUITAR ESTO MAS ADELANTE
        //em.getEntityManagerFactory().getCache().evictAll();
        if (fecha == null) {
            q = em.createQuery("Select a FROM BswSucursales a order by a.codSucursal");
        } else {
            q = em.createQuery("Select a FROM BswSucursales a where a.fechaModificacion > :fecha order by a.codSucursal");
            q.setParameter("fecha", fecha);
        }

        q.setFirstResult(inicio);
        q.setMaxResults(cantidad);

        return q.getResultList();
    }
    
     public BswSucursales getByCod(String codigo) {
        q = getEm().createNamedQuery("BswSucursales.findByCodSucursal", BswSucursales.class);
        q.setParameter("codSucursal", codigo);
        list = q.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }

    }

}
