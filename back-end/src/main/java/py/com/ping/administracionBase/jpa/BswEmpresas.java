/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import py.com.ping.listener.BusinessListener;

import javax.validation.constraints.Size;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_EMPRESAS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_EMPRESA"})})
@NamedQueries({
    @NamedQuery(name = "BswEmpresas.findAll", query = "SELECT b FROM BswEmpresas b"),
    @NamedQuery(name = "BswEmpresas.findById", query = "SELECT b FROM BswEmpresas b WHERE b.id = :id"),
    @NamedQuery(name = "BswEmpresas.findByCodEmpresa", query = "SELECT b FROM BswEmpresas b WHERE b.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "BswEmpresas.findByTituloReportes", query = "SELECT b FROM BswEmpresas b WHERE b.tituloReportes = :tituloReportes"),
    @NamedQuery(name = "BswEmpresas.findByDescripcion", query = "SELECT b FROM BswEmpresas b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BswEmpresas.findByCodUsuarioAud", query = "SELECT b FROM BswEmpresas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswEmpresas implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "EMPRESAS_SEQ_GENERATOR", sequenceName = "BSW_EMPRESA_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESAS_SEQ_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @NotNullString(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class)
    @Basic(optional = false)
    @Column(name = "COD_EMPRESA", nullable = false, length = 5)
    private String codEmpresa;
    @Column(name = "TITULO_REPORTES", length = 80)
    private String tituloReportes;
    @Column(name = "DESCRIPCION", length = 80)
    private String descripcion;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @Size(max = 50)
    @Column(name = "ruc")
    private String ruc;
    @NotNullString(campoNombre = "Razon Social", groups = AbstractControlerValidationGroups.class)
    @Column(name = "razon_social", length = 50)
    private String razonSocial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswEmpresas")
    private List<BswSucursales> bswSucursalesSet;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private BswPersonas bswPersonas;
    @JoinColumn(name = "grupo_empresa_id", referencedColumnName = "ID")
    @ManyToOne
    private BswGrupoEmpresasUsuarios bswGrupoEmpresas;
    @JoinColumn(name = "ID_MONEDAS", referencedColumnName = "ID")
    @ManyToOne
    private BswMonedas bswMonedas;
    @Column(name = "mostrar_marca")
    private boolean mostrarMarca;
    @Column(name = "mostrar_talle")
    private boolean mostrarTalle;
    @Column(name = "mostrar_modelo")
    private boolean mostrarModelo;
    @Column(name = "mostrar_color")
    private boolean mostrarColor;
    @Column(name = "mostrar_linea")
    private boolean mostrarLinea;
    @Column(name = "mostrar_plan_cuenta")
    private boolean mostrarPlanCuenta;
    @Column(name = "mostrar_contrato")
    private boolean mostrarContrato;
    @Column(name = "mostrar_rubro_pre")
    private boolean mostrarRubroPre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswEmpresas")
    private List<BswGruposUsuarios> bswGruposUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswEmpresas")
    private List<BswAccesosGrupos> bswAccesosGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bswEmpresas")
    private List<BswModulos> bswModulosList;
    @Column(name = "direccion", length = 125)
    private String direccion;

    @Column(name = "foto")
    private byte[] foto;
    @Transient
    private String imagenProducto;
    @Column(name = "nombre_imagen", length = 120)
    private String nombreImagen;

    @Column(name = "user_audit_id")
    private Integer userAuditID;

    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;

    @Column(name = "theme")
    private String componentTheme;
    @Column
    private String darkMode;
    @Column
    private String menuMode;
    @Column
    private String topbarTheme;
    @Column
    private String menuTheme;
    @Column
    private String inputStyle;
    @Column
    private boolean lightLogo;

    @Column(name = "reporte_presupuesto", length = 80)
    private String reportePresupuesto;

    @Column(name = "reporte_factura", length = 80)
    private String reporteFactura;
    @Column(name = "repres_legal", length = 100)
    private String represLegal;
    @Column(name = "actividad", length = 10)
    private String actividad;
    @Size(max = 50)
    @Column(name = "longitud")
    private String longitud;
    @Size(max = 50)
    @Column(name = "latitud")
    private String latitud;

    @Lob
    @Column(name = "logo_doc_elec")
    private byte[] logoDocumentoElectronico;

    @Column(name = "nombre_imagen_logo_doc_elec", length = 120)
    private String nombreImagenLogoDocumentoElectronico;

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

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public BswEmpresas() {
    }

    public BswEmpresas(Long id) {
        this.id = id;
    }

    public BswEmpresas(Long id, String codEmpresa) {
        this.id = id;
        this.codEmpresa = codEmpresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getTituloReportes() {
        return tituloReportes;
    }

    public void setTituloReportes(String tituloReportes) {
        this.tituloReportes = tituloReportes.toUpperCase();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }

    public String getReportePresupuesto() {
        return reportePresupuesto;
    }

    public void setReportePresupuesto(String reportePresupuesto) {
        this.reportePresupuesto = reportePresupuesto;
    }

    public String getReporteFactura() {
        return reporteFactura;
    }

    public void setReporteFactura(String reporteFactura) {
        this.reporteFactura = reporteFactura;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    public BswPersonas getBswPersonas() {
        return bswPersonas;
    }

    public void setBswPersonas(BswPersonas bswPersonas) {
        this.bswPersonas = bswPersonas;
    }

    public BswMonedas getBswMonedas() {
        return bswMonedas;
    }

    public void setBswMonedas(BswMonedas bswMonedas) {
        this.bswMonedas = bswMonedas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRepresLegal() {
        return represLegal;
    }

    public void setRepresLegal(String represLegal) {
        this.represLegal = represLegal;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public boolean isMostrarContrato() {
        return mostrarContrato;
    }

    public void setMostrarContrato(boolean mostrarContrato) {
        this.mostrarContrato = mostrarContrato;
    }

    public List<BswModulos> getBswModulosList() {
        return bswModulosList;
    }

    public void setBswModulosList(List<BswModulos> bswModulosList) {
        this.bswModulosList = bswModulosList;
    }

    public boolean isMostrarRubroPre() {
        return mostrarRubroPre;
    }

    public void setMostrarRubroPre(boolean mostrarRubroPre) {
        this.mostrarRubroPre = mostrarRubroPre;
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
        if (!(object instanceof BswEmpresas)) {
            return false;
        }
        BswEmpresas other = (BswEmpresas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswEmpresas[id=" + id + "]";
    }

    @Override
    public String getCodigo() {
        return getCodEmpresa();
    }

    @Override
    public void setCodigo(String codigo) {
        this.setCodEmpresa(codigo);
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

    public List<BswSucursales> getBswSucursalesSet() {
        return bswSucursalesSet;
    }

    public void setBswSucursalesSet(List<BswSucursales> bswSucursalesSet) {
        this.bswSucursalesSet = bswSucursalesSet;
    }
    
    public Integer getUserAuditID() {
        return userAuditID;
    }

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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getNombreImagen() {

        if (this.getFoto() == null) {
            return "avatar3.png";
        }
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /**
     * @return the mostrarMarca
     */
    public boolean isMostrarMarca() {
        return mostrarMarca;
    }

    /**
     * @param mostrarMarca the mostrarMarca to set
     */
    public void setMostrarMarca(boolean mostrarMarca) {
        this.mostrarMarca = mostrarMarca;
    }

    /**
     * @return the mostrarTalle
     */
    public boolean isMostrarTalle() {
        return mostrarTalle;
    }

    /**
     * @param mostrarTalle the mostrarTalle to set
     */
    public void setMostrarTalle(boolean mostrarTalle) {
        this.mostrarTalle = mostrarTalle;
    }

    /**
     * @return the mostrarModelo
     */
    public boolean isMostrarModelo() {
        return mostrarModelo;
    }

    /**
     * @param mostrarModelo the mostrarModelo to set
     */
    public void setMostrarModelo(boolean mostrarModelo) {
        this.mostrarModelo = mostrarModelo;
    }

    /**
     * @return the mostrarColor
     */
    public boolean isMostrarColor() {
        return mostrarColor;
    }

    /**
     * @param mostrarColor the mostrarColor to set
     */
    public void setMostrarColor(boolean mostrarColor) {
        this.mostrarColor = mostrarColor;
    }

    /**
     * @return the mostrarLinea
     */
    public boolean isMostrarLinea() {
        return mostrarLinea;
    }

    /**
     * @param mostrarLinea the mostrarLinea to set
     */
    public void setMostrarLinea(boolean mostrarLinea) {
        this.mostrarLinea = mostrarLinea;
    }

    public boolean isMostrarPlanCuenta() {
        return mostrarPlanCuenta;
    }

    public void setMostrarPlanCuenta(boolean mostrarPlanCuenta) {
        this.mostrarPlanCuenta = mostrarPlanCuenta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the bswGrupoEmpresas
     */
    public BswGrupoEmpresasUsuarios getBswGrupoEmpresas() {
        return bswGrupoEmpresas;
    }

    /**
     * @param bswGrupoEmpresas the bswGrupoEmpresas to set
     */
    public void setBswGrupoEmpresas(BswGrupoEmpresasUsuarios bswGrupoEmpresas) {
        this.bswGrupoEmpresas = bswGrupoEmpresas;
    }

    public List<BswGruposUsuarios> getBswGruposUsuariosList() {
        return bswGruposUsuariosList;
    }

    public void setBswGruposUsuariosList(List<BswGruposUsuarios> bswGruposUsuariosList) {
        this.bswGruposUsuariosList = bswGruposUsuariosList;
    }

    public List<BswAccesosGrupos> getBswAccesosGruposList() {
        return bswAccesosGruposList;
    }

    public void setBswAccesosGruposList(List<BswAccesosGrupos> bswAccesosGruposList) {
        this.bswAccesosGruposList = bswAccesosGruposList;
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public String getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
    }

    public String getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public String getTopbarTheme() {
        return topbarTheme;
    }

    public void setTopbarTheme(String topbarTheme) {
        this.topbarTheme = topbarTheme;
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public boolean isLightLogo() {
        return lightLogo;
    }

    public void setLightLogo(boolean lightLogo) {
        this.lightLogo = lightLogo;
    }

    public byte[] getLogoDocumentoElectronico() {
        return logoDocumentoElectronico;
    }

    public void setLogoDocumentoElectronico(byte[] logoDocumentoElectronico) {
        this.logoDocumentoElectronico = logoDocumentoElectronico;
    }

    public String getNombreImagenLogoDocumentoElectronico() {
        if (this.getLogoDocumentoElectronico() == null) {
            return "avatar3.png";
        }
        return nombreImagenLogoDocumentoElectronico;
    }

    public void setNombreImagenLogoDocumentoElectronico(String nombreImagenLogoDocumentoElectronico) {
        this.nombreImagenLogoDocumentoElectronico = nombreImagenLogoDocumentoElectronico;
    }
}
