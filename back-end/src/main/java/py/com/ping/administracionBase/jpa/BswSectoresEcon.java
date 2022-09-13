/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author Inventiva
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_SECTORES_ECON")
@NamedQueries({
    @NamedQuery(name = "BswSectoresEcon.findAll", query = "SELECT b FROM BswSectoresEcon b")
    ,
    @NamedQuery(name = "BswSectoresEcon.findById", query = "SELECT b FROM BswSectoresEcon b WHERE b.id = :id")
    ,
    @NamedQuery(name = "BswSectoresEcon.findByCodSector", query = "SELECT b FROM BswSectoresEcon b WHERE b.codSector = :codSector")
    ,
    @NamedQuery(name = "BswSectoresEcon.findByDescripcion", query = "SELECT b FROM BswSectoresEcon b WHERE b.descripcion = :descripcion")
    ,
    @NamedQuery(name = "BswSectoresEcon.findByComision", query = "SELECT b FROM BswSectoresEcon b WHERE b.comision = :comision")
    ,
    @NamedQuery(name = "BswSectoresEcon.findByCodUsuarioAud", query = "SELECT b FROM BswSectoresEcon b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswSectoresEcon implements Serializable, EntidadCodigo, EntidadABM {

   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "SECTOR_ECON_SEQ_GENERATOR", sequenceName = "BSW_SECTORES_ECON_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTOR_ECON_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_SECTOR")
    private String codSector;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "COMISION")
    private BigDecimal comision;
    @Column(name = "COD_USUARIO_AUD")
    private String codUsuarioAud;
    @OneToMany(mappedBy = "bswSectoresEcon")
    private Set<BswPersonasJuridicas> bswPersonasJuridicasSet;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswEmpresas bswEmpresas;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswModulos bswModulos;
    @Column(name = "user_audit_id")
    private Integer userAuditID;
    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;
    @Column(name = "es_sector_ot")
    private boolean esSectorOt;

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

    public BswSectoresEcon() {
    }

    public BswSectoresEcon(Long id) {
        this.id = id;
    }

    public BswSectoresEcon(Long id, String codSector) {
        this.id = id;
        this.codSector = codSector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodSector() {
        return codSector;
    }

    public void setCodSector(String codSector) {
        this.codSector = codSector;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
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
        if (!(object instanceof BswSectoresEcon)) {
            return false;
        }
        BswSectoresEcon other = (BswSectoresEcon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswSectoresEcon[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.codSector;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codSector = codigo;
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

    /**
     * @return the bswEmpresas
     */
    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    /**
     * @param bswEmpresas the bswEmpresas to set
     */
    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }
    
    /**
     * @return the bswModulos
     */
    public BswModulos getBswModulos() {
        return bswModulos;
    }

    /**
     * @param bswModulos the bswModulos to set
     */
    public void setBswModulos(BswModulos bswModulos) {
        this.bswModulos = bswModulos;
    }
    
     /**
     * @return the esSectorOt
     */
    public boolean isEsSectorOt() {
        return esSectorOt;
    }

    /**
     * @param esSectorOt the esSectorOt to set
     */
    public void setEsSectorOt(boolean esSectorOt) {
        this.esSectorOt = esSectorOt;
    }
}
