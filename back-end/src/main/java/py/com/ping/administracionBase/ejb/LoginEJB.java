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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswSession;

import py.com.ping.administracionBase.jpa.BswUsuarios;

/**
 *
 * @author rlqr
 */
@Stateless
public class LoginEJB {

    @PersistenceContext
    EntityManager em;

    private String queryString;
    private Query query;

    private static final Logger logger =
            LoggerFactory.getLogger(LoginEJB.class);


    public BswUsuarios login(String codigo) {
        try {
            query = em.createQuery("SELECT u FROM BswUsuarios u WHERE u.codUsuario = :codigo");
            query.setParameter("codigo", codigo);
            Object result = query.getSingleResult();
            return (BswUsuarios) result;
        } catch (NullPointerException ex) {
            logger.trace("### NullPointer", ex);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void guardarConfiguracion(BswUsuarios user) {
        em.merge(user.getBswPersonas());
        em.merge(user);
    }

    public void actualizarSession(HttpSession hSession) {
        Query query = em.createNativeQuery("UPDATE public.bsw_session "
                + "SET fecha_logout=? "
                + "WHERE id_session=? ");
        query.setParameter(1, new Date());
        query.setParameter(2, hSession.getId());
        query.executeUpdate();
    }

    public void cerrarSessionesUsuario(BswUsuarios bswUsuario, HttpSession hSession) {
        Query query = em.createNativeQuery("UPDATE public.bsw_session "
                + "SET fecha_logout= ? "
                + "WHERE id_user= ? "
                + "and id_session !=? ");
        query.setParameter(1, new Date());
        query.setParameter(2, bswUsuario.getId());
        query.setParameter(3, hSession.getId());
        query.executeUpdate();
    }

    public BswSession getSessionActivaById(HttpSession hSession) {
        BswSession bswSession = null;
        List<BswSession> sessions;
        queryString = "SELECT s.* "
                + "  FROM bsw_session s "
                + "  where s.id_session = ? ";
        query = em.createNativeQuery(queryString, BswSession.class);
        query.setParameter(1, hSession.getId());
        sessions = query.getResultList();
        if (sessions != null && !sessions.isEmpty()) {
            bswSession = sessions.get(0);
        }
        return bswSession;

    }

    public BswSession getSessionActiva(BswUsuarios bswUsuario) {
        BswSession bswSession = null;
        List<BswSession> sessions;
        queryString = "SELECT s.* "
                + "  FROM bsw_session s "
                + "  where s.fecha_logout is null "
                + "  and id_user = ? ";
        query = em.createNativeQuery(queryString, BswSession.class);
        query.setParameter(1, bswUsuario.getId());

        sessions = query.getResultList();
        if (sessions != null && !sessions.isEmpty()) {
            bswSession = sessions.get(0);
        }

        return bswSession;

    }

}
