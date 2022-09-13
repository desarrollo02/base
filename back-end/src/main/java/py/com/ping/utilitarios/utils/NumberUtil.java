/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import py.com.ping.utilitarios.cdi.FuncionesVarias;

/**
 *
 * @author rudy
 */
public class NumberUtil {

    public static BigDecimal getZeroOrBigDecimal(BigDecimal valor) {
        if (valor == null) {
            return BigDecimal.ZERO;
        }
        return valor;
    }

    public static BigDecimal parseAsBigDecimal(String input) {
        try {
            String aux = input.trim().replace(".", "");
            Double doubleValue = Double.valueOf(aux);
            return BigDecimal.valueOf(doubleValue);
        } catch (Exception e) {
            return null;
        }

    }

    public static String getNumeroFormateado(BigDecimal numero) {
        if (numero == null) {
            return null;
        }
        DecimalFormat formateador = new DecimalFormat("#,##0");
        return formateador.format(numero);
    }

    public static String getNumeroFormateadoUnDecimal(BigDecimal numero) {
        if (numero == null) {
            return null;
        }
        DecimalFormat formateador = new DecimalFormat("#,##0.0");
        return formateador.format(numero);
    }

    public static String getNroFormatProdEntregar(BigDecimal numero) {
        DecimalFormat formateador;
        if (numero == null) {
            return null;
        }
        if (numero.doubleValue() % 1 == 0) {
            formateador = new DecimalFormat("#,##0");
        } else {
            formateador = new DecimalFormat("#,##0.00");
        }

        return formateador.format(numero);
    }

    public static String getNroFormatProdEntregarTwo(BigDecimal numero) {
        DecimalFormat formateador;
        if (numero == null) {
            return null;
        }
        if (numero.doubleValue() % 1 == 0) {
            formateador = new DecimalFormat("#,##0");
        } else {
            formateador = new DecimalFormat("#,##0.00");
        }

        return formateador.format(numero);
    }

    public static String getNumeroFormateadoWord(BigDecimal numero) {
        DecimalFormat formateadorEntero = new DecimalFormat("#,##0");
        DecimalFormat formateadorDecimal = new DecimalFormat("#,##0.00");
        if (numero == null) {
            return "";
        }
        if (Math.round(numero.doubleValue()) == numero.doubleValue()) {//entero
            return formateadorEntero.format(numero);
        } else {
            return formateadorDecimal.format(numero);
        }

    }

    public static BigDecimal getIntAsBigDecimalOrCero(int numero) {
        try {
            return BigDecimal.valueOf(Double.valueOf(numero));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public static BigDecimal getProrrateoNumerico(BigDecimal porcentaje, BigDecimal monto) {
        BigDecimal porc = BigDecimal.ZERO;
        Double doubleValue;
        if (porcentaje != null && monto != null) {
            try {
                doubleValue = porcentaje.doubleValue();
                porc = BigDecimal.valueOf(doubleValue).divide(BigDecimal.valueOf(100));
                return monto.multiply(porc);
            } catch (Exception e) {
                return porc;
            }
        } else {
            return porc;
        }

    }

    public static BigDecimal getPorcentaje(BigDecimal total, BigDecimal monto) {
        if (total == null || monto == null || total == BigDecimal.ZERO) {
            return BigDecimal.ZERO;
        }

        return (monto.divide(total)).multiply(BigDecimal.valueOf(100));

    }

    public static String getcantidadCaracter(String direccion) {
        if (direccion != null && direccion.length() > 50) {
            String substring = direccion.substring(0, 50);
            return substring;
        }

        return direccion;
    }

    public static BigInteger parseAsBigInteger(String input) {
        try {
            String aux = input.trim().replace(".", "");

            Double doubleValue = Double.valueOf(aux);
            return BigInteger.valueOf(doubleValue.longValue());
        } catch (Exception e) {
            return null;
        }

    }

    public static String NumberPadding(Integer numero, Integer cantidadCeros) {
        StringBuilder format = new StringBuilder();
        format.append("%0").append(cantidadCeros).append("d");
        return String.format(format.toString(), numero);
    }

    public static BigDecimal sumaBigDecimal(BigDecimal a, BigDecimal b) {
        return (a != null ? a : BigDecimal.ZERO).add(b != null ? b : BigDecimal.ZERO);
    }

    public static boolean coparaEsMayorBigDecimal(BigDecimal a, BigDecimal b) {
        return (a != null ? a : BigDecimal.ZERO).compareTo(b != null ? b : BigDecimal.ZERO) > 0;
    }
      public static boolean coparaEsIgual(BigDecimal a, BigDecimal b) {
        return (a != null ? a : BigDecimal.ZERO).equals(b != null ? b : BigDecimal.ZERO);
    }

    public static BigDecimal sumaBigDecimal(Integer a, BigDecimal b) {
        return (a != null ? new BigDecimal(a) : BigDecimal.ZERO).add(b != null ? b : BigDecimal.ZERO);
    }

    public static BigDecimal restaBigDecimal(BigDecimal a, BigDecimal b) {
        return (a != null ? a : BigDecimal.ZERO).subtract(b != null ? b : BigDecimal.ZERO);
    }

    public static BigDecimal restaBigDecimalNoRetornaNegativo(BigDecimal a, BigDecimal b) {
        BigDecimal resultado = restaBigDecimal(a, b);
        return resultado.compareTo(BigDecimal.ZERO) > 0 ? resultado : BigDecimal.ZERO;
    }

    public static BigDecimal divideBigDecimal(BigDecimal a, BigDecimal b, Integer precision) {
        return (a != null ? a : BigDecimal.ZERO).divide((b != null && !b.equals(BigDecimal.ZERO) ? b : BigDecimal.ONE), precision, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiplicaBigDecimal(BigDecimal a, BigDecimal b) {
        return (a != null ? a : BigDecimal.ZERO).multiply(b != null ? b : BigDecimal.ZERO);
    }

    public static BigDecimal convertirMoneda(boolean esMonedaDestinoGs,
            BigDecimal tipoCambioDestino, BigDecimal tipoCambioOrigen, BigDecimal monto) {
       if (!coparaEsIgual(tipoCambioDestino, tipoCambioOrigen)) {
            if (!esMonedaDestinoGs) {
                return FuncionesVarias.convierteMontoMonedaDestinoOrigen(tipoCambioDestino, tipoCambioOrigen, monto);
            } else {
                System.err.println("***************RETORNO" + multiplicaBigDecimal(monto, tipoCambioOrigen));
                return multiplicaBigDecimal(monto, tipoCambioOrigen);
            }

        }

        return monto;
    }
    public static BigInteger restaBigInteger(BigInteger a, BigInteger b) {
        return (a != null ? a : BigInteger.ZERO).subtract(b != null ? b : BigInteger.ZERO);
    }
}
