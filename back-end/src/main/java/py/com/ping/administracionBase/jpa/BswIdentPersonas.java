/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import py.com.ping.administracionBase.validation.util.NotNullString;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;
import py.com.ping.utilitarios.interfaces.GenericListValidation;
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author inv
 */
@Entity
@EntityListeners(BusinessListener.class)
@Table(name = "BSW_IDENT_PERSONAS")
@NamedQueries({
    @NamedQuery(name = "BswIdentPersonas.findAll", query = "SELECT b FROM BswIdentPersonas b"),
    @NamedQuery(name = "BswIdentPersonas.findById", query = "SELECT b FROM BswIdentPersonas b WHERE b.id = :id"),
    @NamedQuery(name = "BswIdentPersonas.findByNumero", query = "SELECT b FROM BswIdentPersonas b WHERE b.numero = :numero"),
    @NamedQuery(name = "BswIdentPersonas.findByPersona", query = "SELECT b FROM BswIdentPersonas b WHERE b.bswPersonas = :persona"),
    @NamedQuery(name = "BswIdentPersonas.findByFecVencimiento", query = "SELECT b FROM BswIdentPersonas b WHERE b.fecVencimiento = :fecVencimiento"),
    @NamedQuery(name = "BswIdentPersonas.findByRucAnterior", query = "SELECT b FROM BswIdentPersonas b WHERE b.rucAnterior = :rucAnterior"),
    @NamedQuery(name = "BswIdentPersonas.findByNumeroAgenteInf", query = "SELECT b FROM BswIdentPersonas b WHERE b.numeroAgenteInf = :numeroAgenteInf"),
    @NamedQuery(name = "BswIdentPersonas.findByDvAgenteInf", query = "SELECT b FROM BswIdentPersonas b WHERE b.dvAgenteInf = :dvAgenteInf"),
    @NamedQuery(name = "BswIdentPersonas.findByCodUsuarioAud", query = "SELECT b FROM BswIdentPersonas b WHERE b.codUsuarioAud = :codUsuarioAud")})
public class BswIdentPersonas implements Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "IDENT_PERSONAS_GENERATOR", sequenceName = "BSW_IDENT_PERSONAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDENT_PERSONAS_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMERO")
    @NotNullString(campoNombre = "Numero", groups = GenericListValidation.class)
    private String numero;
    @Column(name = "FEC_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fecVencimiento;
    @Column(name = "RUC_ANTERIOR")
    private String rucAnterior;
    @Column(name = "NUMERO_AGENTE_INF")
    private String numeroAgenteInf;
    @Column(name = "DV_AGENTE_INF")
    private String dvAgenteInf;
    @Column(name = "COD_USUARIO_AUD")
    private String codUsuarioAud;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private BswPersonas bswPersonas;
    @JoinColumn(name = "ID_IDENTIFICACIONES", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    @NotNull(message = "TipoDoc. es un campo requerido", groups = GenericListValidation.class)
    private BswIdentificaciones bswIdentificaciones;
    @Transient
    private Integer indice;

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

    public BswIdentPersonas(BswIdentificaciones ident, String numero, BswPersonas p) {
        this.bswIdentificaciones = ident;
        this.numero = numero;
        this.bswPersonas = p;

    }

    public BswIdentPersonas() {
    }

    public BswIdentPersonas(Long id) {
        this.id = id;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Long getId() {
        return id;
    }

    public Integer getUserAuditID() {
        return userAuditID;
    }

    public void setUserAuditID(Integer userAuditID) {
        this.userAuditID = userAuditID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(Date fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getRucAnterior() {
        return rucAnterior;
    }

    public void setRucAnterior(String rucAnterior) {
        this.rucAnterior = rucAnterior;
    }

    public String getNumeroAgenteInf() {
        return numeroAgenteInf;
    }

    public void setNumeroAgenteInf(String numeroAgenteInf) {
        this.numeroAgenteInf = numeroAgenteInf;
    }

    public String getDvAgenteInf() {
        return dvAgenteInf;
    }

    public void setDvAgenteInf(String dvAgenteInf) {
        this.dvAgenteInf = dvAgenteInf;
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

    public BswIdentificaciones getBswIdentificaciones() {
        return bswIdentificaciones;
    }

    public void setBswIdentificaciones(BswIdentificaciones bswIdentificaciones) {
        this.bswIdentificaciones = bswIdentificaciones;
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
        if (!(object instanceof BswIdentPersonas)) {
            return false;
        }
        BswIdentPersonas other = (BswIdentPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.invweb.py.com.ping.administracionBase.jpa.BswIdentPersonas[id=" + id + "]";
    }

    @Override
    public Integer getIndiceLista() {
        return this.indice;
    }

    @Override
    public void setIndiceLista(Integer id) {
        this.indice = id;
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
