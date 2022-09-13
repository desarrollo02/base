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
@Table(name = "BSW_OPCIONES_PARAMETROS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PARAMETRO"})})
@NamedQueries({
     @NamedQuery(name = "BswOpcionesParametros.getPorCodigo", query = "SELECT b FROM BswOpcionesParametros b WHERE b.parametro = :codigo"),
    @NamedQuery(name = "BswOpcionesParametros.findAll", query = "SELECT b FROM BswOpcionesParametros b"),
    @NamedQuery(name = "BswOpcionesParametros.findById", query = "SELECT b FROM BswOpcionesParametros b WHERE b.id = :id"),
    @NamedQuery(name = "BswOpcionesParametros.findByParametro", query = "SELECT b FROM BswOpcionesParametros b WHERE b.parametro = :parametro"),
    @NamedQuery(name = "BswOpcionesParametros.findByCodUsuarioAud", query = "SELECT b FROM BswOpcionesParametros b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswOpcionesParametros implements Serializable, EntidadCodigo , EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "OPC_PARAMETROS_SEQ_GENERATOR", sequenceName = "BSW_OPCIONES_PARAMETROS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPC_PARAMETROS_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    
    @NotNullString(campoNombre = "Parametro", groups = AbstractControlerValidationGroups.class)
    @Basic(optional = false)
    @MaxSize(campoNombre = "Parametro", tamañoMaximo = 60, groups = AbstractControlerValidationGroups.class)
    @Column(name = "PARAMETRO", nullable = false, length = 25)
    private String parametro;
    
    @NotNull(message = "Fomulario es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_FORMA", referencedColumnName = "ID")
    @ManyToOne
    private BswFormas bswFormas;
    
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
    
    
    public BswOpcionesParametros() {
    }

    public BswOpcionesParametros(Long id) {
        this.id = id;
    }

    public BswOpcionesParametros(Long id, String parametro) {
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
        this.parametro = parametro;
    }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    

    public BswFormas getBswFormas() {
        return bswFormas;
    }

    public void setBswFormas(BswFormas bswFormas) {
        this.bswFormas = bswFormas;
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
        if (!(object instanceof BswOpcionesParametros)) {
            return false;
        }
        BswOpcionesParametros other = (BswOpcionesParametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswOpcionesParametros[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return getParametro();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setParametro(codigo);
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
        throw new UnsupportedOperationException("Not supported yet.");
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

    
    public String getDescripcion(){
        return this.parametro;
    }
    
}
