/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.primefaces.model.LazyDataModel;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.utilitarios.interfaces.GenericQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author gdb <dominbla@gmail.com>
 */
@Named
@ViewScoped
public class ObjetoComunes implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private static final Logger log =
            LoggerFactory.getLogger(ObjetoComunes.class);

    @EJB
    private GenericQuery genericQuery;
    //<editor-fold defaultstate="collapsed" desc="Usuario">
    private BswUsuarios usuario;

    private LazyDataModel lazyListUsuario;
    private GenericLazyListNew gLLUsuario;

    public GenericLazyListNew getgLLUsuario() {
        if (gLLUsuario == null) {
            gLLUsuario = new GenericLazyListNew(em, BswUsuarios.class);
        }
        return gLLUsuario;
    }

    public void setgLLUsuario(GenericLazyListNew gLLUsuario) {
        this.gLLUsuario = gLLUsuario;
    }

    public void obtieneLazyUsuarios() {
        HashMap campos = new HashMap();
        campos.put("estado", "A");
        lazyListUsuario = getgLLUsuario().getLazyDataModel(BswUsuarios.class, campos);

    }

    public LazyDataModel getLazyListUsuario() {
        if (lazyListUsuario == null) {
            obtieneLazyUsuarios();
        }

        return lazyListUsuario;
    }

    public void setLazyListUsuario(LazyDataModel lpb) {
        this.lazyListUsuario = lpb;
    }

    /**
     * @return the usuario
     */
    public BswUsuarios getUsuario() {
        if (usuario == null) {
            usuario = new BswUsuarios();
        }
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(BswUsuarios usuario) {
        this.usuario = usuario;
    }

    public void usuarioChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                HashMap campos = new HashMap();
                campos.put("estado", "A");
                campos.put("codUsuario", event.getNewValue().toString());
                genericQuery.setEm(em);
                usuario = (BswUsuarios) genericQuery.getSearchSingleResult(campos, BswUsuarios.class, null);
            } catch (Exception ex) {
                usuario = new BswUsuarios();
                mensajeAlerta("No Existe el codigo de Usuario");
                log.error("Error al consultar Usuario", ex);
            }
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Mensajes">

    protected FacesMessage facesMsg;

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    //</editor-fold>
}
