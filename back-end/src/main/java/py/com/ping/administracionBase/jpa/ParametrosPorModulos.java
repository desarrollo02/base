/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author josema
 */
@Entity
@Cacheable(false)
@Table(name = "parametros_por_modulos")

@NamedQueries({
    @NamedQuery(name = "ParametrosPorModulos.findAll", query = "SELECT p FROM ParametrosPorModulos p"),
    @NamedQuery(name = "ParametrosPorModulos.findById", query = "SELECT p FROM ParametrosPorModulos p WHERE p.id = :id"),
    @NamedQuery(name = "ParametrosPorModulos.findByIdParamGen", query = "SELECT p FROM ParametrosPorModulos p WHERE p.bswParametrosGenerales.id = :idParamGen"),
    @NamedQuery(name = "ParametrosPorModulos.findByIdEmpresa", query = "SELECT p FROM ParametrosPorModulos p WHERE p.bswEmpresas.id = :idEmpresa"),
    @NamedQuery(name = "ParametrosPorModulos.findByCodModulo", query = "SELECT p FROM ParametrosPorModulos p WHERE p.codModulo = :codModulo"),
    @NamedQuery(name = "ParametrosPorModulos.findByParametro", query = "SELECT p FROM ParametrosPorModulos p WHERE p.parametro = :parametro"),
    @NamedQuery(name = "ParametrosPorModulos.findByDescripcion", query = "SELECT p FROM ParametrosPorModulos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ParametrosPorModulos.findByValor", query = "SELECT p FROM ParametrosPorModulos p WHERE p.valor = :valor")})
public class ParametrosPorModulos implements Serializable, EntidadABM {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private BigInteger id;
    @JoinColumn(name = "id_param_gen", referencedColumnName = "id")
    @ManyToOne
    private BswParametrosGenerales bswParametrosGenerales;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private BswEmpresas bswEmpresas;
    @Size(max = 2)
    @Column(name = "cod_modulo")
    private String codModulo;
    @Size(max = 60)
    @Column(name = "parametro")
    private String parametro;
    @Size(max = 240)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 80)
    @Column(name = "valor")
    private String valor;

    public ParametrosPorModulos() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BswParametrosGenerales getBswParametrosGenerales() {
        return bswParametrosGenerales;
    }

    public void setBswParametrosGenerales(BswParametrosGenerales bswParametrosGenerales) {
        this.bswParametrosGenerales = bswParametrosGenerales;
    }

    public BswEmpresas getBswEmpresas() {
        return bswEmpresas;
    }

    public void setBswEmpresas(BswEmpresas bswEmpresas) {
        this.bswEmpresas = bswEmpresas;
    }

    public String getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(String codModulo) {
        this.codModulo = codModulo;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
    public Integer getUserAuditID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getPK() {
        return this.id;
    }

    @Override
    public void setPK(Object id) {
        this.id = (BigInteger) id;
    }

    @Override
    public void setUserAuditID(Integer userAuditID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIpCliente(String ipCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdSession(String idSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
