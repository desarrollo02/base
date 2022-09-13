/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author Usuario
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "bsw_rol_usuario_sucursal")

@NamedQueries({
    @NamedQuery(name = "BswRolUsuarioSucursal.findAll", query = "SELECT b FROM BswRolUsuarioSucursal b"),
    @NamedQuery(name = "BswRolUsuarioSucursal.findById", query = "SELECT b FROM BswRolUsuarioSucursal b WHERE b.id = :id")})
public class BswRolUsuarioSucursal implements Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private BswUsuarios bswUsuarios;
    @NotNull(message = "Sucursal es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id")
    @ManyToOne
    private BswSucursales bswSucursales;
    @JoinColumn(name = "id_grupo", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private BswGruposUsuarios bswGruposUsuarios;

    @Column(name = "firmante")
    private Boolean firmante;

    @Column(name = "cargo", length = 500)
    private String cargo;

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

    public BswRolUsuarioSucursal() {
    }

    public BswRolUsuarioSucursal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public Boolean getFirmante() {
        return firmante == null ? false : firmante;
    }

    public void setFirmante(Boolean firmante) {
        this.firmante = firmante;
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
        if (!(object instanceof BswRolUsuarioSucursal)) {
            return false;
        }
        BswRolUsuarioSucursal other = (BswRolUsuarioSucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal[ id=" + id + " ]";
    }

    public BswUsuarios getBswUsuarios() {
        return bswUsuarios;
    }

    public void setBswUsuarios(BswUsuarios bswUsuarios) {
        this.bswUsuarios = bswUsuarios;
    }

    public BswSucursales getBswSucursales() {
        return bswSucursales;
    }

    public void setBswSucursales(BswSucursales bswSucursales) {
        this.bswSucursales = bswSucursales;
    }

    public BswGruposUsuarios getBswGruposUsuarios() {
        return bswGruposUsuarios;
    }

    public void setBswGruposUsuarios(BswGruposUsuarios bswGruposUsuarios) {
        this.bswGruposUsuarios = bswGruposUsuarios;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
