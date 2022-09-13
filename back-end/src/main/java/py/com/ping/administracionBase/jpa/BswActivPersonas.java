/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_ACTIV_PERSONAS", catalog = "", schema = "public", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"ID_ACTIVIDAD", "ID_PERSONA"})})
@NamedQueries({
  @NamedQuery(name = "BswActivPersonas.findAll", query = "SELECT b FROM BswActivPersonas b"),
  @NamedQuery(name = "BswActivPersonas.findById", query = "SELECT b FROM BswActivPersonas b WHERE b.id = :id"),
  @NamedQuery(name = "BswActivPersonas.findByCodUsuarioAud", query = "SELECT b FROM BswActivPersonas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswActivPersonas implements Serializable , EntidadABM{
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID", nullable = false)
  private Long id;
  @Column(name = "COD_USUARIO_AUD", length = 10)
  private String codUsuarioAud;
  @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswPersonas bswPersonas;
  @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswActividadesEcon bswActividadesEcon;

    @Column(name = "user_audit_id")
private Integer userAuditID;
  
    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;
    
     @Column(name = "id_session", length = 65)
    private String idSession;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }
  
  public BswActivPersonas() {
  }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

  
  

  public BswActivPersonas(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodUsuarioAud() {
    return codUsuarioAud;
  }

  public void setCodUsuarioAud(String codUsuarioAud) {
    this.codUsuarioAud = codUsuarioAud;
  }

  public BswPersonas getBswPersonas() {
    return bswPersonas;
  }

  public void setBswPersonas(BswPersonas bswPersonas) {
    this.bswPersonas = bswPersonas;
  }

  public BswActividadesEcon getBswActividadesEcon() {
    return bswActividadesEcon;
  }

  public void setBswActividadesEcon(BswActividadesEcon bswActividadesEcon) {
    this.bswActividadesEcon = bswActividadesEcon;
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
    if (!(object instanceof BswActivPersonas)) {
      return false;
    }
    BswActivPersonas other = (BswActivPersonas) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswActivPersonas[id=" + id + "]";
  }

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPK(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    
}
