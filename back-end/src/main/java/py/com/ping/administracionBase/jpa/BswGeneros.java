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
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_GENEROS", catalog = "", schema = "public")
@NamedQueries({
  @NamedQuery(name = "BswGeneros.findAll", query = "SELECT b FROM BswGeneros b"),
  @NamedQuery(name = "BswGeneros.findById", query = "SELECT b FROM BswGeneros b WHERE b.id = :id"),
  @NamedQuery(name = "BswGeneros.findByCodUsuarioAud", query = "SELECT b FROM BswGeneros b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswGeneros implements Serializable , EntidadABM {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID", nullable = false)
  private Integer id;
  @Column(name = "COD_USUARIO_AUD", length = 10)
  private String codUsuarioAud;
  @Column(name = "CODIGO", length = 12)
  private String codigo;
  @Column(name = "DESCRIPCION", length = 120)
  private String descripcion;

  
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
  public BswGeneros() {
  }

  public BswGeneros(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCodUsuarioAud() {
    return codUsuarioAud;
  }

  public void setCodUsuarioAud(String codUsuarioAud) {
    this.codUsuarioAud = codUsuarioAud;
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
        if (!(object instanceof BswGeneros)) {
            return false;
        }
        BswGeneros other = (BswGeneros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswGeneros[id=" + id + "]";
  }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

}
