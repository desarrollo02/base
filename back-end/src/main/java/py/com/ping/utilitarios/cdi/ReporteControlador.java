/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.cdi;

import java.io.Serializable;
import javax.inject.Inject;
import py.com.ping.administracionBase.cdi.ParametrosGenerales;
import py.com.ping.administracionBase.jpa.BswDummy;
import py.com.ping.administracionBase.util.ImprimirReporte;

/**
 *
 * @author USUARIO
 */
public abstract class ReporteControlador extends AbstractControllerGenerico<BswDummy> implements Serializable{

    /**
     * @return the pg
     */
    public ParametrosGenerales getPg() {
        return pg;
    }

    /**
     * @param pg the pg to set
     */
    public void setPg(ParametrosGenerales pg) {
        this.pg = pg;
    }
    private String formato;
    private String ipHost;
    private ImprimirReporte imprimirreporte;
    @Inject
    private ParametrosGenerales pg;
    public ReporteControlador (){
       
    }

    /**
     * @return the imprimirreporte
     */
    public ImprimirReporte getImprimirreporte() {
        if(imprimirreporte==null){
             imprimirreporte =new ImprimirReporte(getEmpresaLogueada());
        }
        
        return imprimirreporte;
    }

    /**
     * @param imprimirreporte the imprimirreporte to set
     */
    public void setImprimirreporte(ImprimirReporte imprimirreporte) {
        this.imprimirreporte = imprimirreporte;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getIpHost() {
        return ipHost;
    }

    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }
    
}
