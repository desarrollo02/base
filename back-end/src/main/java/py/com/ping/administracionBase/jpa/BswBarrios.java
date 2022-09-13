/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_BARRIOS", catalog = "", schema = "public", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"ID_PAIS", "ID_PROVINCIA", "ID_CIUDAD", "COD_BARRIO"})})
@NamedQueries({
  @NamedQuery(name = "BswBarrios.findAll", query = "SELECT b FROM BswBarrios b"),
  @NamedQuery(name = "BswBarrios.findById", query = "SELECT b FROM BswBarrios b WHERE b.id = :id"),
  @NamedQuery(name = "BswBarrios.findByCodBarrio", query = "SELECT b FROM BswBarrios b WHERE b.codBarrio = :codBarrio"),
  @NamedQuery(name = "BswBarrios.count", query = "SELECT count(b) FROM BswBarrios b"),
  @NamedQuery(name = "BswBarrios.findByDescripcion", query = "SELECT b FROM BswBarrios b WHERE b.descripcion = :descripcion"),
  @NamedQuery(name = "BswBarrios.findByAbreviatura", query = "SELECT b FROM BswBarrios b WHERE b.abreviatura = :abreviatura"),
  @NamedQuery(name = "BswBarrios.findByCodUsuarioAud", query = "SELECT b FROM BswBarrios b WHERE b.codUsuarioAud = :codUsuarioAud")})
  public class BswBarrios implements Serializable, EntidadCodigo, EntidadABM  {
  private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "BARRIOS_SEQ_GENERATOR", sequenceName = "BSW_BARRIOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BARRIOS_SEQ_GENERATOR")
    @Column(name = "ID")
  private Long id;
  @NotNullString(campoNombre = "Codigo",groups = AbstractControlerValidationGroups.class)
  @Column(name = "COD_BARRIO")
  private String codBarrio;
  @Column(name = "DESCRIPCION")
  private String descripcion;
  @Column(name = "ABREVIATURA")
  private String abreviatura;
  @Column(name = "COD_USUARIO_AUD")
  private String codUsuarioAud;
  @OneToMany(mappedBy = "bswBarrios")
  private Set<BswSucursales> bswSucursalesSet;
  @NotNull(message = "Provincia es un campo requerido.", groups = AbstractControlerValidationGroups.class)
  @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID", nullable = false)
  @ManyToOne
  private BswProvincias bswProvincias;
  @NotNull(message = "País es un campo requerido.", groups = AbstractControlerValidationGroups.class)
  @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID", nullable = false)
  @ManyToOne
  private BswPaises bswPaises;
  @NotNull(message = "Ciudad es un campo requerido.", groups = AbstractControlerValidationGroups.class)
  @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID", nullable = false)
  @ManyToOne
  private BswCiudades bswCiudades;

  
   
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
  public BswBarrios() {
  }


  public BswBarrios(Long id) {
    this.id = id;
  }

  public BswBarrios(Long id, String codBarrio) {
    this.id = id;
    this.codBarrio = codBarrio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodBarrio() {
    return codBarrio;
  }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

  public void setCodBarrio(String codBarrio) {
    this.codBarrio = codBarrio;
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

  public Set<BswSucursales> getBswSucursalesSet() {
    return bswSucursalesSet;
  }

  public void setBswSucursalesSet(Set<BswSucursales> bswSucursalesSet) {
    this.bswSucursalesSet = bswSucursalesSet;
  }

  public BswProvincias getBswProvincias() {
    return bswProvincias;
  }

  public void setBswProvincias(BswProvincias bswProvincias) {
    this.bswProvincias = bswProvincias;
  }

  public BswPaises getBswPaises() {
    return bswPaises;
  }

  public void setBswPaises(BswPaises bswPaises) {
    this.bswPaises = bswPaises;
  }

  public BswCiudades getBswCiudades() {
    return bswCiudades;
  }

  public void setBswCiudades(BswCiudades bswCiudades) {
    this.bswCiudades = bswCiudades;
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
    if (!(object instanceof BswBarrios)) {
      return false;
    }
    BswBarrios other = (BswBarrios) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "py.com.invweb.py.com.ping.administracionBase.jpa.BswBarrios[id=" + id + "]";
  }

    @Override
    public String getCodigo() {
        return this.codBarrio;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codBarrio=codigo;
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
        return this.id;
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
