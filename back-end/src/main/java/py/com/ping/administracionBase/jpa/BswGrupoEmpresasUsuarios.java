/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import py.com.ping.listener.BusinessListener;
//import py.com.ping.tesoreria.jpa.TswCuotas;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author Usuario
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "bsw_grupo_empresas_usuarios", catalog = "", schema = "public")
public class BswGrupoEmpresasUsuarios implements Serializable, EntidadCodigo, EntidadABM {

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "empresa_id", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @JoinColumn(name = "usuario_id", referencedColumnName = "ID")
    @ManyToOne
    private BswUsuarios bswUsuarios;
    @Column(name = "user_audit_id")
    private Integer userAuditID;
    @OrderBy(value = "descripcion ASC")
    @OneToMany(mappedBy = "bswGrupoEmpresas")
    private List<BswEmpresas> empresasVinculadasList;
    @Column(name = "nombre", length = 25)
    private String nombre;
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    public String getIpCliente() {
        return ipCliente;
    }

    @Override
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }
    @Column(name = "id_session", length = 65)
    private String idSession;

    public String getIdSession() {
        return idSession;
    }

    @Override
    public void setIdSession(String idSession) {
        this.idSession = idSession;
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
    public Object getPK() {
        return this.id;
    }

    @Override
    public void setPK(Object id) {
        this.id = (Integer) id;
    }

    @Override
    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
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
    public Integer getUserAuditID() {
        return userAuditID;
    }

    /**
     * @return the bswUsuarios
     */
    public BswUsuarios getBswUsuarios() {
        return bswUsuarios;
    }

    /**
     * @param bswUsuarios the bswUsuarios to set
     */
    public void setBswUsuarios(BswUsuarios bswUsuarios) {
        this.bswUsuarios = bswUsuarios;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     /**
     * @return the empresasVinculadasList
     */
    public List<BswEmpresas> getEmpresasVinculadasList() {
        return empresasVinculadasList;
    }

    /**
     * @param empresasVinculadasList the empresasVinculadasList to set
     */
    public void setEmpresasVinculadasList(List<BswEmpresas> empresasVinculadasList) {
        this.empresasVinculadasList = empresasVinculadasList;
    }

    
    
    

}
