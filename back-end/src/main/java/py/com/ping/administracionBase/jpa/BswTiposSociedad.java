/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import py.com.ping.listener.BusinessListener;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author Inventiva
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_TIPOS_SOCIEDAD")
@NamedQueries({
    @NamedQuery(name = "BswTiposSociedad.findAll", query = "SELECT b FROM BswTiposSociedad b"),
    @NamedQuery(name = "BswTiposSociedad.findById", query = "SELECT b FROM BswTiposSociedad b WHERE b.id = :id"),
    @NamedQuery(name = "BswTiposSociedad.findByTipoSociedad", query = "SELECT b FROM BswTiposSociedad b WHERE b.tipoSociedad = :tipoSociedad"),
    @NamedQuery(name = "BswTiposSociedad.findByDescripcion", query = "SELECT b FROM BswTiposSociedad b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswTiposSociedad.findByCodUsuarioAud", query = "SELECT b FROM BswTiposSociedad b WHERE b.codUsuarioAud = :codUsuarioAud")})

public class BswTiposSociedad implements Serializable, EntidadCodigo , EntidadABM {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TIPO_SOCIEDAD")
    private String tipoSociedad;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "COD_USUARIO_AUD")
    private String codUsuarioAud;
    @OneToMany(mappedBy = "bswTiposSociedad")
    private Set<BswPersonasJuridicas> bswPersonasJuridicasSet;

    
    
    @Column(name = "user_audit_id")
private Integer userAuditID;
    
     @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }
    
     @Column(name = "id_session", length = 65)
    private String idSession;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }
    
    
    public BswTiposSociedad() {
    }

    public BswTiposSociedad(Long id) {
        this.id = id;
    }

    public BswTiposSociedad(Long id, String tipoSociedad) {
        this.id = id;
        this.tipoSociedad = tipoSociedad;
    }

    public Long getId() {
        return id;
    }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSociedad() {
        return tipoSociedad;
    }

    public void setTipoSociedad(String tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    
    public Set<BswPersonasJuridicas> getBswPersonasJuridicasSet() {
        return bswPersonasJuridicasSet;
    }

    public void setBswPersonasJuridicasSet(Set<BswPersonasJuridicas> bswPersonasJuridicasSet) {
        this.bswPersonasJuridicasSet = bswPersonasJuridicasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BswTiposSociedad)) {
            return false;
        }
        BswTiposSociedad other = (BswTiposSociedad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswTiposSociedad[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BswEmpresas getEmpresa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUsuario(BswUsuarios user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BswUsuarios getUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getPK() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPK(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
