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
import py.com.ping.administracionBase.jpa.BswEmpresas;
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
public class BswPerfilEmpresaControlador extends AbstractControllerGenerico<BswEmpresas> {
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
      getComponentes().setEmpresasAbm(getEmpresaLogueada());
      getComponentes().setBswPersonas(getEmpresaLogueada().getBswPersonas());
//      getComponentes().setTimbradosABM(getEmpresaLogueada().getVtwTimbrados());
    }

    @Override
    protected void antesABM() {
        setEsModificarDetalle(true);
        setActual(getComponentes().getEmpresasAbm());
        
        if (getComponentes().getBswPersonas().getId() != null) {
            this.actual.setBswPersonas(getComponentes().getBswPersonas());
            this.actual.setRepresLegal(getComponentes().getBswPersonas().getNombre());
        } else {
            this.actual.setBswPersonas(null);
            this.actual.setRepresLegal(null);
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

        if (getActual().getLogoDocumentoElectronico() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(actual.getLogoDocumentoElectronico(), actual.getNombreImagenLogoDocumentoElectronico());
            } catch (Exception ex) {
                mensajeAlerta("Error al mostrar logo para KuDE");
            }
        } else {
            getActual().setNombreImagenLogoDocumentoElectronico("blank_avatar.png");
        }

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
    public void limpiarDatosLocales() {
        getComponentes().setMonedasABM(null);
        getComponentes().setBswPersonas(null);
//        getComponentes().setTimbradosABM(null);
        getActual().setNombreImagen("blank_avatar.png");
        getActual().setNombreImagenLogoDocumentoElectronico("blank_avatar.png");
    }

    @EJB
    FileManager fileManager;

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            int readers = event.getFile().getInputStream().read(buffer);
            getComponentes().getEmpresasAbm().setFoto(buffer);
            getComponentes().getEmpresasAbm().setNombreImagen(
                    fileManager.construirArchivoAGuardarTmp(IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName()));
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
            getComponentes().getEmpresasAbm().setLogoDocumentoElectronico(buffer);
            getComponentes().getEmpresasAbm().setNombreImagenLogoDocumentoElectronico(
                    fileManager.construirArchivoAGuardarTmp(IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName())
            );
            mensajeExito("Archivo subido exitosamente");
        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");
        }
    }
}
