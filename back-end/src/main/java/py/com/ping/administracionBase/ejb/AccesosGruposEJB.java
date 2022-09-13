package py.com.ping.administracionBase.ejb;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.jpa.BswTipoForma;
import py.com.ping.utilitarios.interfaces.GenericQuery;

/**
 *
 * @author Administrador
 */
@Stateless
public class AccesosGruposEJB {

    @PersistenceContext
    private EntityManager em;
    private static final Logger log =
            LoggerFactory.getLogger(AccesosGruposEJB.class);
    @EJB
    private GenericQuery genericQuery;
    protected FacesMessage facesMsg;

    private HashMap campos;
    private String queryString;
    private Query query;

    public void insertar(BswAccesosGrupos entidad) {
        em.persist(entidad);
    }

    public void actualizar(BswAccesosGrupos entidadNueva) {
        em.merge(entidadNueva);
    }

    public void eliminar(BswAccesosGrupos entidad) {
        em.remove(em.contains(entidad) ? entidad : em.merge(entidad));
    }

    public BswAccesosGrupos getAccesoGrupoByProps(BswModulos modulo, BswFormas forma, BswGruposUsuarios usuario,BswEmpresas emp) {

        campos = new HashMap();
        campos.put("bswModulos.codModulo", modulo.getCodModulo());
        campos.put("bswGruposUsuarios.codGrupo", usuario.getCodGrupo());
        campos.put("bswFormas.id", forma.getId());
        campos.put("bswEmpresas.id", emp.getId());

        //em.getEntityManagerFactory().getCache().evictAll();
        genericQuery.setEm(em);

        return (BswAccesosGrupos) genericQuery.getSearchSingleResult(campos, BswAccesosGrupos.class, null);

    }

    public List<BswTipoForma> getTiposFormaByModulo(BswModulos mod) {
        queryString = "SELECT tf.* AS tipo_forma "
                + "   FROM bsw_modulos m, "
                + "    bsw_tipo_forma tf, "
                + "    bsw_formas f "
                + "  WHERE tf.id = f.tipo_forma_id AND  "
                + "  m.id = f.id_modulo "
                + "  and m.id= ? "
                + " group by m.id, tf.id "
                + "  ORDER by tf.id";

        //em.getEntityManagerFactory().getCache().evict(BswTipoForma.class);
        query = em.createNativeQuery(queryString, BswTipoForma.class);
        query.setParameter(1, mod.getId());

        return query.getResultList();

    }

 
    
}
