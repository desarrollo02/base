package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.jpa.EntidadCodigo;
import py.com.ping.utilitarios.jpa.EntidadABM;

@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_PERSONAS", catalog = "", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COD_PERSONA"})})
@NamedQueries({
    @NamedQuery(name = "BswPersonas.findAll", query = "SELECT b FROM BswPersonas b"),
    @NamedQuery(name = "BswPersonas.findById", query = "SELECT b FROM BswPersonas b WHERE b.id = :id"),
    @NamedQuery(name = "BswPersonas.findByCodPersona", query = "SELECT b FROM BswPersonas b WHERE b.codPersona = :codPersona"),
    @NamedQuery(name = "BswPersonas.getPorCodigo", query = "SELECT b FROM BswPersonas b WHERE b.codPersona = :codPersona"),
    @NamedQuery(name = "BswPersonas.findByEsFisica", query = "SELECT b FROM BswPersonas b WHERE b.esFisica = :esFisica"),
    @NamedQuery(name = "BswPersonas.findByNombre", query = "SELECT b FROM BswPersonas b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "BswPersonas.findByNombFantasia", query = "SELECT b FROM BswPersonas b WHERE b.nombFantasia = :nombFantasia"),
    @NamedQuery(name = "BswPersonas.findBySexo", query = "SELECT b FROM BswPersonas b WHERE b.sexo = :sexo"),
    @NamedQuery(name = "BswPersonas.findByPrimerApellido", query = "SELECT b FROM BswPersonas b WHERE b.primerApellido = :primerApellido"),
    @NamedQuery(name = "BswPersonas.findBySegundoApellido", query = "SELECT b FROM BswPersonas b WHERE b.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "BswPersonas.findByPrimerNombre", query = "SELECT b FROM BswPersonas b WHERE b.primerNombre = :primerNombre"),
    @NamedQuery(name = "BswPersonas.findBySegundoNombre", query = "SELECT b FROM BswPersonas b WHERE b.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "BswPersonas.findByNacionalidad", query = "SELECT b FROM BswPersonas b WHERE b.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "BswPersonas.findByDirecElectronica", query = "SELECT b FROM BswPersonas b WHERE b.direcElectronica = :direcElectronica"),
    @NamedQuery(name = "BswPersonas.findByEsMalDeudor", query = "SELECT b FROM BswPersonas b WHERE b.esMalDeudor = :esMalDeudor"),
    @NamedQuery(name = "BswPersonas.findByNumHijos", query = "SELECT b FROM BswPersonas b WHERE b.numHijos = :numHijos"),
    @NamedQuery(name = "BswPersonas.findByNumDependientes", query = "SELECT b FROM BswPersonas b WHERE b.numDependientes = :numDependientes"),
    @NamedQuery(name = "BswPersonas.findByTotalIngresos", query = "SELECT b FROM BswPersonas b WHERE b.totalIngresos = :totalIngresos"),
    @NamedQuery(name = "BswPersonas.findByEsRepresLegal", query = "SELECT b FROM BswPersonas b WHERE b.esRepresLegal = :esRepresLegal"),
    @NamedQuery(name = "BswPersonas.findByFecActualizacion", query = "SELECT b FROM BswPersonas b WHERE b.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "BswPersonas.findByFecNacimiento", query = "SELECT b FROM BswPersonas b WHERE b.fecNacimiento = :fecNacimiento"),
    @NamedQuery(name = "BswPersonas.findByFecAlta", query = "SELECT b FROM BswPersonas b WHERE b.fecAlta = :fecAlta"),
    @NamedQuery(name = "BswPersonas.findByNomComercial", query = "SELECT b FROM BswPersonas b WHERE b.nomComercial = :nomComercial"),
    @NamedQuery(name = "BswPersonas.findByRazonSocial", query = "SELECT b FROM BswPersonas b WHERE b.razonSocial = :razonSocial"),
    @NamedQuery(name = "BswPersonas.findByLucrativa", query = "SELECT b FROM BswPersonas b WHERE b.lucrativa = :lucrativa"),
    @NamedQuery(name = "BswPersonas.findByEstatal", query = "SELECT b FROM BswPersonas b WHERE b.estatal = :estatal"),
    @NamedQuery(name = "BswPersonas.count", query = "SELECT count(b) FROM BswPersonas b"),
    @NamedQuery(name = "BswPersonas.countTipoPersona", query = "SELECT count(b) FROM BswPersonas b WHERE b.esFisica = :esFisica"),
    @NamedQuery(name = "BswPersonas.findByPaginaWeb", query = "SELECT b FROM BswPersonas b WHERE b.paginaWeb = :paginaWeb")})
public class BswPersonas implements Serializable, EntidadCodigo, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "PERSONAS_SEQ_GENERATOR", sequenceName = "BSW_PERSONAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAS_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNullString(campoNombre = "Codigo", groups = AbstractControlerValidationGroups.class)

    @Column(name = "COD_PERSONA", nullable = false, length = 15)
    private String codPersona;
    @Column(name = "ES_FISICA", length = 1)
    private String esFisica;
    @NotNullString(campoNombre = "Nombre", groups = AbstractControlerValidationGroups.class)

    @Column(name = "NOMBRE", length = 65)
    private String nombre;
    @Column(name = "NOMB_FANTASIA", length = 60)
    private String nombFantasia;
    @Column(name = "COD_USUARIO_AUD", length = 10)
    private String codUsuarioAud;
    @Column(name = "SEXO", length = 1)
    private String sexo;
    @Column(name = "PRIMER_APELLIDO", length = 15)
    private String primerApellido;
    @Column(name = "SEGUNDO_APELLIDO", length = 15)
    private String segundoApellido;
    @Column(name = "PRIMER_NOMBRE", length = 15)
    private String primerNombre;
    @Column(name = "SEGUNDO_NOMBRE", length = 15)
    private String segundoNombre;
    @Column(name = "celular", length = 30)
    private String celular;
    @Column(name = "linea_baja", length = 30)
    private String lineaBaja;
    @Column(name = "NACIONALIDAD", length = 20)
    private String nacionalidad;
    @Column(name = "DIREC_ELECTRONICA", length = 80)
    private String direcElectronica;
    @Column(name = "DIRECCION", length = 100)
    private String direccion;
    @Column(name = "ES_MAL_DEUDOR", length = 1)
    private String esMalDeudor;
    @Column(name = "NUM_HIJOS")
    private BigInteger numHijos;
    @Column(name = "NUM_DEPENDIENTES")
    private BigInteger numDependientes;
    @Column(name = "TOTAL_INGRESOS")
    private BigInteger totalIngresos;
    @Column(name = "ES_REPRES_LEGAL", length = 1)
    private String esRepresLegal;
    @Column(name = "FEC_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fecActualizacion;
    @Column(name = "FEC_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;
    @Column(name = "FEC_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fecAlta;
    @Column(name = "NOM_COMERCIAL", length = 65)
    private String nomComercial;
    @Column(name = "RAZON_SOCIAL", length = 120)
    private String razonSocial;
    @Column(name = "LUCRATIVA", length = 1)
    private String lucrativa;
    @Column(name = "ESTATAL", length = 1)
    private String estatal;
    @Column(name = "PAGINA_WEB", length = 60)
    private String paginaWeb;
    @OneToMany(mappedBy = "bswPersonas")
    private Set<BswEmpresas> bswEmpresasSet;
    @OneToMany(mappedBy = "bswPersonas")
    @JoinColumn(name = "ID_ACTUALIZADO_POR", referencedColumnName = "ID")
    @ManyToOne
    private BswUsuarios bswUsuarios;
    @JoinColumn(name = "ID_ALTA_POR", referencedColumnName = "ID")
    @ManyToOne
    private BswUsuarios bswUsuarios1;
    @JoinColumn(name = "ID_TIPO_SOCIEDAD", referencedColumnName = "ID")
    @ManyToOne
    private BswTiposSociedad bswTiposSociedad;
    @JoinColumn(name = "ID_SECTOR", referencedColumnName = "ID")
    @ManyToOne
    private BswSectoresEcon bswSectoresEcon;
    @JoinColumn(name = "ID_CONYUGUE", referencedColumnName = "ID")
    @ManyToOne
    private BswPersonas bswPersonas;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
    @ManyToOne
    private BswPaises bswPaises;
    @JoinColumn(name = "ID_NIVEL_ESTUDIOS", referencedColumnName = "ID")
    @ManyToOne
    private BswNivelEstudios bswNivelEstudios;
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private BswEstadosCiviles bswEstadosCiviles;
    @OneToMany(mappedBy = "bswPersonas")
    private Set<BswUsuarios> bswUsuariosSet;
    @OneToMany(mappedBy = "bswPersonas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BswIdentPersonas> bswIdentPersonasList;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswEmpresas bswEmpresas;
    @Column(name = "user_audit_id")
    private Integer userAuditID;
    @Column(name = "ip_cliente", length = 50)
    private String ipCliente;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private BswModulos bswModulos;
    @Column(name = "tipo")
    private String tipo;

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

    public BswPersonas() {
    }

    public BswPersonas(String cod, String nombre) {
        this.codPersona = cod;
        this.nombre = nombre;
    }

    public BswPersonas(Long id) {
        this.id = id;
    }

    public BswPersonas(Long id, String codPersona) {
        this.id = id;
        this.codPersona = codPersona;
    }

    public BswPersonas(String codPersona, String nombre, String ruc, Boolean esPersonaFisicaABM, String emailABM, String sexoABM, String userName, BswEmpresas empresa) {
        this.codPersona = codPersona;
        this.nombre = nombre;
        if (esPersonaFisicaABM) {
            this.setEsFisica("S");
        } else {
            this.setEsFisica("N");
        }
        this.setDirecElectronica(emailABM);
        this.setSexo(sexoABM);
        this.bswEmpresas = empresa;
        this.setCodUsuarioAud(userName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(String codPersona) {
        this.codPersona = codPersona;
    }

    public String getEsFisica() {

        return esFisica;
    }

    public void setEsFisica(String esFisica) {
        this.esFisica = esFisica;
    }

    public boolean isEsFisicaBoolean() {
        return "S".equals(esFisica);
    }

    public void setEsFisicaBoolean(boolean useCreditcard) {
        this.esFisica = useCreditcard ? "S" : "N";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombFantasia() {
        return nombFantasia;
    }

    public void setNombFantasia(String nombFantasia) {
        this.nombFantasia = nombFantasia;
    }

    public String getCodUsuarioAud() {
        return codUsuarioAud;
    }

    public void setCodUsuarioAud(String codUsuarioAud) {
        this.codUsuarioAud = codUsuarioAud;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDirecElectronica() {
        return direcElectronica;
    }

    public void setDirecElectronica(String direcElectronica) {
        this.direcElectronica = direcElectronica;
    }

    public String getEsMalDeudor() {
        return esMalDeudor;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setEsMalDeudor(String esMalDeudor) {
        this.esMalDeudor = esMalDeudor;
    }

    public BigInteger getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(BigInteger numHijos) {
        this.numHijos = numHijos;
    }

    public BigInteger getNumDependientes() {
        return numDependientes;
    }

    public void setNumDependientes(BigInteger numDependientes) {
        this.numDependientes = numDependientes;
    }

    public BigInteger getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigInteger totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public String getEsRepresLegal() {
        return esRepresLegal;
    }

    public void setEsRepresLegal(String esRepresLegal) {
        this.esRepresLegal = esRepresLegal;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getLucrativa() {
        return lucrativa;
    }

    public void setLucrativa(String lucrativa) {
        this.lucrativa = lucrativa;
    }

    public String getEstatal() {
        return estatal;
    }

    public void setEstatal(String estatal) {
        this.estatal = estatal;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Set<BswEmpresas> getBswEmpresasSet() {
        return bswEmpresasSet;
    }

    public void setBswEmpresasSet(Set<BswEmpresas> bswEmpresasSet) {
        this.bswEmpresasSet = bswEmpresasSet;
    }

    public Set<BswUsuarios> getBswUsuariosSet() {
        return bswUsuariosSet;
    }

    public void setBswUsuariosSet(Set<BswUsuarios> bswUsuariosSet) {
        this.bswUsuariosSet = bswUsuariosSet;
    }

    public BswUsuarios getBswUsuarios() {
        return bswUsuarios;
    }

    public void setBswUsuarios(BswUsuarios bswUsuarios) {
        this.bswUsuarios = bswUsuarios;
    }

    public List<BswIdentPersonas> getBswIdentPersonasList() {
        return bswIdentPersonasList;
    }

    public void setBswIdentPersonasList(List<BswIdentPersonas> bswIdentPersonasList) {
        this.bswIdentPersonasList = bswIdentPersonasList;
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
        if (!(object instanceof BswPersonas)) {
            return false;
        }
        BswPersonas other = (BswPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ping.py.com.ping.administracionBase.jpa.BswPersonas[id=" + id + "]";
    }

    public BswUsuarios getBswUsuarios1() {
        return bswUsuarios1;
    }

    public void setBswUsuarios1(BswUsuarios bswUsuarios1) {
        this.bswUsuarios1 = bswUsuarios1;
    }

    public BswTiposSociedad getBswTiposSociedad() {

        return bswTiposSociedad;
    }

    public void setBswTiposSociedad(BswTiposSociedad bswTiposSociedad) {
        this.bswTiposSociedad = bswTiposSociedad;
    }

    public BswSectoresEcon getBswSectoresEcon() {

        return bswSectoresEcon;
    }

    public void setBswSectoresEcon(BswSectoresEcon bswSectoresEcon) {
        this.bswSectoresEcon = bswSectoresEcon;
    }

    public BswPersonas getBswPersonas() {
        return bswPersonas;
    }

    public void setBswPersonas(BswPersonas bswPersonas) {
        this.bswPersonas = bswPersonas;
    }

    public BswPaises getBswPaises() {

        return bswPaises;
    }

    public void setBswPaises(BswPaises bswPaises) {
        this.bswPaises = bswPaises;
    }

    public BswNivelEstudios getBswNivelEstudios() {

        return bswNivelEstudios;
    }

    public void setBswNivelEstudios(BswNivelEstudios bswNivelEstudios) {
        this.bswNivelEstudios = bswNivelEstudios;
    }

    public BswEstadosCiviles getBswEstadosCiviles() {

        return bswEstadosCiviles;
    }

    public void setBswEstadosCiviles(BswEstadosCiviles bswEstadosCiviles) {
        this.bswEstadosCiviles = bswEstadosCiviles;
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
        return this.codPersona;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codPersona = codigo;
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
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the lineaBaja
     */
    public String getLineaBaja() {
        return lineaBaja;
    }

    /**
     * @param lineaBaja the lineaBaja to set
     */
    public void setLineaBaja(String lineaBaja) {
        this.lineaBaja = lineaBaja;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public Integer getIndiceLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceLista(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDescripcion() {
        return this.nombre;
    }

    public void setDescripcion(String descripcion) {
        this.nombre = descripcion;
    }

    public BswModulos getBswModulos() {
        return bswModulos;
    }

    public void setBswModulos(BswModulos bswModulos) {
        this.bswModulos = bswModulos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
