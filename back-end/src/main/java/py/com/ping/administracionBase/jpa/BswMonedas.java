/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/*import py.com.invweb.py.com.ping.cuentaCobrar.jpa.CmwProveedores;
import py.com.invweb.contabilidad.jpa.CowPlanCuentas;
import py.com.invweb.stock.jpa.*;
import py.com.ping.utilitarios.jpa.Entidad;
import py.com.invweb.ventas.jpa.VtwAutorizaVenta;*/

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_MONEDAS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_MONEDA"})})
@NamedQueries({
    @NamedQuery(name = "BswMonedas.findAll", query = "SELECT b FROM BswMonedas b"),
    @NamedQuery(name = "BswMonedas.findById", query = "SELECT b FROM BswMonedas b WHERE b.id = :id"),
    @NamedQuery(name = "BswMonedas.findByCodMoneda", query = "SELECT b FROM BswMonedas b WHERE b.codMoneda = :codMoneda"),
    @NamedQuery(name = "BswMonedas.getPorCodigo", query = "SELECT b FROM BswMonedas b WHERE b.codMoneda = :codigo"),
    @NamedQuery(name = "BswMonedas.findByDescripcion", query = "SELECT b FROM BswMonedas b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswMonedas.count", query = "SELECT count(b) FROM BswMonedas b"),
    @NamedQuery(name = "BswMonedas.findBySiglas", query = "SELECT b FROM BswMonedas b WHERE b.siglas = :siglas"),
    @NamedQuery(name = "BswMonedas.findByTipoCambioDia", query = "SELECT b FROM BswMonedas b WHERE b.tipoCambioDia = :tipoCambioDia"),
    @NamedQuery(name = "BswMonedas.findByDecimales", query = "SELECT b FROM BswMonedas b WHERE b.decimales = :decimales"),
    @NamedQuery(name = "BswMonedas.findByFecTipoCambio", query = "SELECT b FROM BswMonedas b WHERE b.fecTipoCambio = :fecTipoCambio"),
    @NamedQuery(name = "BswMonedas.findByTipoCambio", query = "SELECT b FROM BswMonedas b WHERE b.tipoCambio = :tipoCambio"),
    @NamedQuery(name = "BswMonedas.findByTipoCambioCompra", query = "SELECT b FROM BswMonedas b WHERE b.tipoCambioCompra = :tipoCambioCompra"),
    @NamedQuery(name = "BswMonedas.findByCodUsuarioAud", query = "SELECT b FROM BswMonedas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswMonedas implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "MONEDA_SEQ_GENERATOR", sequenceName = "BSW_MONEDAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MONEDA_SEQ_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COD_MONEDA", nullable = false, length = 4)
    @NotNullString(groups = AbstractControlerValidationGroups.class, campoNombre = "Codigo")
    private String codMoneda;
    @Column(name = "DESCRIPCION", length = 60)
    @NotNullString(groups = AbstractControlerValidationGroups.class, campoNombre = "Descripcion")
    private String descripcion;
    @Column(name = "SIGLAS", length = 4)
    private String siglas;
    @Column(name = "TIPO_CAMBIO_DIA", precision = 20, scale = 6)
    private BigDecimal tipoCambioDia;
    @Column(name = "DECIMALES")
    private Short decimales;
    @Column(name = "FEC_TIPO_CAMBIO")
    @Temporal(TemporalType.DATE)
    private Date fecTipoCambio;
    @Column(name = "TIPO_CAMBIO", precision = 20, scale = 6)
    @NotNull(message = "TIPO_CAMBIO", groups = AbstractControlerValidationGroups.class)
    private BigDecimal tipoCambio;
    @Column(name = "TIPO_CAMBIO_COMPRA", precision = 20, scale = 6)
    private BigDecimal tipoCambioCompra;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    /*    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwHistCostosArt> stwHistCostosArtSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwHistPrecios> stwHistPreciosSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwEntsalCab> stwEntsalCabSet;*/
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
    @ManyToOne
    private BswPaises bswPaises;
    /* @OneToMany(mappedBy = "bswMonedas")
    private Set<StwPlanillaPreciosDet> stwPlanillaPreciosDetSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<CowPlanCuentas> cowPlanCuentasSet;
    @OneToMany(mappedBy = "bswMonedas")
  private Set<BswEmpresas> bswEmpresasSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwHistexval> stwHistexvalSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwArticulos> stwArticulosSet;
    @OneToMany(mappedBy = "bswMonedas1")
    private Set<StwArticulos> stwArticulosSet1;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwEntradaSalidaArt> stwEntradaSalidaArtSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwHistexvad> stwHistexvadSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<CmwProveedores> cmwProveedoresSet;
    @OneToMany(mappedBy = "bswMonedas")
    private Set<StwCostosHist> stwCostosHistSet;
@OneToMany(mappedBy = "bswMonedas")
private Set<VtwAutorizaVenta> vt*/

    @Column(name = "user_audit_id")
    private Integer userAuditID;
    @Column(name = "es_guaranies")
    private boolean esGuaranies;
    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;
    @OrderBy("id desc")

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
    @Transient
    private String codigo;

    public BswMonedas() {
    }

    public BswMonedas(Long id) {
        this.id = id;
    }

    public BswPaises getBswPaises() {
        return bswPaises;
    }

    public void setBswPaises(BswPaises bswPaises) {
        this.bswPaises = bswPaises;
    }

    public BswMonedas(Long id, String codMoneda) {
        this.id = id;
        this.codMoneda = codMoneda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
        setCodigo(codMoneda);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public BigDecimal getTipoCambioDia() {

        //gdb
        //  return tipoCambio;
        return tipoCambioDia;
    }

    public void setTipoCambioDia(BigDecimal tipoCambioDia) {

        this.tipoCambioDia = tipoCambioDia;
    }

    public Short getDecimales() {
        return decimales;
    }

    public void setDecimales(Short decimales) {
        this.decimales = decimales;
    }

    public Date getFecTipoCambio() {
        return fecTipoCambio;
    }

    public void setFecTipoCambio(Date fecTipoCambio) {
        this.fecTipoCambio = fecTipoCambio;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getTipoCambioCompra() {
        return tipoCambioCompra;
    }

    public void setTipoCambioCompra(BigDecimal tipoCambioCompra) {
        this.tipoCambioCompra = tipoCambioCompra;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    /*  public Set<StwHistCostosArt> getStwHistCostosArtSet() {
        return stwHistCostosArtSet;
    }

    public void setStwHistCostosArtSet(Set<StwHistCostosArt> stwHistCostosArtSet) {
        this.stwHistCostosArtSet = stwHistCostosArtSet;
    }

    public Set<StwHistPrecios> getStwHistPreciosSet() {
        return stwHistPreciosSet;
    }

    public void setStwHistPreciosSet(Set<StwHistPrecios> stwHistPreciosSet) {
        this.stwHistPreciosSet = stwHistPreciosSet;
    }

    public Set<StwEntsalCab> getStwEntsalCabSet() {
        return stwEntsalCabSet;
    }

    public void setStwEntsalCabSet(Set<StwEntsalCab> stwEntsalCabSet) {
        this.stwEntsalCabSet = stwEntsalCabSet;
    }

  public BswPaises getBswPaises() {
    return bswPaises;
  }

  public void setBswPaises(BswPaises bswPaises) {
    this.bswPaises = bswPaises;
  }

    public Set<StwPlanillaPreciosDet> getStwPlanillaPreciosDetSet() {
        return stwPlanillaPreciosDetSet;
    }

    public void setStwPlanillaPreciosDetSet(Set<StwPlanillaPreciosDet> stwPlanillaPreciosDetSet) {
        this.stwPlanillaPreciosDetSet = stwPlanillaPreciosDetSet;
    }

    public Set<CowPlanCuentas> getCowPlanCuentasSet() {
        return cowPlanCuentasSet;
    }

    public void setCowPlanCuentasSet(Set<CowPlanCuentas> cowPlanCuentasSet) {
        this.cowPlanCuentasSet = cowPlanCuentasSet;
    }

  public Set<BswEmpresas> getBswEmpresasSet() {
    return bswEmpresasSet;
  }

  public void setBswEmpresasSet(Set<BswEmpresas> bswEmpresasSet) {
    this.bswEmpresasSet = bswEmpresasSet;
  }

    public Set<StwHistexval> getStwHistexvalSet() {
        return stwHistexvalSet;
    }

    public void setStwHistexvalSet(Set<StwHistexval> stwHistexvalSet) {
        this.stwHistexvalSet = stwHistexvalSet;
    }

    public Set<StwArticulos> getStwArticulosSet() {
        return stwArticulosSet;
    }

    public void setStwArticulosSet(Set<StwArticulos> stwArticulosSet) {
        this.stwArticulosSet = stwArticulosSet;
    }

    public Set<StwArticulos> getStwArticulosSet1() {
        return stwArticulosSet1;
    }

    public void setStwArticulosSet1(Set<StwArticulos> stwArticulosSet1) {
        this.stwArticulosSet1 = stwArticulosSet1;
    }

    public Set<StwEntradaSalidaArt> getStwEntradaSalidaArtSet() {
        return stwEntradaSalidaArtSet;
    }

    public void setStwEntradaSalidaArtSet(Set<StwEntradaSalidaArt> stwEntradaSalidaArtSet) {
        this.stwEntradaSalidaArtSet = stwEntradaSalidaArtSet;
    }

    public Set<StwHistexvad> getStwHistexvadSet() {
        return stwHistexvadSet;
    }

    public void setStwHistexvadSet(Set<StwHistexvad> stwHistexvadSet) {
        this.stwHistexvadSet = stwHistexvadSet;
    }

    public Set<CmwProveedores> getCmwProveedoresSet() {
        return cmwProveedoresSet;
    }

    public void setCmwProveedoresSet(Set<CmwProveedores> cmwProveedoresSet) {
        this.cmwProveedoresSet = cmwProveedoresSet;
    }

    public Set<StwCostosHist> getStwCostosHistSet() {
        return stwCostosHistSet;
    }

    public void setStwCostosHistSet(Set<StwCostosHist> stwCostosHistSet) {
        this.stwCostosHistSet = stwCostosHistSet;
    }
    public Set<VtwAutorizaVenta> getVtwAutorizaVentaSet() {
        return vtwAutorizaVentaSet;
    }

    public void setVtwAutorizaVentaSet(Set<VtwAutorizaVenta> vtwAutorizaVentaSet) {
        this.vtwAutorizaVentaSet = vtwAutorizaVentaSet;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BswMonedas)) {
            return false;
        }
        BswMonedas other = (BswMonedas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswMonedas[id=" + id + "]";
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
        this.codigo = this.getCodMoneda();
        return codigo;
    }

    @Override

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override

    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the esGuaranies
     */
    public boolean isEsGuaranies() {
        return esGuaranies;
    }

    /**
     * @param esGuaranies the esGuaranies to set
     */
    public void setEsGuaranies(boolean esGuaranies) {
        this.esGuaranies = esGuaranies;
    }

}
