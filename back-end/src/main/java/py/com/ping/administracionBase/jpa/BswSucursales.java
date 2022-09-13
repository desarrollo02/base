/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import py.com.ping.administracionBase.validation.util.MaxSize;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/*import py.com.invweb.stock.jpa.*;
 import py.com.invweb.ventas.jpa.VtwAutorizaVenta;*/

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_SUCURSALES", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_EMPRESA", "COD_SUCURSAL"})})
@NamedQueries({
    @NamedQuery(name = "BswSucursales.findAll", query = "SELECT b FROM BswSucursales b"),
    @NamedQuery(name = "BswSucursales.findAllByEmpresa", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp"),
    @NamedQuery(name = "BswSucursales.findOneByEmpresa", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp order by b.id desc"),
    @NamedQuery(name = "BswSucursales.findAllOrderByCode", query = "SELECT b FROM BswSucursales b ORDER by b.codSucursal"),
    @NamedQuery(name = "BswSucursales.findByCodSucursal", query = "SELECT b FROM BswSucursales b WHERE b.codSucursal = :codSucursal"),
    @NamedQuery(name = "BswSucursales.findById", query = "SELECT b FROM BswSucursales b WHERE b.id = :id"),
    @NamedQuery(name = "BswSucursales.getPorCodigo", query = "SELECT b FROM BswSucursales b WHERE b.codSucursal = :codigo"),
    @NamedQuery(name = "BswSucursales.count", query = "SELECT count(b) FROM BswSucursales b"),
    @NamedQuery(name = "BswSucursales.findByDescripcion", query = "SELECT b FROM BswSucursales b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswSucursales.findByEsMatriz", query = "SELECT b FROM BswSucursales b WHERE b.esMatriz = :esMatriz"),
    @NamedQuery(name = "BswSucursales.findByEstado", query = "SELECT b FROM BswSucursales b WHERE b.estado = :estado"),
    @NamedQuery(name = "BswSucursales.findByCasillaCorreo", query = "SELECT b FROM BswSucursales b WHERE b.casillaCorreo = :casillaCorreo"),
    @NamedQuery(name = "BswSucursales.findByCodigoPostal", query = "SELECT b FROM BswSucursales b WHERE b.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "BswSucursales.findByDetalleDir", query = "SELECT b FROM BswSucursales b WHERE b.detalleDir = :detalleDir"),
    @NamedQuery(name = "BswSucursales.findByTrabajaDom", query = "SELECT b FROM BswSucursales b WHERE b.trabajaDom = :trabajaDom"),
    @NamedQuery(name = "BswSucursales.findByTrabajaSab", query = "SELECT b FROM BswSucursales b WHERE b.trabajaSab = :trabajaSab"),
    @NamedQuery(name = "BswSucursales.findByManejaStock", query = "SELECT b FROM BswSucursales b WHERE b.manejaStock = :manejaStock"),
    @NamedQuery(name = "BswSucursales.findByPlazoEnvio", query = "SELECT b FROM BswSucursales b WHERE b.plazoEnvio = :plazoEnvio"),
    @NamedQuery(name = "BswSucursales.findByDireccion", query = "SELECT b FROM BswSucursales b WHERE b.direccion = :direccion"),
    @NamedQuery(name = "BswSucursales.findAllByEmpresaAndFinanciero", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp and  b.esFinanciero = 'N'"),
    @NamedQuery(name = "BswSucursales.findAllByEmpresaAndCampoGanadero", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp and  b.esCampoGanadero = TRUE"),
    @NamedQuery(name = "BswSucursales.findAllByEmpAndFinaAndsucH", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp and  b.esFinanciero = 'N'and b.bswSucursalPadre is not null"),
    @NamedQuery(name = "BswSucursales.findAllByEmpAndFinanSi", query = "SELECT b FROM BswSucursales b where b.bswEmpresas = :emp and  b.esFinanciero = 'S'"),
    @NamedQuery(name = "BswSucursales.findByCodUsuarioAud", query = "SELECT b FROM BswSucursales b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswSucursales implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SUCURSALES_SEQ_GENERATOR", sequenceName = "BSW_SUCURSALES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUCURSALES_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    @NotNull(message = "Empresa es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    private BswEmpresas bswEmpresas;

    @Column(name = "COD_SUCURSAL", length = 5)
    @NotNullString(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class)
    private String codSucursal;

    @MaxSize(campoNombre = "ruc", tamañoMaximo = 25, groups = AbstractControlerValidationGroups.class)
    @Column(name = "ruc", length = 25)
    private String ruc;

    @MaxSize(campoNombre = "DESCRIPCION", tamañoMaximo = 80, groups = AbstractControlerValidationGroups.class)
    @Column(name = "DESCRIPCION", length = 80)
    @NotNullString(groups = AbstractControlerValidationGroups.class, campoNombre = "Descripcion")
    private String descripcion;

    @Column(name = "ES_MATRIZ", length = 1)
    private String esMatriz;
    @Column(name = "ESTADO", length = 1)
    private String estado;
    @Column(name = "CASILLA_CORREO", length = 15)
    private String casillaCorreo;
    @Column(name = "CODIGO_POSTAL", length = 10)
    private String codigoPostal;
    @Column(name = "DETALLE_DIR", length = 160)
    private String detalleDir;
    @Column(name = "TRABAJA_DOM", length = 1)
    private String trabajaDom;
    @Column(name = "TRABAJA_SAB", length = 1)
    private String trabajaSab;
    @Column(name = "MANEJA_STOCK", length = 1)
    private String manejaStock;
    @Column(name = "PLAZO_ENVIO")
    private Short plazoEnvio;
    @Column(name = "ABREVIATURA", length = 25)
    private String abreviatura;
    @Column(name = "fecha_desde_resumen", length = 25)
    private String fechaDesdeResumen;
    @Column(name = "es_sucursal_dependiente")
    private boolean utilizaResumen;

    @Column(name = "DIRECCION", length = 150)
    private String direccion;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    /* @OneToMany(mappedBy = "bswSucursales")
     private Set<StwPedidosEnvioCab> stwPedidosEnvioCabSet;
     @OneToMany(mappedBy = "bswSucursales1")
     private Set<StwPedidosEnvioCab> stwPedidosEnvioCabSet1;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwEntsalCab> stwEntsalCabSet;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwInventCab> stwInventCabSet;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwEntsalDet> stwEntsalDetSet;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwOrdenReposicion> stwOrdenReposicionSet;*/
    @OneToMany(mappedBy = "bswSucursales")
    private Set<BswUsuarios> bswUsuariosSet;
    /*  @OneToMany(mappedBy = "bswSucursales")
     private Set<StwHistexval> stwHistexvalSet;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwExistenciaArt> stwExistenciaArtSet;
   
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwEntradaSalidaArt> stwEntradaSalidaArtSet;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwHistexvad> stwHistexvadSet;*/
    @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID")
    @ManyToOne
    private BswProvincias bswProvincias;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
    @ManyToOne
    private BswPaises bswPaises;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    private BswCiudades bswCiudades;
    @JoinColumn(name = "ID_BARRIO", referencedColumnName = "ID")
    @ManyToOne
    private BswBarrios bswBarrios;
    /*   @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwSaldosSucursal> stwSaldosSucursalSet;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwReservasArticulos> stwReservasArticulosSet;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwNotasEnvioCab> stwNotasEnvioCabSet;
     @OneToMany(mappedBy = "bswSucursales1")
     private Set<StwNotasEnvioCab> stwNotasEnvioCabSet1;
     @OneToMany(mappedBy = "bswSucursales")
     private Set<StwCostosArt> stwCostosArtSet;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private Set<StwUbicArticulos> stwUbicArticulosSet;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswSucursales")
     private List<StwBloqueoArt> stwBloqueoArtList;
    
     @OneToMany(mappedBy = "bswSucursales")
     private Set<VtwAutorizaVenta> vtwAutorizaVentaSet;*/
    @Column(name = "TIPO_PRECIO", length = 1)
    private String tipoPrecio;
    @JoinColumn(name = "id_tipo_programa", referencedColumnName = "id")
    @ManyToOne
    private BswTipoPrograma bswTipoPrograma;
    @Transient
    private boolean esFinancieroABM;
    @Size(max = 150)
    @Column(name = "nro_acuerdo")
    private String nroAcuerdo;

    @Size(max = 250)
    @Column(name = "factura_nombre")
    private String facturaNombre;

    @Size(max = 250)
    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Size(max = 250)
    @Column(name = "email")
    private String email;

    @Size(max = 80)
    @Column(name = "programa")
    private String programa;

    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;

    @Size(max = 25)
    @Column(name = "codigo_geografico")
    private String codigoGeografico;

    @Size(max = 100)
    @Column(name = "cliente_nombre")
    private String clienteNombre;

    @JoinColumn(name = "ID_DIRECTOR", referencedColumnName = "ID")
    @ManyToOne
    private BswPersonas director;

    @Column(name = "user_audit_id")
    private Integer userAuditID;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    @Size(max = 15)
    @Column(name = "ip")
    private String ip;
    @Size(max = 5)
    @Column(name = "puerto")
    private String puerto;
    @OneToMany(mappedBy = "bswSucursalPadre")
    private List<BswSucursales> bswSucursalesHijas;
    @JoinColumn(name = "padre_id", referencedColumnName = "id")
    @ManyToOne
    private BswSucursales bswSucursalPadre;
   
    @Size(max = 250)
    @Column(name = "path_imagen")
    private String pathImagen;
     @Column(name = "es_financiero", length = 1)
    private String esFinanciero;
    @Column(name = "es_campo_ganadero")
    private boolean esCampoGanadero;
    @Transient
    private boolean activoABM;

    @Transient
    private boolean matrizABM;

    @Column(name = "cod_suc_remota", length = 15)
    private String codSucursalRem;

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

    public BswSucursales() {
    }

    public BswSucursales(String codigo, String descripcion) {
        this.codSucursal = codigo;
        this.descripcion = descripcion;
    }

    public BswSucursales(Long id) {
        this.id = id;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public BswSucursales(Long id, String codSucursal) {
        this.id = id;
        this.codSucursal = codSucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(String esMatriz) {
        this.esMatriz = esMatriz;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCasillaCorreo() {
        return casillaCorreo;
    }

    public void setCasillaCorreo(String casillaCorreo) {
        this.casillaCorreo = casillaCorreo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDetalleDir() {
        return detalleDir;
    }

    public void setDetalleDir(String detalleDir) {
        this.detalleDir = detalleDir;
    }

    public String getTrabajaDom() {
        return trabajaDom;
    }

    public void setTrabajaDom(String trabajaDom) {
        this.trabajaDom = trabajaDom;
    }

    public String getTrabajaSab() {
        return trabajaSab;
    }

    public void setTrabajaSab(String trabajaSab) {
        this.trabajaSab = trabajaSab;
    }

    public String getManejaStock() {
        return manejaStock;
    }

    public void setManejaStock(String manejaStock) {
        this.manejaStock = manejaStock;
    }

    public Short getPlazoEnvio() {
        return plazoEnvio;
    }

    public void setPlazoEnvio(Short plazoEnvio) {
        this.plazoEnvio = plazoEnvio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    /*  public Set<StwPedidosEnvioCab> getStwPedidosEnvioCabSet() {
     return stwPedidosEnvioCabSet;
     }

     public void setStwPedidosEnvioCabSet(Set<StwPedidosEnvioCab> stwPedidosEnvioCabSet) {
     this.stwPedidosEnvioCabSet = stwPedidosEnvioCabSet;
     }

     public Set<StwPedidosEnvioCab> getStwPedidosEnvioCabSet1() {
     return stwPedidosEnvioCabSet1;
     }

     public void setStwPedidosEnvioCabSet1(Set<StwPedidosEnvioCab> stwPedidosEnvioCabSet1) {
     this.stwPedidosEnvioCabSet1 = stwPedidosEnvioCabSet1;
     }

     public Set<StwEntsalCab> getStwEntsalCabSet() {
     return stwEntsalCabSet;
     }

     public void setStwEntsalCabSet(Set<StwEntsalCab> stwEntsalCabSet) {
     this.stwEntsalCabSet = stwEntsalCabSet;
     }

     public Set<StwInventCab> getStwInventCabSet() {
     return stwInventCabSet;
     }

     public void setStwInventCabSet(Set<StwInventCab> stwInventCabSet) {
     this.stwInventCabSet = stwInventCabSet;
     }

     public Set<StwEntsalDet> getStwEntsalDetSet() {
     return stwEntsalDetSet;
     }

     public void setStwEntsalDetSet(Set<StwEntsalDet> stwEntsalDetSet) {
     this.stwEntsalDetSet = stwEntsalDetSet;
     }

     public Set<StwOrdenReposicion> getStwOrdenReposicionSet() {
     return stwOrdenReposicionSet;
     }

     public void setStwOrdenReposicionSet(Set<StwOrdenReposicion> stwOrdenReposicionSet) {
     this.stwOrdenReposicionSet = stwOrdenReposicionSet;
     }*/
    
    public Set<BswUsuarios> getBswUsuariosSet() {
        return bswUsuariosSet;
    }

    public void setBswUsuariosSet(Set<BswUsuarios> bswUsuariosSet) {
        this.bswUsuariosSet = bswUsuariosSet;
    }

    
    
    /*  public Set<StwHistexval> getStwHistexvalSet() {
     return stwHistexvalSet;
     }

     public void setStwHistexvalSet(Set<StwHistexval> stwHistexvalSet) {
     this.stwHistexvalSet = stwHistexvalSet;
     }

     public Set<StwExistenciaArt> getStwExistenciaArtSet() {
     return stwExistenciaArtSet;
     }

     public void setStwExistenciaArtSet(Set<StwExistenciaArt> stwExistenciaArtSet) {
     this.stwExistenciaArtSet = stwExistenciaArtSet;
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
     }*/
    public BswProvincias getBswProvincias() {
        return bswProvincias;
    }

    public void setBswProvincias(BswProvincias bswProvincias) {
        this.bswProvincias = bswProvincias;
    }

    public BswPaises getBswPaises() {
        return bswPaises;
    }

    public void setBswPaises(BswPaises bswPaises) {
        this.bswPaises = bswPaises;
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }

    public BswCiudades getBswCiudades() {
        return bswCiudades;
    }

    public void setBswCiudades(BswCiudades bswCiudades) {
        this.bswCiudades = bswCiudades;
    }

    public BswBarrios getBswBarrios() {
        return bswBarrios;
    }

    public void setBswBarrios(BswBarrios bswBarrios) {
        this.bswBarrios = bswBarrios;
    }

    /*  public Set<StwSaldosSucursal> getStwSaldosSucursalSet() {
     return stwSaldosSucursalSet;
     }

     public void setStwSaldosSucursalSet(Set<StwSaldosSucursal> stwSaldosSucursalSet) {
     this.stwSaldosSucursalSet = stwSaldosSucursalSet;
     }

     public Set<StwReservasArticulos> getStwReservasArticulosSet() {
     return stwReservasArticulosSet;
     }

     public void setStwReservasArticulosSet(Set<StwReservasArticulos> stwReservasArticulosSet) {
     this.stwReservasArticulosSet = stwReservasArticulosSet;
     }

     public Set<StwNotasEnvioCab> getStwNotasEnvioCabSet() {
     return stwNotasEnvioCabSet;
     }

     public void setStwNotasEnvioCabSet(Set<StwNotasEnvioCab> stwNotasEnvioCabSet) {
     this.stwNotasEnvioCabSet = stwNotasEnvioCabSet;
     }

     public Set<StwNotasEnvioCab> getStwNotasEnvioCabSet1() {
     return stwNotasEnvioCabSet1;
     }

     public void setStwNotasEnvioCabSet1(Set<StwNotasEnvioCab> stwNotasEnvioCabSet1) {
     this.stwNotasEnvioCabSet1 = stwNotasEnvioCabSet1;
     }

     public Set<StwCostosArt> getStwCostosArtSet() {
     return stwCostosArtSet;
     }

     public void setStwCostosArtSet(Set<StwCostosArt> stwCostosArtSet) {
     this.stwCostosArtSet = stwCostosArtSet;
     }

     public Set<StwUbicArticulos> getStwUbicArticulosSet() {
     return stwUbicArticulosSet;
     }

     public void setStwUbicArticulosSet(Set<StwUbicArticulos> stwUbicArticulosSet) {
     this.stwUbicArticulosSet = stwUbicArticulosSet;
     }

     public List<StwBloqueoArt> getStwBloqueoArtList() {
     return stwBloqueoArtList;
     }

     public void setStwBloqueoArtList(List<StwBloqueoArt> stwBloqueoArtList) {
     this.stwBloqueoArtList = stwBloqueoArtList;
     }

     public Set<VtwAutorizaVenta> getVtwAutorizaVentaSet() {
     return vtwAutorizaVentaSet;
     }

     public void setVtwAutorizaVentaSet(Set<VtwAutorizaVenta> vtwAutorizaVentaSet) {
     this.vtwAutorizaVentaSet = vtwAutorizaVentaSet;
     }*/
    public BswTipoPrograma getBswTipoPrograma() {
        return bswTipoPrograma;
    }

    public void setBswTipoPrograma(BswTipoPrograma bswTipoPrograma) {
        this.bswTipoPrograma = bswTipoPrograma;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
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
        if (!(object instanceof BswSucursales)) {
            return false;
        }
        BswSucursales other = (BswSucursales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswSucursales[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return this.codSucursal;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codSucursal = codigo;
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
        return this.id;
    }

    @Override
    public void setPK(Object id) {
        this.id = (Long) id;
    }

    /**
     * @return the tipoPrecio
     */
    public String getTipoPrecio() {
        return tipoPrecio;
    }

    /**
     * @param tipoPrecio the tipoPrecio to set
     */
    public void setTipoPrecio(String tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNroAcuerdo() {
        return nroAcuerdo;
    }

    public void setNroAcuerdo(String nroAcuerdo) {
        this.nroAcuerdo = nroAcuerdo;
    }

    public String getFacturaNombre() {
        return facturaNombre;
    }

    public void setFacturaNombre(String facturaNombre) {
        this.facturaNombre = facturaNombre;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCodigoGeografico() {
        return codigoGeografico;
    }

    public void setCodigoGeografico(String codigoGeografico) {
        this.codigoGeografico = codigoGeografico;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public BswPersonas getDirector() {
        return director;
    }

    public void setDirector(BswPersonas director) {
        this.director = director;
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
     * @return the utilizaResumen
     */
    public boolean isUtilizaResumen() {
        return utilizaResumen;
    }

    /**
     * @param utilizaResumen the utilizaResumen to set
     */
    public void setUtilizaResumen(boolean utilizaResumen) {
        this.utilizaResumen = utilizaResumen;
    }

    /**
     * @return the matrizABM
     */
    public boolean isMatrizABM() {
        return (esMatriz != null && "S".equals(esMatriz));
    }

    /**
     * @param matrizABM the matrizABM to set
     */
    public void setMatrizABM(boolean matrizABM) {
        this.matrizABM = matrizABM;
        if (activoABM) {
            esMatriz = "S";
        } else {
            esMatriz = "N";
        }
    }

    public boolean isActivoABM() {
        return (estado != null && "S".equals(estado));
    }

    public void setActivoABM(boolean activoABM) {
        this.activoABM = activoABM;
        if (activoABM) {
            estado = "S";
        } else {
            estado = "N";
        }

    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the codSucursalRem
     */
    public String getCodSucursalRem() {
        return codSucursalRem;
    }

    /**
     * @param codSucursalRem the codSucursalRem to set
     */
    public void setCodSucursalRem(String codSucursalRem) {
        this.codSucursalRem = codSucursalRem;
    }

    /**
     * @return the bswSucursalPadre
     */
    public BswSucursales getBswSucursalPadre() {
        return bswSucursalPadre;
    }

    /**
     * @param bswSucursalPadre the bswSucursalPadre to set
     */
    public void setBswSucursalPadre(BswSucursales bswSucursalPadre) {
        this.bswSucursalPadre = bswSucursalPadre;
    }

    /**
     * @return the bswSucursalesHijas
     */
    public List<BswSucursales> getBswSucursalesHijas() {
        return bswSucursalesHijas;
    }

    /**
     * @param bswSucursalesHijas the bswSucursalesHijas to set
     */
    public void setBswSucursalesHijas(List<BswSucursales> bswSucursalesHijas) {
        this.bswSucursalesHijas = bswSucursalesHijas;
    }

    /**
     * @return the puerto
     */
    public String getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the fechaDesdeResumen
     */
    public String getFechaDesdeResumen() {
        return fechaDesdeResumen;
    }

    /**
     * @param fechaDesdeResumen the fechaDesdeResumen to set
     */
    public void setFechaDesdeResumen(String fechaDesdeResumen) {
        this.fechaDesdeResumen = fechaDesdeResumen;
    }
    
      public boolean isEsFinancieroABM() {
        return (esFinanciero != null && "S".equals(esFinanciero));
    }

    public void setEsFinancieroABM(boolean esFinancieroABM) {
        this.esFinancieroABM = esFinancieroABM;
        if (esFinancieroABM) {
            esFinanciero = "S";
        } else {
            esFinanciero = "N";
        }
    }

      /**
     * @return the esCampoGanadero
     */
    public boolean isEsCampoGanadero() {
        return esCampoGanadero;
    }

    /**
     * @param esCampoGanadero the esCampoGanadero to set
     */
    public void setEsCampoGanadero(boolean esCampoGanadero) {
        this.esCampoGanadero = esCampoGanadero;
    }


}
