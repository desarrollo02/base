/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.ping.administracionBase.jpa.BswEmpresas;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class BswEmpresasEJB {


    @PersistenceContext
    private EntityManager em;
    private Query q;
    private List<BswEmpresas> list;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void insertar(BswEmpresas entidad) {
        em.persist(entidad);
    }

    public void actualizar(BswEmpresas entidad) {
        em.merge(entidad);
    }

    public void eliminar(BswEmpresas entidad) {
        em.remove(em.contains(entidad) ? entidad : em.merge(entidad));
    }

    public BswEmpresas getEmpresaByCod(String codigo) {
        q = getEm().createNamedQuery("BswEmpresas.findByCodEmpresa", BswEmpresas.class);
        q.setParameter("codEmpresa", codigo);
        list = q.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }

    }

}
