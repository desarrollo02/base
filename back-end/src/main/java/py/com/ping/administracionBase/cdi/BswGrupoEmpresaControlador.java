/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGrupoEmpresasUsuarios;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class BswGrupoEmpresaControlador extends AbstractControllerGenerico<BswGrupoEmpresasUsuarios> {

    private static final Logger log =
            LoggerFactory.getLogger(BswGrupoEmpresaControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswGrupoEmpresasUsuarios.class;
        this.formName = ApplicationConstant.nombreFormaEmpresaBase;
        this.paginaActual = "BswEmpresas.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSEMPRES");
            getAcercaDe().add("- Controlador: BswEmpresasControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswEmpresas.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Modulos.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }

    }
    
    
    //<editor-fold defaultstate="collapsed" desc="GRUPO EMPRESAS">
    private List<BswEmpresas> empresasAsociadas;
    @Inject
    private BswEmpresasControlador empresasControlador;
    /**
     * @return the empresasAsociadas
     */
    public List<BswEmpresas> getEmpresasAsociadas() {
        if(empresasAsociadas==null)
            empresasAsociadas=new ArrayList<>();
        return empresasAsociadas;
    }

    /**
     * @param empresasAsociadas the empresasAsociadas to set
     */
    public void setEmpresasAsociadas(List<BswEmpresas> empresasAsociadas) {
        this.empresasAsociadas = empresasAsociadas;
    }

    @Override
    public void cargarCamposRelacionados() {
            setEmpresasAsociadas(getComponentes().obtieneListaEmpresasViculadas(getActual()));
    }
    
    
    
    @Override
    public void despuesABM() {
       
        for(BswEmpresas emp:getActual().getEmpresasVinculadasList()){
            emp.setBswGrupoEmpresas(null);
            empresasControlador.setEsModificarDetalle(true);
            empresasControlador.setActual(emp);
             empresasControlador.cargarCamposRelacionados();
            empresasControlador.grabar();
        }
        for(BswEmpresas emp:getEmpresasAsociadas()){
            empresasControlador.setEsModificarDetalle(true);
            emp.setBswGrupoEmpresas(getActual());
            empresasControlador.setActual(emp);
             empresasControlador.cargarCamposRelacionados();
            empresasControlador.grabar();
        }
    }
    
    public void completaComponentesVarios(BswEmpresas emp){
        empresasControlador.getComponentes().setMonedasABM(emp.getBswMonedas());
         empresasControlador.getComponentes().setBswPersonas(emp.getBswPersonas());
    }
//</editor-fold>


    
    
     
}
