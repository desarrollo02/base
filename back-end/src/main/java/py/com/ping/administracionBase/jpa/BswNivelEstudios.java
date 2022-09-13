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
import javax.persistence.UniqueConstraint;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_NIVEL_ESTUDIOS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_NIVEL"})})
@NamedQueries({
    @NamedQuery(name = "BswNivelEstudios.getPorCodigo", query = "SELECT b FROM BswNivelEstudios b WHERE b.codNivel = :codigo"),
    @NamedQuery(name = "BswNivelEstudios.findAll", query = "SELECT b FROM BswNivelEstudios b"),
    @NamedQuery(name = "BswNivelEstudios.findById", query = "SELECT b FROM BswNivelEstudios b WHERE b.id = :id"),
    @NamedQuery(name = "BswNivelEstudios.findByCodNivel", query = "SELECT b FROM BswNivelEstudios b WHERE b.codNivel = :codNivel"),
    @NamedQuery(name = "BswNivelEstudios.findByDescripcion", query = "SELECT b FROM BswNivelEstudios b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswNivelEstudios.findByCodUsuarioAud", query = "SELECT b FROM BswNivelEstudios b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswNivelEstudios implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "NIVEL_ESTUDIO_SEQ_GENERATOR", sequenceName = "BSW_NIVEL_ESTUDIOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NIVEL_ESTUDIO_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_NIVEL", nullable = false, length = 5)
    private String codNivel;
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @OneToMany(mappedBy = "bswNivelEstudios")
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

    public BswNivelEstudios() {
    }

    public BswNivelEstudios(Long id) {
        this.id = id;
    }

    public BswNivelEstudios(Long id, String codNivel) {
        this.id = id;
        this.codNivel = codNivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodNivel() {
        return codNivel;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setCodNivel(String codNivel) {
        this.codNivel = codNivel;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BswNivelEstudios)) {
            return false;
        }
        BswNivelEstudios other = (BswNivelEstudios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswNivelEstudios[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.getCodNivel();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setCodNivel(codigo);
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
        return id;

    }

    @Override
    public void setPK(Object id) {
        this.id = (Long) id;
    }

    public Set<BswPersonas> getBswPersonasSet() {
        return bswPersonasSet;
    }

    public void setBswPersonasSet(Set<BswPersonas> bswPersonasSet) {
        this.bswPersonasSet = bswPersonasSet;
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
