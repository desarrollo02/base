/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import py.com.ping.listener.BusinessListener;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import py.com.ping.utilitarios.jpa.EntidadABM;


@Entity
@EntityListeners(BusinessListener.class)	
@Table(name = "BSW_PERSONAS_JURIDICAS")
@NamedQueries({
    @NamedQuery(name = "BswPersonasJuridicas.findAll", query = "SELECT b FROM BswPersonasJuridicas b"),
    @NamedQuery(name = "BswPersonasJuridicas.findById", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.id = :id"),
    @NamedQuery(name = "BswPersonasJuridicas.findByCodPerJuridica", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.codPerJuridica = :codPerJuridica"),
    @NamedQuery(name = "BswPersonasJuridicas.findByNomComercial", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.nomComercial = :nomComercial"),
    @NamedQuery(name = "BswPersonasJuridicas.findByRazonSocial", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.razonSocial = :razonSocial"),
    @NamedQuery(name = "BswPersonasJuridicas.findByEsMalDeudor", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.esMalDeudor = :esMalDeudor"),
    @NamedQuery(name = "BswPersonasJuridicas.findByFecFundacion", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.fecFundacion = :fecFundacion"),
    @NamedQuery(name = "BswPersonasJuridicas.findByLucrativa", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.lucrativa = :lucrativa"),
    @NamedQuery(name = "BswPersonasJuridicas.findByEstatal", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.estatal = :estatal"),
    @NamedQuery(name = "BswPersonasJuridicas.findByDirecElectronica", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.direcElectronica = :direcElectronica"),
    @NamedQuery(name = "BswPersonasJuridicas.findByPaginaWed", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.paginaWed = :paginaWed"),
    @NamedQuery(name = "BswPersonasJuridicas.findByAltaPor", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.altaPor = :altaPor"),
    @NamedQuery(name = "BswPersonasJuridicas.findByFecAlta", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.fecAlta = :fecAlta"),
    @NamedQuery(name = "BswPersonasJuridicas.findByActualizadoPor", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.actualizadoPor = :actualizadoPor"),
    @NamedQuery(name = "BswPersonasJuridicas.findByFecActualizacion", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "BswPersonasJuridicas.findByNombFantasia", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.nombFantasia = :nombFantasia"),
    @NamedQuery(name = "BswPersonasJuridicas.findByCodUsuarioAud", query = "SELECT b FROM BswPersonasJuridicas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswPersonasJuridicas implements Serializable , EntidadABM {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "PERSONAS_JUR_SEQ_GENERATOR", sequenceName = "bsw_personas_jur_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAS_JUR_SEQ_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "COD_PER_JURIDICA")
    private String codPerJuridica;
    @Column(name = "NOM_COMERCIAL")
    private String nomComercial;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "ES_MAL_DEUDOR")
    private String esMalDeudor;
    @Column(name = "FEC_FUNDACION")
    @Temporal(TemporalType.DATE)
    private Date fecFundacion;
    @Column(name = "LUCRATIVA")
    private String lucrativa;
    @Column(name = "ESTATAL")
    private String estatal;
    @Column(name = "DIREC_ELECTRONICA")
    private String direcElectronica;
    @Column(name = "PAGINA_WED")
    private String paginaWed;
    @Column(name = "ALTA_POR")
    private String altaPor;
    @Column(name = "FEC_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fecAlta;
    @Column(name = "ACTUALIZADO_POR")
    private String actualizadoPor;
    @Column(name = "FEC_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fecActualizacion;
    @Column(name = "NOMB_FANTASIA")
    private String nombFantasia;
    @Column(name = "COD_USUARIO_AUD")
    private String codUsuarioAud;
    @JoinColumn(name = "ID_TIPO_SOCIEDAD", referencedColumnName = "ID")
    @ManyToOne
    private BswTiposSociedad bswTiposSociedad;
    @JoinColumn(name = "ID_SECTOR", referencedColumnName = "ID")
    @ManyToOne
    private BswSectoresEcon bswSectoresEcon;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
    @ManyToOne
    private BswPaises bswPaises;

    
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
    
    
    public BswPersonasJuridicas() {
    }

    public BswPersonasJuridicas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }
    
    

    public String getCodPerJuridica() {
        return codPerJuridica;
    }

    public void setCodPerJuridica(String codPerJuridica) {
        this.codPerJuridica = codPerJuridica;
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

    public String getEsMalDeudor() {
        return esMalDeudor;
    }

    public void setEsMalDeudor(String esMalDeudor) {
        this.esMalDeudor = esMalDeudor;
    }

    public Date getFecFundacion() {
        return fecFundacion;
    }

    public void setFecFundacion(Date fecFundacion) {
        this.fecFundacion = fecFundacion;
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

    public String getDirecElectronica() {
        return direcElectronica;
    }

    public void setDirecElectronica(String direcElectronica) {
        this.direcElectronica = direcElectronica;
    }

    public String getPaginaWed() {
        return paginaWed;
    }

    public void setPaginaWed(String paginaWed) {
        this.paginaWed = paginaWed;
    }

    public String getAltaPor() {
        return altaPor;
    }

    public void setAltaPor(String altaPor) {
        this.altaPor = altaPor;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(String actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
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

    public BswPaises getBswPaises() {
        return bswPaises;
    }

    public void setBswPaises(BswPaises bswPaises) {
        this.bswPaises = bswPaises;
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
        if (!(object instanceof BswPersonasJuridicas)) {
            return false;
        }
        BswPersonasJuridicas other = (BswPersonasJuridicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswPersonasJuridicas[id=" + id + "]";
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
    public Object getPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPK(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
