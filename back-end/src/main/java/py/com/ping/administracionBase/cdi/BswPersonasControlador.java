/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.jpa.BswIdentPersonas;
import py.com.ping.administracionBase.jpa.BswPersonas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.administracionBase.util.GenericABMList;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
public class BswPersonasControlador extends AbstractControllerGenerico<BswPersonas> {
    private static final Logger log =
            LoggerFactory.getLogger(BswPersonasControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswPersonas.class;
        this.formName = ApplicationConstant.nombreFormaPersonasBase;
        this.paginaActual = "BswCOPersonas.xhtml";
    }

    @PostConstruct
    public void inicializaValores() {
        camposQuery.put("bswEmpresas.id", getEmpresaLogueada().getId());
    }

    @Override
    public void inicializaVariablesLocales() {
        getIdentidadPersonaABMGeneric().setClase(BswIdentPersonas.class);

    }

    @Override
    protected void antesABM() {
        getActual().setCodUsuarioAud(getUserNombre());
        getActual().setBswEmpresas(getEmpresaLogueada());
        getActual().setNombre(getActual().getPrimerNombre()+" "+getActual().getPrimerApellido());
        if(getComponentes().getBswNivelEstudioABM().getId()!=null)
              actual.setBswNivelEstudios(getComponentes().getBswNivelEstudioABM());

            

    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswNivelEstudioABM(actual.getBswNivelEstudios());
    }

    //<editor-fold defaultstate="collapsed" desc="Documentos Generic">
    private GenericABMList<BswIdentPersonas> identidadPersonaABMGeneric;

    public GenericABMList<BswIdentPersonas> getIdentidadPersonaABMGeneric() {
        if (identidadPersonaABMGeneric == null) {
            identidadPersonaABMGeneric = new GenericABMList<BswIdentPersonas>();
        }
        return identidadPersonaABMGeneric;
    }

    public void setIdentidadPersonaABMGeneric(GenericABMList<BswIdentPersonas> identidadPersonaABMGeneric) {
        this.identidadPersonaABMGeneric = identidadPersonaABMGeneric;
    }

    public void agregarIdentidadABMGeneric() {
        if (!isDocDuplicado()) {
            if (getComponentes().getBswIdentificacionesAbm() != null && getComponentes().getBswIdentificacionesAbm().getId() != null) {
                getIdentidadPersonaABMGeneric().getObjectView().setBswIdentificaciones(getComponentes().getBswIdentificacionesAbm());
            }
            getIdentidadPersonaABMGeneric().addObject();
            getComponentes().setBswIdentificacionesAbm(null);

        }  // getIdentidadPersonaABMGeneric().cleanObject();

    }

    public boolean isDocDuplicado() {
        //TODO: verificar documento duplicado
        return false;

    }

    public void limpiarIdentidadPersona() {
        getComponentes().setBswIdentificacionesAbm(null);
        getIdentidadPersonaABMGeneric().cleanObject();
    }

    public void editarIndentidad() {
        getComponentes().setBswIdentificacionesAbm(getIdentidadPersonaABMGeneric().getObjectView().getBswIdentificaciones());

    }

//</editor-fold>
    @Override
    public void cargarDatosNuevoObjeto() {
        getComponentes().setBswPersonas(getObjetoAgregado());
        getComponentes().setRepresantLegalABM(getObjetoAgregado());
    }

 
    @Override
    protected void antesAccionABMDetalle() {
         getComponentes().setBswNivelEstudioABM(null);
    }
    
    
}
