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
@Table(name = "BSW_ESTADOS_CIVILES", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_ESTADO_CIVIL"})})
@NamedQueries({
    @NamedQuery(name = "BswEstadosCiviles.findAll", query = "SELECT b FROM BswEstadosCiviles b"),
    @NamedQuery(name = "BswEstadosCiviles.findById", query = "SELECT b FROM BswEstadosCiviles b WHERE b.id = :id"),
    @NamedQuery(name = "BswEstadosCiviles.findByCodEstadoCivil", query = "SELECT b FROM BswEstadosCiviles b WHERE b.codEstadoCivil = :codEstadoCivil"),
    @NamedQuery(name = "BswEstadosCiviles.findByDescripcion", query = "SELECT b FROM BswEstadosCiviles b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswEstadosCiviles.findByCodUsuarioAud", query = "SELECT b FROM BswEstadosCiviles b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswEstadosCiviles implements Serializable , EntidadABM, EntidadCodigo {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "ESTADOS_CIVILES_SEQ_GENERATOR", sequenceName = "BSW_ESTADOS_CIVILES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_CIVILES_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_ESTADO_CIVIL", nullable = false, length = 3)
    private String codEstadoCivil;
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @OneToMany(mappedBy = "bswEstadosCiviles")
    private Set<BswPersonas> bswPersonasSet;

    
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
    
    
    public BswEstadosCiviles() {
    }

    public BswEstadosCiviles(Long id) {
        this.id = id;
    }

    public BswEstadosCiviles(Long id, String codEstadoCivil) {
        this.id = id;
        this.codEstadoCivil = codEstadoCivil;
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

    public String getCodEstadoCivil() {
        return codEstadoCivil;
    }

    public void setCodEstadoCivil(String codEstadoCivil) {
        this.codEstadoCivil = codEstadoCivil;
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

    public Set<BswPersonas> getBswPersonasSet() {
        return bswPersonasSet;
    }

    public void setBswPersonasSet(Set<BswPersonas> bswPersonasSet) {
        this.bswPersonasSet = bswPersonasSet;
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
        if (!(object instanceof BswEstadosCiviles)) {
            return false;
        }
        BswEstadosCiviles other = (BswEstadosCiviles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswEstadosCiviles[id=" + id + "]";
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

    @Override
    public String getCodigo() {
       return this.codEstadoCivil;
    }

    @Override
    public void setCodigo(String codigo) {
       this.codEstadoCivil = codigo;
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BswEmpresas getEmpresa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsuario(BswUsuarios user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BswUsuarios getUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
