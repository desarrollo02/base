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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author Inventiva
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_PAISES")
@NamedQueries({
    @NamedQuery(name = "BswPaises.findAll", query = "SELECT b FROM BswPaises b"),
    @NamedQuery(name = "BswPaises.findById", query = "SELECT b FROM BswPaises b WHERE b.id = :id"),
    @NamedQuery(name = "BswPaises.findAllOrdered", query = "SELECT b FROM BswPaises b ORDER by b.id"),
    @NamedQuery(name = "BswPaises.findByCodPais", query = "SELECT b FROM BswPaises b WHERE b.codPais = :codPais"),
    @NamedQuery(name = "BswPaises.findByDescripcion", query = "SELECT b FROM BswPaises b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswPaises.findByNacionalidad", query = "SELECT b FROM BswPaises b WHERE b.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "BswPaises.findByCodigoArea", query = "SELECT b FROM BswPaises b WHERE b.codigoArea = :codigoArea"),
    @NamedQuery(name = "BswPaises.findByAbreviatura", query = "SELECT b FROM BswPaises b WHERE b.abreviatura = :abreviatura"),
    @NamedQuery(name = "BswPaises.findBySiglas", query = "SELECT b FROM BswPaises b WHERE b.siglas = :siglas"),
    @NamedQuery(name = "BswPaises.count", query = "SELECT count(b) FROM BswPaises b"),
    @NamedQuery(name = "BswPaises.findByCodUsuarioAud", query = "SELECT b FROM BswPaises b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswPaises implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "BSW_PAISES_SEQ_GENERATOR", sequenceName = "BSW_PAISES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BSW_PAISES_SEQ_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @NotNullString(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class)
    @Basic(optional = false)
    @Column(name = "COD_PAIS")
    private String codPais;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @Column(name = "CODIGO_AREA")
    private String codigoArea;
    @Column(name = "ABREVIATURA")
    private String abreviatura;
    @Column(name = "SIGLAS")
    private String siglas;
    @Column(name = "COD_USUARIO_AUD")
    private String codUsuarioAud;
    @OneToMany(mappedBy = "bswPaises")
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

    public BswPaises() {
    }

    public BswPaises(Long id) {
        this.id = id;
    }

    public BswPaises(Long id, String codPais) {
        this.id = id;
        this.codPais = codPais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
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
        if (!(object instanceof BswPaises)) {
            return false;
        }
        BswPaises other = (BswPaises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswPaises[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.codPais;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codPais = codigo;
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {
       
    }

    @Override
    public BswEmpresas getEmpresa() {
        return null;
    }

    @Override
    public void setUsuario(BswUsuarios user) {

    }

    @Override
    public BswUsuarios getUsuario() {
        return null;
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
