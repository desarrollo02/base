/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.util;

import java.util.Map;

/**
 *
 * @author inv
 */
public abstract  class Reporte {

  public abstract byte[] generarReporte();
  public abstract void imprimir();
  public abstract void setParametros(Map<String, Object> parametros);
  public abstract void setNombreReporte(String nombreReporte);

}
