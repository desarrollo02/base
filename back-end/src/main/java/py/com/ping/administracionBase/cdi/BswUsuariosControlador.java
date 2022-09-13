/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.administracionBase.util.ApplicationConstant;
//import py.com.ping.rrhh.dto.MarcacionDto;
//import py.com.ping.rrhh.ejb.RhwMarcacionEjb;
//import py.com.ping.rrhh.utils.PaseUtils;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import py.com.ping.utilitarios.utils.FileManager;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
public class BswUsuariosControlador extends AbstractControllerGenerico<BswUsuarios> {
    private static final Logger logger =
            LoggerFactory.getLogger(BswUsuariosControlador.class);

    @Override
    public void setParametros() {
        this.formName = ApplicationConstant.nombreFormaUsuariosBase;
        this.paginaActual = "BswUsuarios.xhtml";
        this.clase = BswUsuarios.class;
        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:BswUsuarios.xhtml");
            getAcercaDe().add("- Controlador: BswUsuariosControlador.java");
            getAcercaDe().add("- EJB: BswUsuariosEJB.java");
            getAcercaDe().add("- JPA: BswUsuarios.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Usuarios del sistema.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }
    }

    @Override
    public void inicializaVariablesLocales() {
        getComponentes().setSucursalABM(sucursalActual);
    }

    @Override
    protected void antesABM() {
        System.err.println("*****************CLAVE ANTERIOR"+actual.getClave());
        getActual().setCodUsuarioAud(getUserNombre());
        if (getComponentes().getSucursalABM().getId() != null) {
            actual.setBswSucursales(getComponentes().getSucursalABM());
        } else {
            actual.setBswSucursales(null);
        }
        if (getComponentes().getBswPersonas().getId() != null) {
            actual.setBswPersonas(getComponentes().getBswPersonas());
        } else {
            actual.setBswPersonas(null);
        }
        if (getComponentes().getBswGruposUsuariosABM().getId() != null) {
            actual.setBswGruposUsuarios(getComponentes().getBswGruposUsuariosABM());
        } else {
            actual.setBswGruposUsuarios(null);
        }
        if (esModificarDetalle && actualizaPassword) {
            actual.setClave(claveABM);
        } else if (!esModificarDetalle) {
            actual.setClave(claveABM);
        }
        getActual().setBswEmpresas(getEmpresaLogueada());
        if (getActual().getFoto() != null) {
            try {
                fileManager.construirArchivoAGuardarTmp(actual.getFoto(), actual.getNombreImagen());
            } catch (Exception ex) {
                mensajeAlerta("Error al mostrar la imagen");
            }
        } else {
            getActual().setNombreImagen("blank_avatar.png");
        }
         System.err.println("*****************CLAVE Actual"+actual.getClave());
    }

    @Override
    public void cargarCamposRelacionados() {
        getComponentes().setBswPersonas(getActual().getBswPersonas());
        getComponentes().setSucursalABM(getActual().getBswSucursales());
        getComponentes().setBswGruposUsuariosABM(getActual().getBswGruposUsuarios());
//        getComponentes().setComponentesABM(getActual().getBswComponentes());

    }

    @Override
    public void limpiarDatosLocales() {
        getComponentes().setBswPersonas(null);
        getComponentes().setSucursalABM(getSucursalActual());
        getComponentes().setBswGruposUsuariosABM(null);
//        getComponentes().setComponentesABM(null);
        actualizaPassword = false;
        setClaveABM(null);
        getActual().setNombreImagen("blank_avatar.png");
    }

    @EJB
    FileManager fileManager;

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            int readers = event.getFile().getInputStream().read(buffer);
            getActual().setFoto(buffer);

            getActual().setNombreImagen(fileManager.construirArchivoAGuardarTmp(IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName()));

            mensajeExito("Archivo subido exitosamente");
        } catch (Exception ex) {
            logger.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");

        }

    }
    //<editor-fold defaultstate="collapsed" desc="Variables Locales">

    private boolean actualizaPassword;
    private String claveABM;

    /**
     * @return the claveABM
     */
    public String getClaveABM() {
        return claveABM;
    }

    /**
     * @param claveABM the claveABM to set
     */
    public void setClaveABM(String claveABM) {
        this.claveABM = claveABM;
    }

    /**
     * @return the actualizaPassword
     */
    public boolean getActualizaPassword() {
        return actualizaPassword;
    }

    /**
     * @param actualizaPassword the actualizaPassword to set
     */
    public void setActualizaPassword(boolean actualizaPassword) {
        this.actualizaPassword = actualizaPassword;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Marcaciones">
//    @EJB
//    private RhwMarcacionEjb marcacionEjb;
//
//    private UploadedFile uploadedFile;
//
//    public UploadedFile getUploadedFile() {
//        return uploadedFile;
//    }
//
//    public void setUploadedFile(UploadedFile uploadedFile) {
//        this.uploadedFile = uploadedFile;
//    }
//
//    public void procesarArchivo() {
//        List<MarcacionDto> marcaciones = null;
//        if (uploadedFile != null && uploadedFile.getFileName() != null) {
//            try {
//                marcaciones = PaseUtils.parseExcelFileToBeans(uploadedFile.getInputStream(), "marcacion.xml", "marcaciones");
//
//                if (marcaciones != null && !marcaciones.isEmpty()) {
//                    insertarMarcaciones(marcaciones);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                mensajeError("Error al procesar el archivo de marcaciones");
//            }
//
//        }
//
//    }
//
//    private void insertarMarcaciones(List<MarcacionDto> marcaciones) {
//        int exito = 0;
//        int fracaso = 0;
//        int registros = 0;
//
//        for (MarcacionDto marcacionDto : marcaciones) {
//            try {
//                registros++;
//                marcacionEjb.insertarMarcacion(marcacionDto);
//                exito++;
//            } catch (Exception e) {
//                fracaso++;
//            }
//
//        }
//        mensajeAlerta("Registros : " + registros + " Insertados : " + exito + " Ignorados : " + fracaso);
//    }

//</editor-fold>
}
