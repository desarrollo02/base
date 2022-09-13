/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.listener;

import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.utilitarios.jpa.EntidadABM;

import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Rudy
 */
public class BusinessListener implements Serializable {

    BswUsuarios user;
    Map<String, Object> map;

    @PrePersist
    public void prePersist(EntidadABM ob) {
        addAudit(ob);
        System.out.println("Listening EntidadABM Pre Persist : " + ob.getUserAuditID());
//    
    }

//    @PostPersist
//    public void postPersist(EntidadABM ob) {
//        System.out.println("Listening EntidadABM Post Persist : " + ob.getName());
//    }
//
//    @PostLoad
//    public void postLoad(EntidadABM ob) {
//        System.out.println("Listening EntidadABM Post Load : " + ob.getName());
//    }
    @PreUpdate
    public void preUpdate(EntidadABM ob) {
        addAudit(ob);
        System.out.println("Listening EntidadABM Pre Update : " + ob.getUserAuditID());
//   
    }

//    @PostUpdate
//    public void postUpdate(EntidadABM ob) {
//        System.out.println("Listening EntidadABM Post Update : " + ob.getName());
//    }
    @PreRemove
    public void preRemove(EntidadABM ob) {
        addAudit(ob);
        System.out.println("Listening EntidadABM Pre Remove : " + ob.getUserAuditID());
    }

//    @PostRemove
//    public void postRemove(EntidadABM ob) {
//        System.out.println("Listening EntidadABM Post Remove : " + ob.getName());
//    }
    private void addAudit(EntidadABM ob) {
        if (FacesContext.getCurrentInstance() != null) {
            map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            Long userId = (Long) map.get("user");
            if (userId != null) {
                ob.setUserAuditID(userId.intValue());
            }

            ob.setIpCliente((String) map.get("ipCliente"));
            ob.setIdSession((String) map.get("idSession"));
        }

    }
}
