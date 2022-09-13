/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
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
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_IDENTIFICACIONES")
@NamedQueries({
    @NamedQuery(name = "BswIdentificaciones.findAll", query = "SELECT b FROM BswIdentificaciones b"),
    @NamedQuery(name = "BswIdentificaciones.findById", query = "SELECT b FROM BswIdentificaciones b WHERE b.id = :id"),
    @NamedQuery(name = "BswIdentificaciones.findByCodIdent", query = "SELECT b FROM BswIdentificaciones b WHERE b.codIdent = :codIdent"),
    @NamedQuery(name = "BswIdentificaciones.findByDescripcion", query = "SELECT b FROM BswIdentificaciones b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswIdentificaciones.findByMascara", query = "SELECT b FROM BswIdentificaciones b WHERE b.mascara = :mascara"),
    @NamedQuery(name = "BswIdentificaciones.findByAbreviatura", query = "SELECT b FROM BswIdentificaciones b WHERE b.abreviatura = :abreviatura"),
    @NamedQuery(name = "BswIdentificaciones.findByCodUsuarioAud", query = "SELECT b FROM BswIdentificaciones b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswIdentificaciones implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "bsw_documento_ident_id_seq", sequenceName = "bsw_documento_ident_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bsw_documento_ident_id_seq")
    @Column(name = "ID")
    private Long id;
    @Column(name = "COD_IDENT")
    private String codIdent;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "MASCARA")
    private String mascara;
    @Column(name = "ABREVIATURA")
    private String abreviatura;
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
    public BswIdentificaciones() {
    }

    public BswIdentificaciones(Long id) {
        this.id = id;
    }

    public BswIdentificaciones(Long id, String codIdent) {
        this.id = id;
        this.codIdent = codIdent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodIdent() {
        return codIdent;
    }

    public void setCodIdent(String codIdent) {
        this.codIdent = codIdent;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BswIdentificaciones)) {
            return false;
        }
        BswIdentificaciones other = (BswIdentificaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswIdentificaciones[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.codIdent;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codIdent=codigo;
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
}
