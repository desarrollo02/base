/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.jpa.BswIdentPersonas;
import py.com.ping.administracionBase.jpa.BswPersonas;

/**
 *
 * @author Administrador
 */
@Stateless
public class BswPersonasEJB {

    @PersistenceContext
    private EntityManager entidadManager;
    private @Inject
    LoginControlador loginControlador;

    public void insertar(BswPersonas entidad) {
        entidadManager.persist(entidad);
    }
    
    public BswPersonas insertarRapido(BswPersonas entidad) {
        entidadManager.merge(entidad);
        return (BswPersonas) entidadManager.createNamedQuery("BswPersonas.findByCodPersona",BswPersonas.class).setParameter("codPersona", entidad.getCodPersona()).getSingleResult();
        
    }

    public void actualizar(BswPersonas entidadNueva) {
        entidadManager.merge(entidadNueva);
    }
    
    public void actualizarPersonaConDetalle(BswPersonas entidadNueva, List<BswIdentPersonas> listaDocumento) {
        
        if (listaDocumento != null) {
            for (BswIdentPersonas detalleOriginal : listaDocumento) {
                if (entidadNueva.getBswIdentPersonasList()!=null && !entidadNueva.getBswIdentPersonasList().contains(detalleOriginal)) {
                    entidadManager.remove(entidadManager.contains(detalleOriginal) ? detalleOriginal : entidadManager.merge(detalleOriginal));
                }
            }
        }
        
        entidadManager.merge(entidadNueva);
        entidadManager.flush();
    }

    public void eliminar(BswPersonas entidad) {
        entidadManager.remove(entidadManager.contains(entidad) ? entidad : entidadManager.merge(entidad));
    }

    
    
    public List<BswPersonas> listarTodos() {
        return entidadManager.createNamedQuery("BswPersonas.findAll").getResultList();
    }

    public String getMaxCodPersona() {
        Query q = entidadManager.createNativeQuery("select max(cast(cod_persona as numeric)) from bsw_personas");
        return "1";//q.getSingleResult().toString();
    }

    public String getDireccionPersona(long idPersona) {
        String sql = "Select detalle from bsw_direc_personas dp where id_persona = ? and por_defecto = 'S'";
        Query q = entidadManager.createNativeQuery(sql);
        q.setParameter(1, idPersona);
        try {
            return q.getSingleResult().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getRucPersona(long idPersona) {
        long idRUC;
        String sql = "Select id from bsw_identificaciones ip where cod_ident = 'RUC' ";
        Query q = entidadManager.createNativeQuery(sql);

        try {
            idRUC = Long.parseLong(q.getSingleResult().toString());
        } catch (Exception e) {
            idRUC = 0;
        }

        if (idRUC != 0) {
            sql = "Select numero from bsw_ident_personas ip where id_persona = ? and id_identificaciones = ? ";
            q = entidadManager.createNativeQuery(sql);
            q.setParameter(1, idPersona);
            q.setParameter(2, idRUC);
            try {
                return q.getSingleResult().toString();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String getTelefonoPersona(long idPersona) {
        String sql = "Select num_telefono from bsw_telef_personas tp where id_persona = ? and por_defecto = 'S'";
        Query q = entidadManager.createNativeQuery(sql);
        q.setParameter(1, idPersona);
        try {
            return q.getSingleResult().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public BswPersonas getPersonaByCodPersona(String cod) {
        String sql = "Select * from bsw_personas where cod_persona = ?";
        Query q = entidadManager.createNativeQuery(sql, BswPersonas.class);
        q.setParameter(1, cod);
        try {
            return (BswPersonas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     public String getCIPersona(long idPersona) {
        long idCI;
        String sql = "Select id from bsw_identificaciones ip where cod_ident = 'CI' ";
        Query q = entidadManager.createNativeQuery(sql);

        try {
            idCI = Long.parseLong(q.getSingleResult().toString());
        } catch (Exception e) {
            idCI = 0;
        }

        if (idCI != 0) {
            sql = "Select numero from bsw_ident_personas ip where id_persona = ? and id_identificaciones = ? ";
            q = entidadManager.createNativeQuery(sql);
            q.setParameter(1, idPersona);
            q.setParameter(2, idCI);
            try {
                return q.getSingleResult().toString();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
