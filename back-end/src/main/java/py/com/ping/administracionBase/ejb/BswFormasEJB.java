package py.com.ping.administracionBase.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswFormas;

/**
 *
 * @author Administrador
 */
@Stateless
public class BswFormasEJB {
    private static final Logger log =
            LoggerFactory.getLogger(BswFormasEJB.class);

    @PersistenceContext
    private EntityManager entidadManager;

    public void insertar(BswFormas entidad) {
        entidadManager.persist(entidad);
    }

    public void actualizar(BswFormas entidadNueva) {
        entidadManager.merge(entidadNueva);
    }

    public void eliminar(BswFormas entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

    public List<BswFormas> listarTodos() {
        return entidadManager.createNamedQuery("BswFormas.findAll").getResultList();
    }

    public BswFormas getForma(String nombreForma) {
        log.debug("Query nombreForma= " + nombreForma);
          String sql = "Select f from BswFormas f where f.nomForma = :nomF "
                + " ";
        Query q = entidadManager.createQuery(sql, BswFormas.class);
        
        q.setParameter("nomF", nombreForma);
        log.debug("Query q= " + q);
        try{
            return (BswFormas)q.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
}
