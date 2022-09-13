/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * @author Guillermo Dominguez
 */
@Named
@RequestScoped
public class FuncionesVarias implements Serializable {

    /**
     * Metodo que convierte un monto en un moneda origen al monto
     * correspondiente la moneda destino. por ej.: de dolares a pesos
     * argentinos, de pesos argentinos a reales, de dolares a guaranies etc.
     *
     * La funcion convierte la moneda origen que recibe a guaranies
     * (multiplicando) utilizando el tipo de cambio origen luego ese monto lo
     * convierte a la moneda de destino (dividiendo) utilizando el tipo de
     * cambio de destino
     *
     * @param tipoCambioDestino tipo de cambio utilizado para convertir a la
     * moneda destino.
     * @param tipoCambioOrigen tipo de cambio utilizado para convertir a
     * guaranies la moneda original.
     *
     *
     *
     */
    private String formato = "dd/MM/yyyy";

    public static BigDecimal convierteMontoMonedaDestinoOrigen(BigDecimal tipoCambioDestino, BigDecimal tipoCambioOrigen, BigDecimal importeOriginal) {
        BigDecimal importeConvertido = BigDecimal.ZERO;
        try {

            if (tipoCambioOrigen == null || tipoCambioOrigen.equals(BigDecimal.ZERO)) {
                tipoCambioOrigen = BigDecimal.ONE;
            }
            if (tipoCambioDestino == null || tipoCambioDestino.equals(BigDecimal.ZERO)) {
                tipoCambioDestino = BigDecimal.ONE;
            }

            importeConvertido = (importeOriginal != null ? importeOriginal : BigDecimal.ZERO).multiply(tipoCambioOrigen).divide(tipoCambioDestino, 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            importeConvertido = BigDecimal.ZERO;

        }

        return importeConvertido;

    }

    public NumberFormat obtieneFormatoNros() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        String formato = "###,###,###,###,###.00";
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        return new DecimalFormat(formato, simbolos);

    }

    private SimpleDateFormat sdf;

    /**
     * @return the sdf
     */
    public SimpleDateFormat getSdf() {

        if (sdf == null) {
            sdf = new SimpleDateFormat(getFormato());
        }
        return sdf;
    }

    /**
     * @param sdf the sdf to set
     */
    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    public static HashMap orderForValues(HashMap miMap) {
        HashMap mapResultado = new LinkedHashMap();
        List misMapKeys = new ArrayList(miMap.keySet());
        List misMapValues = new ArrayList(miMap.values());
        TreeSet conjuntoOrdenado = new TreeSet(misMapValues);
        Object[] arrayOrdenado = conjuntoOrdenado.toArray();
        int size = arrayOrdenado.length;
        for (int i = 0; i < size; i++) {
            mapResultado.put(misMapKeys.get(
                    misMapValues.indexOf(
                            arrayOrdenado[i])
            ), arrayOrdenado[i]);
        }

        return mapResultado;

    }

    public static HashMap orderForKeys(HashMap miMap) {
        SortedSet<Integer> keys = new TreeSet<Integer>(miMap.keySet());
        HashMap mapResultado = new LinkedHashMap();
        for (Integer key : keys) {
            mapResultado.put(key, miMap.get(key));
        }
        return mapResultado;
    }
    
    
    public BigDecimal calculaIVA10(BigDecimal montoTotal){
        montoTotal=montoTotal!=null?montoTotal:BigDecimal.ZERO; 
        return  montoTotal.divide(new BigDecimal(11), 3, RoundingMode.HALF_UP);
    }
    public BigDecimal calculaIVA5(BigDecimal montoTotal){
        montoTotal=montoTotal!=null?montoTotal:BigDecimal.ZERO; 
        return  montoTotal.divide(new BigDecimal(21), 3, RoundingMode.HALF_UP);
    }
    public static boolean isBigDecimal(String cadena) {

        boolean resultado;
        if(cadena!=null && !cadena.isEmpty()){
        try {
           BigDecimal numero = new BigDecimal(cadena);
            resultado = true;
        } catch (Exception excepcion) {
            resultado = false;
        }}else{
            resultado = false;
        }

        return resultado;
    }

    
     public static boolean esbBigDecimalNotNullNotZero(BigDecimal a){
        return (a!=null)?((!a.equals(BigDecimal.ZERO))?true:false):false; 
    
    }
     
    
 
    

}
