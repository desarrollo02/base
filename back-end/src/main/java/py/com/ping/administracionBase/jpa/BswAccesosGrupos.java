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
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_ACCESOS_GRUPOS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_GRUPO", "ID_MODULO", "ID_FORMA"})})
@NamedQueries({
    @NamedQuery(name = "BswAccesosGrupos.findAll", query = "SELECT b FROM BswAccesosGrupos b order by b.bswGruposUsuarios.codGrupo"),
    @NamedQuery(name = "BswAccesosGrupos.findById", query = "SELECT b FROM BswAccesosGrupos b WHERE b.id = :id"),
    @NamedQuery(name = "BswAccesosGrupos.findByNomForma", query = "SELECT b FROM BswAccesosGrupos b WHERE b.bswFormas.nomForma = :nomForma"),
    @NamedQuery(name = "BswAccesosGrupos.findByPuedeInsertar", query = "SELECT b FROM BswAccesosGrupos b WHERE b.puedeInsertar = :puedeInsertar"),
    @NamedQuery(name = "BswAccesosGrupos.findByPuedeBorrar", query = "SELECT b FROM BswAccesosGrupos b WHERE b.puedeBorrar = :puedeBorrar"),
    @NamedQuery(name = "BswAccesosGrupos.findByPuedeActualizar", query = "SELECT b FROM BswAccesosGrupos b WHERE b.puedeActualizar = :puedeActualizar"),
    @NamedQuery(name = "BswAccesosGrupos.findByPuedeConsultar", query = "SELECT b FROM BswAccesosGrupos b WHERE b.puedeConsultar = :puedeConsultar"),
    @NamedQuery(name = "BswAccesosGrupos.findByItemMenu", query = "SELECT b FROM BswAccesosGrupos b WHERE b.itemMenu = :itemMenu"),
    @NamedQuery(name = "BswAccesosGrupos.findByBswEmpresas", query = "SELECT b FROM BswAccesosGrupos b WHERE b.bswEmpresas.id = :idEmpresa"),
    @NamedQuery(name = "BswAccesosGrupos.findByNomFormaAndCodGrupo", query = "SELECT b FROM BswAccesosGrupos b WHERE b.bswFormas.nomForma = :nomForma and b.bswGruposUsuarios.codGrupo = :codGrupo and b.bswEmpresas.id = :idEmpresa and b.bswModulos.bswEmpresas.id = :idEmpresa and b.bswModulos.estado = 'A'"),
    @NamedQuery(name = "BswAccesosGrupos.count", query = "SELECT count(b) FROM BswAccesosGrupos b"),
    @NamedQuery(name = "BswAccesosGrupos.findByCodGrupoAndCodModAndbswEmpresas", query = "SELECT b FROM BswAccesosGrupos b WHERE b.puedeConsultar = :puedeConsultar and  b.bswGruposUsuarios.codGrupo = :codGrupo and b.bswModulos.bswEmpresas.id = :idEmpresa and b.bswModulos.codModulo = :codModulo and b.bswEmpresas.id = :idEmpresa and b.bswModulos.estado = 'A'"),
    //@NamedQuery(name = "BswAccesosGrupos.bswfindByCodGrupo", query = "select b from BswAccesosGrupos as b, BswGruposUsuarios as c inner join c using (b.idGrupo)"),
    @NamedQuery(name = "BswAccesosGrupos.findByCodUsuarioAud", query = "SELECT b FROM BswAccesosGrupos b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswAccesosGrupos implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "ACCESOS_GRUPOS_SEQ_GENERATOR", sequenceName = "BSW_ACCESOS_GRUPOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESOS_GRUPOS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "PUEDE_INSERTAR", length = 1)
    private String puedeInsertar;
    @Column(name = "PUEDE_BORRAR", length = 1)
    private String puedeBorrar;
    @Column(name = "PUEDE_ACTUALIZAR", length = 1)
    private String puedeActualizar;
    @Column(name = "PUEDE_CONSULTAR", length = 1)
    private String puedeConsultar;
    @Column(name = "ITEM_MENU", length = 50)
    private String itemMenu;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @NotNull(message = "Modulos es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswModulos bswModulos;
    @NotNull(message = "Grupos es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswGruposUsuarios bswGruposUsuarios;
    @NotNull(message = "Forma es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_FORMA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private BswFormas bswFormas;

    @Column(name = "user_audit_id")
    private Integer userAuditID;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    @Column(name = "id_session", length = 65)
    private String idSession;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @JoinColumn(name = "id_empresa_referencia", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresasReferencia;

    @Transient
    private boolean puedeInsertarABM;
    @Transient
    private boolean puedeConsultarABM;
    @Transient
    private boolean puedeEliminarABM;

    @Transient
    private boolean puedeModificarABM;

    
    
    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    

    public BswAccesosGrupos() {
    }

    public BswAccesosGrupos(Long id) {
        this.id = id;
    }

    public BswAccesosGrupos(BswAccesosGrupos entidad, BswEmpresas empresaD, BswModulos bswModulos, BswGruposUsuarios gruposUsuarios) {
        this.bswEmpresas = empresaD;
        this.bswEmpresasReferencia = entidad.bswEmpresas;
        this.bswGruposUsuarios = gruposUsuarios;
        this.bswFormas = entidad.getBswFormas();
        this.bswModulos = bswModulos;
        this.puedeActualizar = entidad.getPuedeActualizar();
        this.puedeBorrar = entidad.getPuedeBorrar();
        this.puedeConsultar = entidad.getPuedeConsultar();
        this.puedeInsertar = entidad.getPuedeInsertar();

    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }


    public BswAccesosGrupos(Long id, String nomForma) {
        this.id = id;
        //this.nomForma = nomForma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getNomForma() {
//        return nomForma;
//    }
//
//    public void setNomForma(String nomForma) {
//        this.nomForma = nomForma;
//    }
    public String getPuedeInsertar() {
        return puedeInsertar;
    }

    public void setPuedeInsertar(String puedeInsertar) {
        this.puedeInsertar = puedeInsertar;
    }

    public String getPuedeBorrar() {
        return puedeBorrar;
    }

    public void setPuedeBorrar(String puedeBorrar) {
        this.puedeBorrar = puedeBorrar;
    }

    public String getPuedeActualizar() {
        return puedeActualizar;
    }

    public void setPuedeActualizar(String puedeActualizar) {
        this.puedeActualizar = puedeActualizar;
    }

    public String getPuedeConsultar() {
        return puedeConsultar;
    }

    public void setPuedeConsultar(String puedeConsultar) {
        this.puedeConsultar = puedeConsultar;
    }

    public String getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(String itemMenu) {
        this.itemMenu = itemMenu;
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

    public BswGruposUsuarios getBswGruposUsuarios() {
        return bswGruposUsuarios;
    }

    public void setBswGruposUsuarios(BswGruposUsuarios bswGruposUsuarios) {
        this.bswGruposUsuarios = bswGruposUsuarios;
    }

    public BswFormas getBswFormas() {
        return bswFormas;
    }

    public void setBswFormas(BswFormas bswFormas) {
        this.bswFormas = bswFormas;
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
        if (!(object instanceof BswAccesosGrupos)) {
            return false;
        }
        BswAccesosGrupos other = (BswAccesosGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.inv.invweb.base.jpa.BswAccesosGrupos[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public String getIpCliente() {
        return ipCliente;
    }

    @Override
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public boolean isPuedeInsertarABM() {
        return (puedeInsertar != null && "S".equals(puedeInsertar));
    }

    public void setPuedeInsertarABM(boolean puedeInsertarABM) {
        this.puedeInsertarABM = puedeInsertarABM;
        if (puedeInsertarABM) {
            puedeInsertar = "S";
        } else {
            puedeInsertar = "N";
        }

    }

    public boolean isPuedeConsultarABM() {
        return (puedeConsultar != null && "S".equals(puedeConsultar));

    }

    public void setPuedeConsultarABM(boolean puedeConsultarABM) {
        this.puedeConsultarABM = puedeConsultarABM;
        if (puedeConsultarABM) {
            puedeConsultar = "S";
        } else {
            puedeConsultar = "N";
        }

    }

    public boolean isPuedeEliminarABM() {
        return (puedeBorrar != null && "S".equals(puedeBorrar));

    }

    public void setPuedeEliminarABM(boolean puedeEliminarABM) {
        this.puedeEliminarABM = puedeEliminarABM;
        if (puedeEliminarABM) {
            puedeBorrar = "S";
        } else {
            puedeBorrar = "N";
        }

    }

    public boolean isPuedeModificarABM() {
        return (puedeActualizar != null && "S".equals(puedeActualizar));
    }

    public void setPuedeModificarABM(boolean puedeModificarABM) {
        this.puedeModificarABM = puedeModificarABM;
        if (puedeModificarABM) {
            puedeActualizar = "S";
        } else {
            puedeActualizar = "N";
        }

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

}
