package py.com.ping.administracionBase.cdi;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import py.com.ping.administracionBase.ejb.BswUsuariosEJB;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import py.com.ping.utilitarios.utils.FileManager;
import py.com.ping.utilitarios.utils.StringUtils;
import py.com.ping.utils.dto.PasswordDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ViewScoped
public class BswCambioClave extends AbstractControllerGenerico<BswUsuarios> implements Serializable {
    private static final int tamPrimo = 16; //Tamaño actual de primos actualmente utilizados para las claves
    private static final Logger logger =
            LoggerFactory.getLogger(BswCambioClave.class);

    private String paginaActual;
    private @Inject LoginControlador loginControlador;
    @PersistenceContext
    protected EntityManager em;
    @EJB
    private BswUsuariosEJB usuariosEJB;

    //OBJETOS SELECCIONADOS.
    private String claveVieja;
    private String claveNueva;
    private String confirmacionClave;
    private PasswordDto passwordDto;
    
    public BswCambioClave () {
        super();
        limpiar();
    }

    @Override
    public void limpiar() {
        claveVieja = "";
        claveNueva = "";
        confirmacionClave = "";
    }

    @Override
    public void setParametros() {
        this.formName = "BSCAMPAS";
        this.clase = BswUsuarios.class;
        this.paginaActual = "BswCambioClave.xhtml";


        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:BswCambioClave.xhtml");
            getAcercaDe().add("- Forma: BSCAMPAS");
            getAcercaDe().add("- Controlador: BswCambioClave.java");
            getAcercaDe().add("- EJB: AbstractControllerGenerico.java");
        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta pantalla puede ser utilizada para cambiar la contraseña del usuario que ha iniciado sesión.");
        }
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        if(claveNueva != null)
            this.claveNueva = claveNueva;
    }

    public String getClaveVieja() {
        return claveVieja;
    }

    public void setClaveVieja(String claveVieja) {
        if(claveVieja != null)
            this.claveVieja = claveVieja;
    }

    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        if(confirmacionClave != null)
            this.confirmacionClave = confirmacionClave;
    }

    public void cambiar() {
        if(claveVieja == null){
            mensajeError("Debe introducir su contraseña actual");
            return;
        }
        if(claveNueva == null){
            mensajeError("Debe introducir la contraseña nueva");
            return;
        }
        if(claveNueva.isEmpty()){
            mensajeError("Introduzca una contraseña válida");
            return;
        }
        if(confirmacionClave == null){
            mensajeError("Debe introducir la confirmación de su nueva contraseña");
            return;
        }
        if(claveNueva.compareTo(confirmacionClave) != 0){
            mensajeError("La nueva contraseña y su confirmación no coinciden. Por favor ingrese nuevamente");
            return;
        }
        if(loginControlador.getUsuario().getClave().compareTo(claveVieja) != 0){
            mensajeError("Su contraseña actual no es correcta. Ingrese de nuevo");
            return;
        }
        passwordDto = StringUtils.passwordValidation(claveNueva);
        if(!passwordDto.isValido()){
            mensajeError(passwordDto.getMsg());
            return;
        }
        loginControlador.getUsuario().setClave(claveNueva);
        loginControlador.getUsuario().setModificoPassword(true);
        usuariosEJB.actualizar(loginControlador.getUsuario());
        mensajeExito("Su contraseña ha sido cambiada con éxito");
    }

    public void guardarPrerencias(){
        loginControlador.getUsuario().setComponentTheme(loginControlador.getComponentTheme());
        loginControlador.getUsuario().setMenuMode(loginControlador.getMenuMode());
        loginControlador.getUsuario().setDarkMode(loginControlador.getDarkMode());
        loginControlador.getUsuario().setTopbarTheme(loginControlador.getTopbarTheme());
        loginControlador.getUsuario().setMenuTheme(loginControlador.getMenuTheme());
        loginControlador.getUsuario().setInputStyle(loginControlador.getInputStyle());

        usuariosEJB.actualizar(loginControlador.getUsuario());
        mensajeExito("Preferencias Actualizadas con exito!");
    }

    
    public void actualizarImagen(){
        if(loginControlador.getUsuario().getFoto()!=null){
            try {
                fileManager.construirArchivoAGuardarTmp(loginControlador.getUsuario().getFoto(), loginControlador.getUsuario().getNombreImagen());
            } catch (Exception ex) {
                mensajeAlerta("Error al mostrar la imagen");
            }
        }else{
            mensajeAlerta("Debe seleccionar una imagen.");
        }

    }

    @EJB
    FileManager fileManager;
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {
            byte[] buffer=new byte[(int)event.getFile().getSize()];
            int readers=event.getFile().getInputStream().read(buffer);
            loginControlador.getUsuario().setFoto(buffer);
            loginControlador.getUsuario().setNombreImagen(fileManager.construirArchivoAGuardarTmp(IOUtils.toByteArray(event.getFile().getInputStream()), event.getFile().getFileName()));
            actualizarImagen();
            mensajeExito("Archivo subido exitosamente");
        } catch (Exception ex) {
            logger.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");

        }

    }


}
