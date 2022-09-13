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
import javax.persistence.UniqueConstraint;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_PERMISOS_FORMAS", catalog = "", schema = "public", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"ID_USUARIO", "ID_MODULO", "NOM_FORMA", "PARAMETRO"})})
@NamedQueries({
  @NamedQuery(name = "BswPermisosFormas.findAll", query = "SELECT b FROM BswPermisosFormas b"),
  @NamedQuery(name = "BswPermisosFormas.findById", query = "SELECT b FROM BswPermisosFormas b WHERE b.id = :id"),
  @NamedQuery(name = "BswPermisosFormas.findByNomForma", query = "SELECT b FROM BswPermisosFormas b WHERE b.nomForma = :nomForma"),
  @NamedQuery(name = "BswPermisosFormas.findByParametro", query = "SELECT b FROM BswPermisosFormas b WHERE b.parametro = :parametro"),
  @NamedQuery(name = "BswPermisosFormas.findByIndPermiso", query = "SELECT b FROM BswPermisosFormas b WHERE b.indPermiso = :indPermiso"),
  @NamedQuery(name = "BswPermisosFormas.findByCodUsuarioAud", query = "SELECT b FROM BswPermisosFormas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswPermisosFormas implements Serializable, EntidadCodigo , EntidadABM {
  private static final long serialVersionUID = 1L;
  @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "PERMISOS_FORMAS_SEQ_GENERATOR", sequenceName = "BSW_PERMISOS_FORMAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISOS_FORMAS_SEQ_GENERATOR")
  @Column(name = "ID", nullable = false)
  private Long id;
  @Basic(optional = false)
  @Column(name = "NOM_FORMA", nullable = false, length = 8)
  private String nomForma;
  @Basic(optional = false)
  @Column(name = "PARAMETRO", nullable = false, length = 50)
  private String parametro;
  @Column(name = "IND_PERMISO", length = 1)
  private String indPermiso;
  @Column(name = "COD_USUARIO_AUD", length = 10)
  private String codUsuarioAud;
  @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswUsuarios bswUsuarios;
  @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false)
  private BswModulos bswModulos;
  @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
  @ManyToOne
  private BswEmpresas bswEmpresas;

  
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
    
  
  public BswPermisosFormas() {
  }

  public BswPermisosFormas(Long id) {
    this.id = id;
  }

  public BswPermisosFormas(Long id, String nomForma, String parametro) {
    this.id = id;
    this.nomForma = nomForma;
    this.parametro = parametro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomForma() {
    return nomForma;
  }

  public void setNomForma(String nomForma) {
    this.nomForma = nomForma;
  }

  public String getParametro() {
    return parametro;
  }

  public void setParametro(String parametro) {
    this.parametro = parametro;
  }

  public String getIndPermiso() {
    return indPermiso;
  }

  public void setIndPermiso(String indPermiso) {
    this.indPermiso = indPermiso;
  }

  public String getCodUsuarioAud() {
    return codUsuarioAud;
  }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
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
    if (!(object instanceof BswPermisosFormas)) {
      return false;
    }
    BswPermisosFormas other = (BswPermisosFormas) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswPermisosFormas[id=" + id + "]";
  }



    @Override
    public String getCodigo() {
        return this.getNomForma();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setNomForma(codigo);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public Object getPK() {
        return id;
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

}
