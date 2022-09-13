package py.com.ping.contabilidad.cdi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author rudy
 */
public class TransaccionEnTransitoDto implements Serializable{
    //Id del diario detalle
    private Integer id;
    private Date fechaAsiento;
    private Date fechaCheque;
    private Date fechaOrdenPago;
    private String nroCheque;
    private String beneficiario;
    private BigDecimal monto;
    private BigDecimal debe;
    private BigDecimal haber;

    public TransaccionEnTransitoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaAsiento() {
        return fechaAsiento;
    }

    public void setFechaAsiento(Date fechaAsiento) {
        this.fechaAsiento = fechaAsiento;
    }

    public Date getFechaCheque() {
        return fechaCheque;
    }

    public void setFechaCheque(Date fechaCheque) {
        this.fechaCheque = fechaCheque;
    }

    public Date getFechaOrdenPago() {
        return fechaOrdenPago;
    }

    public void setFechaOrdenPago(Date fechaOrdenPago) {
        this.fechaOrdenPago = fechaOrdenPago;
    }

    public String getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }
    
    
    
}
