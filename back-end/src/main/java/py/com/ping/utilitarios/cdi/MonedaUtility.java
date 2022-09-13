/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.ping.administracionBase.cdi.ParametrosGenerales;
import py.com.ping.administracionBase.jpa.BswMonedas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.administracionBase.util.GenericQueryImpl;
import py.com.ping.utilitarios.interfaces.GenericQuery;
import py.com.ping.utilitarios.utils.NumberUtil;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
@Named
@RequestScoped
public class MonedaUtility implements Serializable{

    @PersistenceContext
    private EntityManager em;
    @Inject
    ParametrosGenerales pg;
    private GenericQuery genericQuery = new GenericQueryImpl();
    private boolean preciosPorSucursal;
    
    @PostConstruct
    public void inicializaValores(){
    String precioXsucursalStr = pg.buscarParametro("VT", ApplicationConstant.PRECIO_POR_SUCURSAL);
    if(precioXsucursalStr!=null && precioXsucursalStr.equals("S")){
        preciosPorSucursal=true;
    }else{
        preciosPorSucursal=false;
    }    
    }
      /**
     * Metodo que compara dos monedas distintas y retorna si son diferentes o no
     *
     */
    public boolean sonDistintasMonedas(BswMonedas monedaOrigen, BswMonedas monedaDestino) {
        if (monedaOrigen != null && monedaDestino != null) {
            if (!monedaOrigen.equals(monedaDestino)) {
                return true;
            }
        }
        return false;

    }

      public BigDecimal convertirMoneda(BswMonedas monedaDestino, BswMonedas monedaOrigen, 
              BigDecimal tipoCambioDestino, BigDecimal tipoCambioOrigen, BigDecimal monto) {
       
         if(sonDistintasMonedas(monedaOrigen, monedaDestino))
            return NumberUtil.convertirMoneda(monedaDestino!=null?monedaDestino.isEsGuaranies():true, tipoCambioDestino, tipoCambioOrigen, monto);
         else
            return monto;
    }

    //<editor-fold defaultstate="collapsed" desc="Mensajes">

    protected FacesMessage facesMsg;

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    //</editor-fold>

   
    
}
