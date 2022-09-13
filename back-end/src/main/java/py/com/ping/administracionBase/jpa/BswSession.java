/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author Rudy
 */
@Entity

@Table(name = "bsw_session")

@NamedQueries({
    @NamedQuery(name = "BswSession.findAll", query = "SELECT b FROM BswSession b")
    , @NamedQuery(name = "BswSession.findById", query = "SELECT b FROM BswSession b WHERE b.id = :id")
    , @NamedQuery(name = "BswSession.findByIdUser", query = "SELECT b FROM BswSession b WHERE b.idUser = :idUser")
    , @NamedQuery(name = "BswSession.findByCodUser", query = "SELECT b FROM BswSession b WHERE b.codUser = :codUser")
    , @NamedQuery(name = "BswSession.findByNombreUser", query = "SELECT b FROM BswSession b WHERE b.nombreUser = :nombreUser")
    , @NamedQuery(name = "BswSession.findByIdSession", query = "SELECT b FROM BswSession b WHERE b.idSession = :idSession")})
public class BswSession implements Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_user")
    private Long idUser;
    @Size(max = 10)
    @Column(name = "cod_user")
    private String codUser;
    @Size(max = 65)
    @Column(name = "nombre_user")
    private String nombreUser;
    @Size(max = 65)
    @Column(name = "id_session")
    private String idSession;

    @Column(name = "fecha_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogin;

    @Column(name = "fecha_logout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogout;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    @Column(name = "host_name", length = 150)
    private String hostName;

    public BswSession() {
    }

    public BswSession(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getIdSession() {
        return idSession;
    }

    @Override
    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public Date getFechaLogout() {
        return fechaLogout;
    }

    public void setFechaLogout(Date fechaLogout) {
        this.fechaLogout = fechaLogout;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    @Override
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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
        if (!(object instanceof BswSession)) {
            return false;
        }
        BswSession other = (BswSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.BswSession[ id=" + id + " ]";
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

    @Override
    public Integer getUserAuditID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUserAuditID(Integer userAuditID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
