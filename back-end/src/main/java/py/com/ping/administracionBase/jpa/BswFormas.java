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
import javax.persistence.Transient;
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
@Table(name = "BSW_FORMAS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_MODULO", "NOM_FORMA"})})
@NamedQueries({
    @NamedQuery(name = "BswFormas.getPorCodigo", query = "SELECT b FROM BswFormas b WHERE b.nomForma = :codigo")
    ,
  @NamedQuery(name = "BswFormas.findAll", query = "SELECT b FROM BswFormas b")
    ,
  @NamedQuery(name = "BswFormas.findById", query = "SELECT b FROM BswFormas b WHERE b.id = :id")
    ,
  @NamedQuery(name = "BswFormas.findByNomForma", query = "SELECT b FROM BswFormas b WHERE b.nomForma = :nomForma")
    ,
  @NamedQuery(name = "BswFormas.findByTitulo", query = "SELECT b FROM BswFormas b WHERE b.titulo = :titulo")
    ,
  @NamedQuery(name = "BswFormas.findByDescripcion", query = "SELECT b FROM BswFormas b WHERE b.descripcion = :descripcion")
    ,
  @NamedQuery(name = "BswFormas.findByTipo", query = "SELECT b FROM BswFormas b WHERE b.tipo = :tipo")
    ,
  @NamedQuery(name = "BswFormas.count", query = "SELECT count(b) FROM BswFormas b")
    ,
  @NamedQuery(name = "BswFormas.findByCodUsuarioAud", query = "SELECT b FROM BswFormas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswFormas implements Serializable, EntidadCodigo, EntidadABM {



    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "FORMAS_SEQ_GENERATOR", sequenceName = "BSW_FORMAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOM_FORMA", nullable = false, length = 12)
    @MaxSize(campoNombre = "Nom.Forma", groups = AbstractControlerValidationGroups.class, tamañoMaximo = 12)
    @NotNullString(campoNombre = "Nom.Forma", groups = AbstractControlerValidationGroups.class)
    private String nomForma;
    @MaxSize(campoNombre = "Titulo", groups = AbstractControlerValidationGroups.class, tamañoMaximo = 100)
    @Column(name = "TITULO", length = 100)
    private String titulo;

    @MaxSize(campoNombre = "Descripcion", groups = AbstractControlerValidationGroups.class, tamañoMaximo = 80)
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "TIPO", length = 1)
    private String tipo;
    @Column(name = "link", length = 120)
    private String link;
    @Column(name = "orden", length = 120)
    private Integer orden;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    @NotNull(message = "Campo Modulo es requerido.", groups = AbstractControlerValidationGroups.class)
    private BswModulos bswModulos;

    @Column(name = "user_audit_id")
    private Integer userAuditID;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;
    
    @JoinColumn(name = "tipo_forma_id", referencedColumnName = "id")
    @ManyToOne
    private BswTipoForma bswTipoForma;
    
    
    @Transient
    private boolean puedeInsertarABM;
    @Transient
    private boolean puedeConsultarABM;
    @Transient
    private boolean puedeEliminarABM;
    
    @Transient
    private boolean puedeModificarABM;

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

    public BswFormas() {
    }

    public BswFormas(Long id) {
        this.id = id;
    }

    public BswFormas(Long id, String nomForma) {
        this.id = id;
        this.nomForma = nomForma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomForma() {
        return nomForma;
    }

    public void setNomForma(String nomForma) {
        this.nomForma = nomForma;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof BswFormas)) {
            return false;
        }
        BswFormas other = (BswFormas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswFormas[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.getNomForma();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setNomForma(codigo);
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

    public BswTipoForma getBswTipoForma() {
        return bswTipoForma;
    }

    public void setBswTipoForma(BswTipoForma bswTipoForma) {
        this.bswTipoForma = bswTipoForma;
    }

    public boolean isPuedeInsertarABM() {
        return puedeInsertarABM;
    }

    public void setPuedeInsertarABM(boolean puedeInsertarABM) {
        this.puedeInsertarABM = puedeInsertarABM;
    }

    public boolean isPuedeConsultarABM() {
        return puedeConsultarABM;
    }

    public void setPuedeConsultarABM(boolean puedeConsultarABM) {
        this.puedeConsultarABM = puedeConsultarABM;
    }

    public boolean isPuedeEliminarABM() {
        return puedeEliminarABM;
    }

    public void setPuedeEliminarABM(boolean puedeEliminarABM) {
        this.puedeEliminarABM = puedeEliminarABM;
    }

    public boolean isPuedeModificarABM() {
        return puedeModificarABM;
    }

    public void setPuedeModificarABM(boolean puedeModificarABM) {
        this.puedeModificarABM = puedeModificarABM;
    }
    
        /**
     * @return the orden
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

}
