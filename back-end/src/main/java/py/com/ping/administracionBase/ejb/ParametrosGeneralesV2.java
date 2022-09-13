/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

/**
 *
 * @author vo
 */
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswParametrosGenerales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@Stateless
public class ParametrosGeneralesV2{
    private static final Logger log =
            LoggerFactory.getLogger(ParametrosGeneralesV2.class);

    @PersistenceContext
    private EntityManager em;
    @EJB
    BswParametrosGeneralesEJB parametrosEJB;
    @EJB
    BswFormasEJB formasEJB;

    public ParametrosGeneralesV2() {
    }

    

    public String buscarParametro(String codigo, String parametro) {
        try {
            BswFormas f = new BswFormas();
            f = formasEJB.getForma(codigo);
            //Se puede dar estos 2 casos 1-codigo=nombre de la forma, 2-codigo=codigo del modulo
            //Para el caso 1 se utiliza la primera condicion
            //Para el caso 2 se utiliza la segunda condicion
            if (f != null) {
                BswParametrosGenerales pg= parametrosEJB.getParametro(f.getBswModulos().getId(), parametro);
                if(pg != null)
                    return pg.getValor();
            }else{
                BswParametrosGenerales pg= parametrosEJB.getParametro(codigo, parametro);
                if(pg != null)
                    return pg.getValor();

            }

        } catch (Exception e) {
            log.error("Error al buscar parametros",e);
            return  null;

        }
        return null;
    }
}
