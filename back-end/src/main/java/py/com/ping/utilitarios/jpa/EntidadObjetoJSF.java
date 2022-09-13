/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.utilitarios.jpa;

import java.math.BigDecimal;

/**
 *
 * @author Augusto
 *
 * Objeto JSF que se puede usar para mostrar una lista de objetos con distintos campos en un datatable
 * , se puede usar en el caso en que se quiera mostrar valores que no forman parte de la entidad mostrada
 * en la pantalla
 * ej. ver StwConsultaStockControlador > getListadoPreciosJSF()
 */
public class EntidadObjetoJSF {

    private String codigo;

    private String descripcion;

    private String descripcion2;

    private String descripcion3;

    private BigDecimal valorBigD;

    private BigDecimal valorBigD2;

    private BigDecimal valorBigD3;

    private BigDecimal valorBigD4;

    private Long valorLong;

    private Long valorLong2;

    private Long valorLong3;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public BigDecimal getValorBigD() {
        if(valorBigD == null)
        {
        valorBigD = BigDecimal.ZERO;
        }
        return valorBigD;
    }

    public void setValorBigD(BigDecimal valorBigD) {
        this.valorBigD = valorBigD;
    }

    public BigDecimal getValorBigD2() {
        if(valorBigD2 == null)
        {
        valorBigD2 = BigDecimal.ZERO;
        }
        return valorBigD2;
    }

    public void setValorBigD2(BigDecimal valorBigD2) {
        this.valorBigD2 = valorBigD2;
    }

    public BigDecimal getValorBigD3() {
        if(valorBigD3 == null)
        {
        valorBigD3 = BigDecimal.ZERO;
        }
        return valorBigD3;
    }

    public void setValorBigD3(BigDecimal valorBigD3) {
        this.valorBigD3 = valorBigD3;
    }

   public BigDecimal getValorBigD4() {
        if(valorBigD4 == null)
        {
        valorBigD4 = BigDecimal.ZERO;
        }
        return valorBigD4;
    }

    public void setValorBigD4(BigDecimal valorBigD4) {
        this.valorBigD4 = valorBigD4;
    }

    public Long getValorLong() {
        return valorLong;
    }

    public void setValorLong(Long valorLong) {
        this.valorLong = valorLong;
    }

    public Long getValorLong2() {
        return valorLong2;
    }

    public void setValorLong2(Long valorLong2) {
        this.valorLong2 = valorLong2;
    }

    public Long getValorLong3() {
        return valorLong3;
    }

    public void setValorLong3(Long valorLong3) {
        this.valorLong3 = valorLong3;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    // Agregar mas si hace falta....

}
