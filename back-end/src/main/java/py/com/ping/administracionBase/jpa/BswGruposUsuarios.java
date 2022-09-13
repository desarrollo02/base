/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "BSW_GRUPOS_USUARIOS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_GRUPO"})})
@NamedQueries({
    @NamedQuery(name = "BswGruposUsuarios.findAll", query = "SELECT b FROM BswGruposUsuarios b"),
    @NamedQuery(name = "BswGruposUsuarios.findById", query = "SELECT b FROM BswGruposUsuarios b WHERE b.id = :id"),
    @NamedQuery(name = "BswGruposUsuarios.findByCodGrupo", query = "SELECT b FROM BswGruposUsuarios b WHERE b.codGrupo = :codGrupo"),
    @NamedQuery(name = "BswGruposUsuarios.findByBswEmpresas", query = "SELECT b FROM BswGruposUsuarios b WHERE b.bswEmpresas.id = :idEmpresa"),
    @NamedQuery(name = "BswGruposUsuarios.findByDescripcion", query = "SELECT b FROM BswGruposUsuarios b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswGruposUsuarios.findByCodUsuarioAud", query = "SELECT b FROM BswGruposUsuarios b WHERE b.codUsuarioAud = :codUsuarioAud")})

public class BswGruposUsuarios implements Serializable, EntidadCodigo, EntidadABM {

   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "GRUPOS_USUARIOS_SEQ_GENERATOR", sequenceName = "BSW_GRUPOS_USUARIOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOS_USUARIOS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_GRUPO", nullable = false, length = 6)
    @NotNullString(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class)
    @MaxSize(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class, tamañoMaximo = 6)
    private String codGrupo;
    @Column(name = "DESCRIPCION", length = 40)
    @MaxSize(campoNombre = "Descripcion", groups = AbstractControlerValidationGroups.class, tamañoMaximo = 40)
    @NotNullString(campoNombre = "Descripcion", groups = AbstractControlerValidationGroups.class)
    private String descripcion;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @Column(name = "PASOS_PROCESO", length = 150)
    private String pasosProcesos;
    @Column(name = "PASOS_PROCESO_CONTRATOS", length = 150)
    private String pasosProcesosContratos;
    @OneToMany(mappedBy = "bswGruposUsuarios")
    private Set<BswUsuarios> bswUsuariosSet;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @JoinColumn(name = "id_empresa_referencia", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresasReferencia;
    @OneToMany(mappedBy = "bswGruposUsuarios", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BswAccesosGrupos> bswAccesosGrupos;

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

    public BswGruposUsuarios() {
    }

    public BswGruposUsuarios(Long id) {
        this.id = id;
    }

    public BswGruposUsuarios(Long id, String codGrupo) {
        this.id = id;
        this.codGrupo = codGrupo;
    }

    public BswGruposUsuarios(BswGruposUsuarios ent, BswEmpresas emp) {
        this.codGrupo = ent.codGrupo;
        this.descripcion = ent.descripcion;
        this.bswEmpresas = emp;
        this.bswEmpresasReferencia = ent.getBswEmpresas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
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

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    
    public Set<BswUsuarios> getBswUsuariosSet() {
        return bswUsuariosSet;
    }

    public void setBswUsuariosSet(Set<BswUsuarios> bswUsuariosSet) {
        this.bswUsuariosSet = bswUsuariosSet;
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
        if (!(object instanceof BswGruposUsuarios)) {
            return false;
        }
        BswGruposUsuarios other = (BswGruposUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswGruposUsuarios[id=" + id + "]";
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
        return this.codGrupo;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codGrupo = codigo;
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

    /**
     * @return the pasosProcesos
     */
    public String getPasosProcesos() {
        return pasosProcesos;
    }

    /**
     * @param pasosProcesos the pasosProcesos to set
     */
    public void setPasosProcesos(String pasosProcesos) {
        this.pasosProcesos = pasosProcesos;
    }

    /**
     * @return the pasosProcesosContratos
     */
    public String getPasosProcesosContratos() {
        return pasosProcesosContratos;
    }

    /**
     * @param pasosProcesosContratos the pasosProcesosContratos to set
     */
    public void setPasosProcesosContratos(String pasosProcesosContratos) {
        this.pasosProcesosContratos = pasosProcesosContratos;
    }

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }

    public BswEmpresas getBswEmpresasReferencia() {
        return bswEmpresasReferencia;
    }

    public void setBswEmpresasReferencia(BswEmpresas bswEmpresasReferencia) {
        this.bswEmpresasReferencia = bswEmpresasReferencia;
    }
    
    
     /**
     * @return the bswGruposUsuarios
     */
    public List<BswAccesosGrupos> getBswAccesosGrupos() {
        return bswAccesosGrupos;
    }

    /**
     * @param bswAccesosGrupos
     */
    public void setBswGruposUsuarios(List<BswAccesosGrupos> bswAccesosGrupos) {
        this.bswAccesosGrupos = bswAccesosGrupos;
    }


}
