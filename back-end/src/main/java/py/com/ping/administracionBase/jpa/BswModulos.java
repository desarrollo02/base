/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.Transient;
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
@Table(name = "BSW_MODULOS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_MODULO"})})
@NamedQueries({
    @NamedQuery(name = "BswModulos.getPorCodigo", query = "SELECT b FROM BswModulos b WHERE b.codModulo = :codigo"),
    @NamedQuery(name = "BswModulos.findAll", query = "SELECT b FROM BswModulos b order by b.descripcion"),
    @NamedQuery(name = "BswModulos.findById", query = "SELECT b FROM BswModulos b WHERE b.id = :id"),
    @NamedQuery(name = "BswModulos.findByCodModulo", query = "SELECT b FROM BswModulos b WHERE b.codModulo = :codModulo"),
    @NamedQuery(name = "BswModulos.findByCodModuloEmpresa", query = "SELECT b FROM BswModulos b WHERE b.codModulo = :codModulo and b.bswEmpresas.id=:empresa"),
    @NamedQuery(name = "BswModulos.findByDescripcion", query = "SELECT b FROM BswModulos b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswModulos.findByManejaCalendario", query = "SELECT b FROM BswModulos b WHERE b.manejaCalendario = :manejaCalendario"),
    @NamedQuery(name = "BswModulos.findByManejaCierre", query = "SELECT b FROM BswModulos b WHERE b.manejaCierre = :manejaCierre"),
    @NamedQuery(name = "BswModulos.findByCodUsuarioAud", query = "SELECT b FROM BswModulos b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswModulos implements Serializable, EntidadCodigo, EntidadABM {

    /**
     * @return the marcarTodosPermisos
     */
    public boolean isMarcarTodosPermisos() {
        return marcarTodosPermisos;
    }

    /**
     * @param marcarTodosPermisos the marcarTodosPermisos to set
     */
    public void setMarcarTodosPermisos(boolean marcarTodosPermisos) {
        this.marcarTodosPermisos = marcarTodosPermisos;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "MODULOS_SEQ_GENERATOR", sequenceName = "BSW_MODULOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODULOS_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @NotNullString(groups = AbstractControlerValidationGroups.class, campoNombre = "Codigo")
    @MaxSize(campoNombre = "Cod. Modulo", tamañoMaximo = 2)
    @Column(name = "COD_MODULO", length = 2)
    private String codModulo;
    @NotNullString(groups = AbstractControlerValidationGroups.class, campoNombre = "Descripcion")
    @MaxSize(campoNombre = "Descripcion", tamañoMaximo = 80)
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "MANEJA_CALENDARIO", length = 1)
    private String manejaCalendario;
    @Column(name = "MANEJA_CIERRE", length = 1)
    private String manejaCierre;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswModulos")
    private Set<BswPermisosOpciones> bswPermisosOpcionesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswModulos")
    private Set<BswPermisosFormas> bswPermisosFormasSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswModulos")
    private Set<BswParametrosGenerales> bswParametrosGeneralesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswModulos")
    private Set<BswAccesosGrupos> bswAccesosGruposSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswModulos")
    private Set<BswFormas> bswFormasSet;
    @Transient
    private boolean marcarTodosPermisos;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @JoinColumn(name = "id_empresa_referencia", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresasReferencia;
    @Column(name = "ESTADO", length = 1)
    private String estado = "A";

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
    @Transient
    private boolean activoABM;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public BswModulos() {
    }

    public BswModulos(Long id) {
        this.id = id;
    }

    public BswModulos(BswModulos ent, BswEmpresas emp) {
        this.bswEmpresas = emp;
        this.bswEmpresasReferencia = ent.getBswEmpresas();
        this.codModulo = ent.getCodModulo();
        this.descripcion = ent.getDescripcion();
    }

    public BswModulos(Long id, String codModulo) {
        this.id = id;
        this.codModulo = codModulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(String codModulo) {
        this.codModulo = codModulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public String getManejaCalendario() {
        return manejaCalendario;
    }

    public void setManejaCalendario(String manejaCalendario) {
        this.manejaCalendario = manejaCalendario;
    }

    public String getManejaCierre() {
        return manejaCierre;
    }

    public void setManejaCierre(String manejaCierre) {
        this.manejaCierre = manejaCierre;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    public Set<BswPermisosOpciones> getBswPermisosOpcionesSet() {
        return bswPermisosOpcionesSet;
    }

    public void setBswPermisosOpcionesSet(Set<BswPermisosOpciones> bswPermisosOpcionesSet) {
        this.bswPermisosOpcionesSet = bswPermisosOpcionesSet;
    }

    public Set<BswPermisosFormas> getBswPermisosFormasSet() {
        return bswPermisosFormasSet;
    }

    public void setBswPermisosFormasSet(Set<BswPermisosFormas> bswPermisosFormasSet) {
        this.bswPermisosFormasSet = bswPermisosFormasSet;
    }

    public Set<BswParametrosGenerales> getBswParametrosGeneralesSet() {
        return bswParametrosGeneralesSet;
    }

    public void setBswParametrosGeneralesSet(Set<BswParametrosGenerales> bswParametrosGeneralesSet) {
        this.bswParametrosGeneralesSet = bswParametrosGeneralesSet;
    }

    public Set<BswAccesosGrupos> getBswAccesosGruposSet() {
        return bswAccesosGruposSet;
    }

    public void setBswAccesosGruposSet(Set<BswAccesosGrupos> bswAccesosGruposSet) {
        this.bswAccesosGruposSet = bswAccesosGruposSet;
    }

    public Set<BswFormas> getBswFormasSet() {
        return bswFormasSet;
    }

    public void setBswFormasSet(Set<BswFormas> bswFormasSet) {
        this.bswFormasSet = bswFormasSet;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActivoABM() {
        return (estado != null && "A".equals(estado));
    }

    public void setActivoABM(boolean activoABM) {
        this.activoABM = activoABM;
        if (activoABM) {
            estado = "A";
        } else {
            estado = "I";
        }

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
        if (!(object instanceof BswModulos)) {
            return false;
        }
        BswModulos other = (BswModulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswModulos[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.getCodModulo();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setCodModulo(codigo);
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

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
