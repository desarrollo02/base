/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import py.com.ping.administracionBase.util.RSA;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.listener.BusinessListener;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadABM;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_USUARIOS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_USUARIO"})})
@NamedQueries({
    @NamedQuery(name = "BswUsuarios.findAll", query = "SELECT b FROM BswUsuarios b"),
    @NamedQuery(name = "BswUsuarios.findById", query = "SELECT b FROM BswUsuarios b WHERE b.id = :id"),
    @NamedQuery(name = "BswUsuarios.findAllByEmpresa", query = "SELECT b FROM BswUsuarios b WHERE b.bswEmpresas = :empresa"),
    @NamedQuery(name = "BswUsuarios.findByCodUsuario", query = "SELECT b FROM BswUsuarios b WHERE b.codUsuario = :codUsuario"),
    @NamedQuery(name = "BswUsuarios.findByCodUsrEmpresa", query = "SELECT b FROM BswUsuarios b WHERE b.codUsuario = :codUsuario and b.bswEmpresas = :empresa"),
    @NamedQuery(name = "BswUsuarios.findByClave", query = "SELECT b FROM BswUsuarios b WHERE b.clave = :clave"),
    @NamedQuery(name = "BswUsuarios.findByEstado", query = "SELECT b FROM BswUsuarios b WHERE b.estado = :estado"),
    @NamedQuery(name = "BswUsuarios.findBySucursalNcr", query = "SELECT b FROM BswUsuarios b WHERE b.sucursalNcr = :sucursalNcr"),
    @NamedQuery(name = "BswUsuarios.findByIndVerificaMenu", query = "SELECT b FROM BswUsuarios b WHERE b.indVerificaMenu = :indVerificaMenu"),
    @NamedQuery(name = "BswUsuarios.findByPuedeFacturar", query = "SELECT b FROM BswUsuarios b WHERE b.puedeFacturar = :puedeFacturar"),
    @NamedQuery(name = "BswUsuarios.count", query = "SELECT count(b) FROM BswUsuarios b"),
    @NamedQuery(name = "BswUsuarios.findBySucursalDeb", query = "SELECT b FROM BswUsuarios b WHERE b.sucursalDeb = :sucursalDeb"),
    @NamedQuery(name = "BswUsuarios.findByCodUsuarioNotCaseSensitive", query = "SELECT b FROM BswUsuarios b WHERE LOWER(b.codUsuario) = LOWER(:codUsuario)")
})

public class BswUsuarios implements Serializable, EntidadCodigo, EntidadABM {

    private static final int tamPrimo = 16; //Tamaño actual de primos actualmente utilizados para las claves
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "USUARIOS_SEQ_GENERATOR", sequenceName = "BSW_USUARIOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @NotNullString(campoNombre = "Codigo de Usuario", groups = AbstractControlerValidationGroups.class)
    @Basic(optional = false)
    @Column(name = "COD_USUARIO", nullable = false, length = 10)
    private String codUsuario;
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "ESTADO", length = 1)
    private String estado;
    @Column(name = "SUCURSAL_NCR", length = 50)
    private String sucursalNcr;
    /*@JoinColumn(name = "ID_SECTOR_VEN", referencedColumnName = "ID")
	 @ManyToOne
	 private VtwSectorVentas vtwSectorVentas;
     */
    @Column(name = "IND_VERIFICA_MENU", length = 1)
    private String indVerificaMenu;
    @Column(name = "PUEDE_FACTURAR", length = 1)
    private String puedeFacturar;
    @Column(name = "SUCURSAL_DEB", length = 50)
    private String sucursalDeb;
    @NotNull(message = "Sucursal es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "ID")
    @ManyToOne
    private BswSucursales bswSucursales;
    @NotNull(message = "Persona es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @ManyToOne
    private BswPersonas bswPersonas;
    @NotNull(message = "Grupo es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private BswGruposUsuarios bswGruposUsuarios;
    @NotNull(message = "Empresa es un campo requerido.", groups = AbstractControlerValidationGroups.class)
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @OneToMany(mappedBy = "bswUsuarios")
    private Set<BswPersonas> bswPersonasSet;
    @OneToMany(mappedBy = "bswUsuarios1")
    private Set<BswPersonas> bswPersonasSet1;
    @Column(name = "modifico_password")
    private Boolean modificoPassword;
    @Column(name = "recibe_email")
    private Boolean recibeEmail;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @Column(name = "super_usuario")
    private boolean superUsuario;
    @Transient
    private boolean activoABM;

    @Size(max = 250)
    @Column(name = "documento")
    private String documento;

    @Column(name = "user_audit_id")
    private Integer userAuditID;

    @Transient
    private String descripcion;
    
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "nombre_imagen", length = 120)
    private String nombreImagen;

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
    
    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    public Boolean getModificoPassword() {
        return modificoPassword;
    }

    public void setModificoPassword(Boolean modificoPassword) {
        this.modificoPassword = modificoPassword;
    }

    public Boolean getRecibeEmail() {
        return recibeEmail;
    }

    public void setRecibeEmail(Boolean recibeEmail) {
        this.recibeEmail = recibeEmail;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    

    public BswUsuarios() {
    }

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

    public BswUsuarios(String cod, String nombre) {
        this.bswPersonas = new BswPersonas(cod, nombre);
        this.codUsuario = cod;
    }

    public BswUsuarios(Long id) {
        this.id = id;
    }

    public BswUsuarios(Long id, String codUsuario) {
        this(id);
        this.codUsuario = codUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    /**
     * Se desencripta la clave almacena en la BD antes de retornarla
     */
    public String getClave() {
        return RSA.desencriptar(tamPrimo, clave);
    }

    /**
     * Se encripta la clave para ser almacenada en la BD
     */
    public void setClave(String clave) {
        if (clave != null) {
            this.clave = RSA.encriptar(tamPrimo, clave);
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSucursalNcr() {
        return sucursalNcr;
    }

    public void setSucursalNcr(String sucursalNcr) {
        this.sucursalNcr = sucursalNcr;
    }

    /*    public VtwSectorVentas getVtwSectorVentas() {
	 return vtwSectorVentas;
	 }

	 public void setCodSectorVentas(VtwSectorVentas vtwSectorVentas) {
	 this.vtwSectorVentas = vtwSectorVentas;
	 }
     */
    public String getIndVerificaMenu() {
        return indVerificaMenu;
    }

    public void setIndVerificaMenu(String indVerificaMenu) {
        this.indVerificaMenu = indVerificaMenu;
    }

    public String getPuedeFacturar() {
        return puedeFacturar;
    }

    public void setPuedeFacturar(String puedeFacturar) {
        this.puedeFacturar = puedeFacturar;
    }

    public String getSucursalDeb() {
        return sucursalDeb;
    }

    public void setSucursalDeb(String sucursalDeb) {
        this.sucursalDeb = sucursalDeb;
    }

    public BswSucursales getBswSucursales() {
        return bswSucursales;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setBswSucursales(BswSucursales bswSucursales) {
        this.bswSucursales = bswSucursales;
    }

    public BswPersonas getBswPersonas() {
        return bswPersonas;
    }

    public void setBswPersonas(BswPersonas bswPersonas) {
        this.bswPersonas = bswPersonas;
    }

    public BswGruposUsuarios getBswGruposUsuarios() {
        return bswGruposUsuarios;
    }

    public void setBswGruposUsuarios(BswGruposUsuarios bswGruposUsuarios) {
        this.bswGruposUsuarios = bswGruposUsuarios;
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
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
        if (!(object instanceof BswUsuarios)) {
            return false;
        }
        BswUsuarios other = (BswUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /*    public List<BswTalonariosUsuarios> getBswTalonariosUsuariosList() {
	 return bswTalonariosUsuariosList;
	 }

	 public void setBswTalonariosUsuariosList(List<BswTalonariosUsuarios> bswTalonariosUsuariosList) {
	 this.bswTalonariosUsuariosList = bswTalonariosUsuariosList;
	 }*/
    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswUsuarios[id=" + id + "]";
    }

    
    public Set<BswPersonas> getBswPersonasSet() {
        return bswPersonasSet;
    }

    public void setBswPersonasSet(Set<BswPersonas> bswPersonasSet) {
        this.bswPersonasSet = bswPersonasSet;
    }

    
    public Set<BswPersonas> getBswPersonasSet1() {
        return bswPersonasSet1;
    }

    public void setBswPersonasSet1(Set<BswPersonas> bswPersonasSet1) {
        this.bswPersonasSet1 = bswPersonasSet1;
    }

    @Override
    public String getCodigo() {
        return this.codUsuario;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codUsuario = codigo;
    }

    public String getDescripcion() {
        if(bswPersonas != null) {
            descripcion = this.bswPersonas.getNombre();
        }
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void setEmpresa(BswEmpresas emp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BswEmpresas getEmpresa() {
        return bswEmpresas;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPK(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * @return the recibeEmail
     */
    public boolean isRecibeEmail() {
        return recibeEmail != null && recibeEmail;
    }

    /**
     * @param recibeEmail the recibeEmail to set
     */
    public void setRecibeEmail(boolean recibeEmail) {
        this.recibeEmail = recibeEmail;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
     * @return the modificoPassword
     */
    public boolean isModificoPassword() {
        return modificoPassword != null && modificoPassword;
    }

    /**
     * @param modificoPassword the modificoPassword to set
     */
    public void setModificoPassword(boolean modificoPassword) {
        this.modificoPassword = modificoPassword;
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

    /**
     * @return the superUsuario
     */
    public boolean isSuperUsuario() {
        return superUsuario;
    }

    /**
     * @param superUsuario the superUsuario to set
     */
    public void setSuperUsuario(boolean superUsuario) {
        this.superUsuario = superUsuario;
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
}
