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
import javax.validation.constraints.NotNull;
import py.com.ping.administracionBase.validation.util.MaxSize;
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
@Table(name = "BSW_PARAMETROS_GENERALES", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PARAMETRO", "ID_MODULO"})})
@NamedQueries({
    @NamedQuery(name = "BswParametrosGenerales.findAll", query = "SELECT b FROM BswParametrosGenerales b"),
    @NamedQuery(name = "BswParametrosGenerales.findById", query = "SELECT b FROM BswParametrosGenerales b WHERE b.id = :id"),
    @NamedQuery(name = "BswParametrosGenerales.findByParametro", query = "SELECT b FROM BswParametrosGenerales b WHERE b.parametro = :parametro"),
    @NamedQuery(name = "BswParametrosGenerales.getPorCodigo", query = "SELECT b FROM BswParametrosGenerales b WHERE b.parametro = :codigo"),
    @NamedQuery(name = "BswParametrosGenerales.findByDescripcion", query = "SELECT b FROM BswParametrosGenerales b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswParametrosGenerales.findByValor", query = "SELECT b FROM BswParametrosGenerales b WHERE b.valor = :valor"),
    @NamedQuery(name = "BswParametrosGenerales.findByCodUsuarioAud", query = "SELECT b FROM BswParametrosGenerales b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswParametrosGenerales implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "PARAMETROS_SEQ_GENERATOR", sequenceName = "BSW_PARAM_GRAL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETROS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @NotNullString(campoNombre = "Parametro", groups = AbstractControlerValidationGroups.class)
    @Basic(optional = false)
    @MaxSize(campoNombre = "Parametro", tamañoMaximo = 60, groups = AbstractControlerValidationGroups.class)
    @Column(name = "PARAMETRO", nullable = false, length = 60)
    private String parametro;
    @MaxSize(campoNombre = "Descripcion", tamañoMaximo = 240, groups = AbstractControlerValidationGroups.class)
    @Column(name = "DESCRIPCION", length = 240)
    private String descripcion;
    @MaxSize(campoNombre = "Valor", tamañoMaximo = 80, groups = AbstractControlerValidationGroups.class)
    @NotNullString(campoNombre = "Valor", groups = AbstractControlerValidationGroups.class)
    @Column(name = "VALOR", length = 80)
    private String valor;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @NotNull(message = "Modulos es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswModulos bswModulos;

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

    public BswParametrosGenerales() {
    }

    public BswParametrosGenerales(Long id) {
        this.id = id;
    }

    public BswParametrosGenerales(Long id, String parametro) {
        this.id = id;
        this.parametro = parametro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro.toUpperCase();
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    public BswModulos getBswModulos() {
        return bswModulos;
    }

    public void setBswModulos(BswModulos bswModulos) {
        this.bswModulos = bswModulos;
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
        if (!(object instanceof BswParametrosGenerales)) {
            return false;
        }
        BswParametrosGenerales other = (BswParametrosGenerales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswParametrosGenerales[id=" + id + "]";
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
    public String getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
