package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;


import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author rudy
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "bsw_tipo_programa")

@NamedQueries({
    @NamedQuery(name = "BswTipoPrograma.findAll", query = "SELECT b FROM BswTipoPrograma b"),
    @NamedQuery(name = "BswTipoPrograma.findById", query = "SELECT b FROM BswTipoPrograma b WHERE b.id = :id"),
    @NamedQuery(name = "BswTipoPrograma.findByCodigo", query = "SELECT b FROM BswTipoPrograma b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "BswTipoPrograma.findByDescripcion", query = "SELECT b FROM BswTipoPrograma b WHERE b.descripcion = :descripcion")})
public class BswTipoPrograma implements Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @OneToMany(mappedBy = "bswTipoPrograma")
    private List<BswSucursales> bswSucursalesList;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;

    
    
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
    
    
    public BswTipoPrograma() {
    }

    public BswTipoPrograma(Integer id) {
        this.id = id;
    }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }

    
    public List<BswSucursales> getBswSucursalesList() {
        return bswSucursalesList;
    }

    public void setBswSucursalesList(List<BswSucursales> bswSucursalesList) {
        this.bswSucursalesList = bswSucursalesList;
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
        if (!(object instanceof BswTipoPrograma)) {
            return false;
        }
        BswTipoPrograma other = (BswTipoPrograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswTipoPrograma[ id=" + id + " ]";
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
        return this.id;
    }

    @Override
    public void setPK(Object id) {
        this.id = (Integer) id;
    }

}
