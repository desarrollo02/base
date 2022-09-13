/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.ejb;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author josema
 */
@Stateless
public class BswGruposUsuariosEJB {
    private static final Logger log =
            LoggerFactory.getLogger(BswGruposUsuariosEJB.class);

    @PersistenceContext
    private EntityManager entidadManager;
    protected FacesMessage facesMsg;

    public void insertar(BswGruposUsuarios entidad, BswEmpresas empresaD) {
        if (existeGrupo(entidad.getCodGrupo(), empresaD.getId())) {
            try {
                entidadManager.persist(new BswGruposUsuarios(entidad, empresaD));
                facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos fueron guardados correctamente!", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            } catch (Exception ex) {
                facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al intentar guardar el registro.", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                log.error("Error al intentar modificar registro.", ex);
            }
        }
    }

    public boolean existeGrupo(String Codigo, Long emp) {
        //entidadManager.getEntityManagerFactory().getCache().evictAll();
        BswGruposUsuarios aux;
        try {
            aux = (BswGruposUsuarios) entidadManager.createNativeQuery("select * from BSW_GRUPOS_USUARIOS where COD_GRUPO='" + Codigo + "' and ID_EMPRESA = " + emp, BswGruposUsuarios.class).getSingleResult();

        } catch (NoResultException e) {
            return true;

        }
        return false;

    }

}
