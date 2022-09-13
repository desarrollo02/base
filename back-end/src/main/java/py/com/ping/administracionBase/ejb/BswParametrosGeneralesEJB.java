/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswParametrosGenerales;

/**
 *
 * @author Ariel Talavera
 */
@Stateless
public class BswParametrosGeneralesEJB {
    
    @PersistenceContext
    private EntityManager entidadManager;

    public void insertar(BswParametrosGenerales entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswParametrosGenerales entidad) {
        entidadManager.merge(entidad);
    }

    public void eliminar(BswParametrosGenerales entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }
    
    
     public BswParametrosGenerales getParametro(long moduloId, String nombreParametro) {
        try{
        String sql = "Select p from BswParametrosGenerales p where p.parametro = :par "
                + " and p.bswModulos.id = :id";
        Query q = entidadManager.createQuery(sql, BswParametrosGenerales.class);
        q.setParameter("par", nombreParametro);
        q.setParameter("id", moduloId);
         return (BswParametrosGenerales) q.getSingleResult();
        }catch(Exception e){
        return null;
        
        }
    }

    public BswParametrosGenerales getParametro(String codModulo, String nombreParametro) {
        
        try{
        String sql = "Select p from BswParametrosGenerales p where p.parametro = :par "
                + " and p.bswModulos.codModulo = :codModulo";
        Query q = entidadManager.createQuery(sql, BswParametrosGenerales.class);
        q.setParameter("par", nombreParametro);
        q.setParameter("codModulo", codModulo);
        return (BswParametrosGenerales) q.getSingleResult();
        }catch(Exception e){
        return null;
        
        }
    }
    
    @EJB
    BswFormasEJB formasEJB;
   
    public String buscarParametro(String codigo, String parametro) {
        try {
            BswFormas f = new BswFormas();
            f = formasEJB.getForma(codigo);
            //Se puede dar estos 2 casos 1-codigo=nombre de la forma, 2-codigo=codigo del modulo
            //Para el caso 1 se utiliza la primera condicion
            //Para el caso 2 se utiliza la segunda condicion
            if (f != null) {
                BswParametrosGenerales pg = getParametro(f.getBswModulos().getId(), parametro);
                if (pg != null) {
                    return pg.getValor();
                }
            } else {
                BswParametrosGenerales pg = getParametro(codigo, parametro);
                if (pg != null) {
                    return pg.getValor();
                }

            }

        } catch (Exception e) {
            // log.error("Error al buscar parametros", e);
            return null;

        }
        return null;
    }
    
}
