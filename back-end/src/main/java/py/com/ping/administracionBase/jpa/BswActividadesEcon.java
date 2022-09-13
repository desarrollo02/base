/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_ACTIVIDADES_ECON", catalog = "", schema = "public", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"COD_ACTIVIDAD"})})
@NamedQueries({
  @NamedQuery(name = "BswActividadesEcon.findAll", query = "SELECT b FROM BswActividadesEcon b"),
  @NamedQuery(name = "BswActividadesEcon.findById", query = "SELECT b FROM BswActividadesEcon b WHERE b.id = :id"),
  @NamedQuery(name = "BswActividadesEcon.findByCodActividad", query = "SELECT b FROM BswActividadesEcon b WHERE b.codActividad = :codActividad"),
  @NamedQuery(name = "BswActividadesEcon.findByDescripcion", query = "SELECT b FROM BswActividadesEcon b WHERE b.descripcion = :descripcion"),
  @NamedQuery(name = "BswActividadesEcon.findByAbreviatura", query = "SELECT b FROM BswActividadesEcon b WHERE b.abreviatura = :abreviatura"),
  @NamedQuery(name = "BswActividadesEcon.findByCodUsuarioAud", query = "SELECT b FROM BswActividadesEcon b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswActividadesEcon implements Serializable , EntidadABM{
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID", nullable = false)
  private Long id;
  @Basic(optional = false)
  @Column(name = "COD_ACTIVIDAD", nullable = false, length = 5)
  private String codActividad;
  @Column(name = "DESCRIPCION", length = 80)
  private String descripcion;
  @Column(name = "ABREVIATURA", length = 15)
  private String abreviatura;
  @Column(name = "COD_USUARIO_AUD", length = 10)
  private String codUsuarioAud;
  
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

   
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswActividadesEcon")
  private Set<BswActivPersonas> bswActivPersonasSet;

  
  
    @Column(name = "user_audit_id")
    private Integer userAuditID;

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    
   
  public BswActividadesEcon() {
  }

  
  
  public BswActividadesEcon(Long id) {
    this.id = id;
  }

  public BswActividadesEcon(Long id, String codActividad) {
    this.id = id;
    this.codActividad = codActividad;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodActividad() {
    return codActividad;
  }

  public void setCodActividad(String codActividad) {
    this.codActividad = codActividad;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getAbreviatura() {
    return abreviatura;
  }

  public void setAbreviatura(String abreviatura) {
    this.abreviatura = abreviatura;
  }

  public String getCodUsuarioAud() {
    return codUsuarioAud;
  }

  public void setCodUsuarioAud(String codUsuarioAud) {
    this.codUsuarioAud = codUsuarioAud;
  }

  public Set<BswActivPersonas> getBswActivPersonasSet() {
    return bswActivPersonasSet;
  }

  public void setBswActivPersonasSet(Set<BswActivPersonas> bswActivPersonasSet) {
    this.bswActivPersonasSet = bswActivPersonasSet;
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
    if (!(object instanceof BswActividadesEcon)) {
      return false;
    }
    BswActividadesEcon other = (BswActividadesEcon) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswActividadesEcon[id=" + id + "]";
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
