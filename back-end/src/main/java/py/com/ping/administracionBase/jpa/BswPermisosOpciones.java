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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_PERMISOS_OPCIONES")
@NamedQueries({
  @NamedQuery(name = "BswPermisosOpciones.findAll", query = "SELECT b FROM BswPermisosOpciones b ORDER BY b.bswModulos.id"),
  @NamedQuery(name = "BswPermisosOpciones.findByEmpUsuParam", query = "SELECT b FROM BswPermisosOpciones b WHERE b.bswEmpresas = :empresa and b.bswUsuarios = :usuario and b.bswOpcionesParametros.parametro=:parametro"),
  @NamedQuery(name = "BswPermisosOpciones.findById", query = "SELECT b FROM BswPermisosOpciones b WHERE b.id = :id"),
 
  @NamedQuery(name = "BswPermisosOpciones.findByCodUsuarioAud", query = "SELECT b FROM BswPermisosOpciones b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswPermisosOpciones implements EntidadCodigo, Serializable, EntidadABM  {

  private static final long serialVersionUID = 1L;
  @Id
  @SequenceGenerator(name="PERMISOS_SEQ_GENERATOR" ,sequenceName="BSW_PERMISOS_OPCIONES_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE , generator="PERMISOS_SEQ_GENERATOR")
  @Column(name = "ID")
  private Long id;
  
  @JoinColumn(name = "ID_PARAMETRO_OPCION", referencedColumnName = "ID")
  @ManyToOne
  private BswOpcionesParametros bswOpcionesParametros;
  
  @Column(name = "PERMISO", length = 1)
  private String permiso;
  @Column(name = "COD_USUARIO_AUD", length = 10)
  private String codUsuarioAud;
  @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswUsuarios bswUsuarios;
  @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswModulos bswModulos;
  @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswEmpresas bswEmpresas;

  @Transient
  private boolean poseePermiso;  
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
    
  public BswPermisosOpciones() {
  }

  public BswPermisosOpciones(Long id) {
    this.id = id;
  }

    public BswOpcionesParametros getBswOpcionesParametros() {
        return bswOpcionesParametros;
    }

    public void setBswOpcionesParametros(BswOpcionesParametros bswOpcionesParametros) {
        this.bswOpcionesParametros = bswOpcionesParametros;
    }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
  

  public String getPermiso() {
    return permiso;
  }

  public void setPermiso(String permiso) {
    this.permiso = permiso;
  }

  public String getCodUsuarioAud() {
    return codUsuarioAud;
  }

  public void setCodUsuarioAud(String codUsuarioAud) {
    this.codUsuarioAud = codUsuarioAud;
  }

  public BswUsuarios getBswUsuarios() {
    return bswUsuarios;
  }

  public void setBswUsuarios(BswUsuarios bswUsuarios) {
    this.bswUsuarios = bswUsuarios;
  }

  public BswModulos getBswModulos() {
    return bswModulos;
  }

  public void setBswModulos(BswModulos bswModulos) {
    this.bswModulos = bswModulos;
  }

  public BswEmpresas getBswEmpresas() {
    return bswEmpresas;
  }

  public void setBswEmpresas(BswEmpresas bswEmpresas) {
    this.bswEmpresas = bswEmpresas;
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
    if (!(object instanceof BswPermisosOpciones)) {
      return false;
    }
    BswPermisosOpciones other = (BswPermisosOpciones) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswPermisosOpciones[id=" + id + "]";
  }

    @Override
    public String getCodigo() {
       return this.id.toString();
    }

    @Override
    public void setCodigo(String codigo) {
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {
        this.setBswEmpresas(emp);
    }

    @Override
    public BswEmpresas getEmpresa() {
       return this.getBswEmpresas();
    }

    @Override
    public void setUsuario(BswUsuarios user) {
        this.codUsuarioAud=user.getCodUsuario();
    }

    @Override
    public BswUsuarios getUsuario() {
        return new BswUsuarios();
    }

    @Override
    public Object getPK() {
       return  this.getId();
    }

    @Override
    public void setPK(Object id) {
       this.id = (Long) id;
    }

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    /**
     * @return the poseePermiso
     */
    public boolean isPoseePermiso() {
        return (permiso!=null && "S".equals(permiso));
    }

    /**
     * @param poseePermiso the poseePermiso to set
     */
    public void setPoseePermiso(boolean poseePermiso) {
        this.poseePermiso = poseePermiso;
        if(poseePermiso){
            permiso="S";
        }else{
            permiso="N";
        }
    }
}
