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
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_PROVINCIAS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_PAIS", "COD_PROVINCIA"})})
@NamedQueries({
    @NamedQuery(name = "BswProvincias.findAll", query = "SELECT b FROM BswProvincias b"),
    @NamedQuery(name = "BswProvincias.findById", query = "SELECT b FROM BswProvincias b WHERE b.id = :id"),

    @NamedQuery(name = "BswProvincias.findByCodProvincia", query = "SELECT b FROM BswProvincias b WHERE b.codProvincia = :codProvincia"),
    @NamedQuery(name = "BswProvincias.findByDescripcion", query = "SELECT b FROM BswProvincias b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswProvincias.findByAbreviatura", query = "SELECT b FROM BswProvincias b WHERE b.abreviatura = :abreviatura"),
    @NamedQuery(name = "BswProvincias.count", query = "SELECT count(b) FROM BswProvincias b"),
    @NamedQuery(name = "BswProvincias.findByCodUsuarioAud", query = "SELECT b FROM BswProvincias b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswProvincias implements EntidadCodigo, Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PROVINCIA_SEQ_GENERATOR", sequenceName = "BSW_PROVINCIAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVINCIA_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_PROVINCIA", nullable = false, length = 10)
    private String codProvincia;
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "ABREVIATURA", length = 5)
    private String abreviatura;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswPaises bswPaises;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswProvincias")
    private Set<BswCiudades> bswCiudadesSet;
    @OneToMany(mappedBy = "bswProvincias")
    private Set<BswSucursales> bswSucursalesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswProvincias")
    private Set<BswBarrios> bswBarriosSet;
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

    public BswProvincias() {
    }

    public BswProvincias(Long id) {
        this.id = id;
    }

    public BswProvincias(Long id, String codProvincia) {
        this.id = id;
        this.codProvincia = codProvincia;
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

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
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

    public BswPaises getBswPaises() {
        return bswPaises;
    }

    public void setBswPaises(BswPaises bswPaises) {
        this.bswPaises = bswPaises;
    }

    
    public Set<BswCiudades> getBswCiudadesSet() {
        return bswCiudadesSet;
    }

    public void setBswCiudadesSet(Set<BswCiudades> bswCiudadesSet) {
        this.bswCiudadesSet = bswCiudadesSet;
    }

    
    public Set<BswSucursales> getBswSucursalesSet() {
        return bswSucursalesSet;
    }

    public void setBswSucursalesSet(Set<BswSucursales> bswSucursalesSet) {
        this.bswSucursalesSet = bswSucursalesSet;
    }

    
    public Set<BswBarrios> getBswBarriosSet() {
        return bswBarriosSet;
    }

    public void setBswBarriosSet(Set<BswBarrios> bswBarriosSet) {
        this.bswBarriosSet = bswBarriosSet;
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
        if (!(object instanceof BswProvincias)) {
            return false;
        }
        BswProvincias other = (BswProvincias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswProvincias[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.codProvincia;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codProvincia = codigo;
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {

    }

    @Override
    public BswEmpresas getEmpresa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUsuario(BswUsuarios user) {
        this.codUsuarioAud = user.getCodUsuario();
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
