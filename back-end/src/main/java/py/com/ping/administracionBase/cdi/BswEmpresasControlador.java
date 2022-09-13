/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import py.com.ping.administracionBase.jpa.BswPersonas;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswMonedas;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import py.com.ping.utilitarios.utils.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gdb
 */
@Named
@ViewScoped
public class BswEmpresasControlador extends AbstractControllerGenerico<BswEmpresas> {
    private static final Logger log =
            LoggerFactory.getLogger(BswModulosControlador.class);

    @Override
    public void setParametros() {
        this.clase = BswEmpresas.class;
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

    @Override
    public void inicializaVariablesLocales() {
        getComponentes().setMonedasABM(new BswMonedas());
        getComponentes().setBswPersonas(new BswPersonas());
    }

    @Override
    protected void antesABM() {
        if (getComponentes().getMonedasABM().getId() != null) {
            this.actual.setBswMonedas(getComponentes().getMonedasABM());
        } else {
            this.actual.setBswMonedas(null);

        }
        if (getComponentes().getBswPersonas().getId() != null) {
            this.actual.setBswPersonas(getComponentes().getBswPersonas());
        } else {
            this.actual.setBswPersonas(null);
        }
        if (getActual().getFoto() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(actual.getFoto(), actual.getNombreImagen());
            } catch (Exception ex) {
                mensajeAlerta("Error al mostrar la imagen");
            }
        } else {
            getActual().setNombreImagen("blank_avatar.png");
        }

//        if (getComponentes().getTimbradosABM().getId() != null) {
//            this.actual.setVtwTimbrados(getComponentes().getTimbradosABM());
//        }
        boolean cargoComponentTheme = false, cargoMenuMode = false, cargoDarkMode = false, cargoTopbarTheme = false,
                cargoMenuTheme = false, cargoInputStyle = false;

        getActual().setComponentTheme(getLoginControlador().getComponentTheme());
        getActual().setMenuMode(getLoginControlador().getMenuMode());
        getActual().setDarkMode(getLoginControlador().getDarkMode());
        getActual().setTopbarTheme(getLoginControlador().getTopbarTheme());
        getActual().setMenuTheme(getLoginControlador().getMenuTheme());
        getActual().setInputStyle(getLoginControlador().getInputStyle());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setMonedasABM(actual.getBswMonedas());
        getComponentes().setBswPersonas(actual.getBswPersonas());
//        getComponentes().setTimbradosABM(actual.getVtwTimbrados());
    }

    @Override
    protected boolean validacionesLocales() {
        if(getActual().getRazonSocial()==null || getActual().getRazonSocial().isEmpty()){
            mensajeAlerta("Razon Social es un campo requerido");
            return false;
        }
        if(getActual().getBswPersonas()==null || getActual().getBswPersonas().getId()==null){
            mensajeAlerta("Persona es un campo requerido");
            return false;
        }
        if(!esModificarDetalle && !getUsuarioLogueado().isSuperUsuario()){
            mensajeAlerta("No posee permisos necesarios para crear nuevas empresas, por favor consulte con el soporte de Cosmesoft S.A.");
            return false;
        }
        return true;
    }
    

    @Override
    public void limpiarDatosLocales() {
        getComponentes().setMonedasABM(null);
        getComponentes().setBswPersonas(null);
//        getComponentes().setTimbradosABM(null);
        getActual().setNombreImagen("blank_avatar.png");

    }

    @EJB
    FileManager fileManager;

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            int readers = event.getFile().getInputStream().read(buffer);
            getActual().setFoto(buffer);
            getActual().setNombreImagen(
                    fileManager.construirArchivoAGuardarTmp(
                            IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName()));
            mensajeExito("Archivo subido exitosamente");
        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");

        }

    }

    public void handleFileUploadLogoDE(FileUploadEvent event) throws IOException {
        try {
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            int readers = event.getFile().getInputStream().read(buffer);
            getActual().setLogoDocumentoElectronico(buffer);
            getActual().setNombreImagenLogoDocumentoElectronico(
                    fileManager.construirArchivoAGuardarTmp(
                            IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName()));
            mensajeExito("Archivo subido exitosamente");
        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");
        }
    }
}
