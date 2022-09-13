package py.com.ping.utilitarios.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rudy
 */
@Stateless
public class FileManager {

    private FacesMessage facesMsg;
    private static final Logger log =
            LoggerFactory.getLogger(FileManager.class);

    private void guardarArchivo(byte[] bytes, String archivo) throws FileNotFoundException, IOException {
        FileOutputStream fos;
        fos = new FileOutputStream(archivo);
        fos.write(bytes);
        fos.flush();
        fos.close();

    }

    public String construirArchivoAGuardar(String basePath, String aditionalPath, FileUploadEvent fileUploadEvent) throws Exception {
        try {

            String uploadedFileName = fileUploadEvent.getFile().getFileName();

            String justFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf(File.separatorChar) + 1);
            justFileName = justFileName.replaceAll("[áéíóúàèìòùñÁÉÍÓÚÀÈÌÒÙÑ]", "_");
            justFileName = justFileName.replaceAll(" ", "_");

            String relativePath = getRelativePath(aditionalPath, justFileName);
            String realPath = getRealpath(basePath, relativePath);

            File file = new File(realPath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            guardarArchivo(IOUtils.toByteArray(fileUploadEvent.getFile().getInputStream()), file.getAbsolutePath());

            return realPath;

        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            throw ex;
        }
    }

    public String construirArchivoAGuardar(String basePath, String aditionalPath, UploadedFile uploadedFile) throws Exception {
        try {

            String uploadedFileName = uploadedFile.getFileName();

            String justFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf(File.separatorChar) + 1);
            justFileName = justFileName.replaceAll("[áéíóúàèìòùñÁÉÍÓÚÀÈÌÒÙÑ]", "_");
            justFileName = justFileName.replaceAll(" ", "_");

            String relativePath = getRelativePath(aditionalPath, justFileName);
            String realPath = getRealpath(basePath, relativePath);

            File file = new File(realPath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            guardarArchivo(IOUtils.toByteArray(uploadedFile.getInputStream()), file.getAbsolutePath());

            return justFileName;

        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            throw ex;
        }
//        return null;

    }

    public String construirArchivoAGuardarTmp(byte[] bytes, String uploadedFileName) throws Exception {
        try {


            String justFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf(File.separatorChar) + 1);
            justFileName = justFileName.replaceAll("[áéíóúàèìòùñÁÉÍÓÚÀÈÌÒÙÑ]", "_");
            justFileName = justFileName.replaceAll(" ", "_");
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            String realPath = servletContext.getRealPath("")+File.separatorChar+"resources"
                    +File.separatorChar+"freya-layout"+File.separatorChar+"images"+File.separatorChar+
                    "tmp"+File.separatorChar+justFileName;

            File file = new File(realPath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            guardarArchivo(bytes, file.getAbsolutePath());

            return justFileName;

        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            mensajeAlerta("Error al subir el archivo");
            throw ex;
        }
//        return null;

    }

    

    public String construirArchivoAGuardar(String basePath, String aditionalPath, UploadedFile uploadedFile, String nombreArchivo) throws Exception {
        try {

            String justFileName = nombreArchivo;

            String relativePath = getRelativePath(aditionalPath, justFileName);
            String realPath = getRealpath(basePath, relativePath);

            File file = new File(realPath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            guardarArchivo(IOUtils.toByteArray(uploadedFile.getInputStream()), file.getAbsolutePath());

            return justFileName;

        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            throw ex;
        }
//        return null;

    }
    
    public String construirArchivoAGuardar(String basePath, String aditionalPath, String nombre, byte[] fileContent) throws Exception {
        try {

            String justFileName = nombre.substring(nombre.lastIndexOf(File.separatorChar) + 1);
            justFileName = justFileName.replaceAll("[áéíóúàèìòùñÁÉÍÓÚÀÈÌÒÙÑ]", "_");
            justFileName = justFileName.replaceAll(" ", "_");

            String relativePath = getRelativePath(aditionalPath, justFileName);
            String realPath = getRealpath(basePath, relativePath);

            File file = new File(realPath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            guardarArchivo(fileContent, file.getAbsolutePath());

            return justFileName;

        } catch (Exception ex) {
            log.error("Error al intentar levantar su archivo al servidor. ", ex);
            throw ex;
        }
//        return null;

    }

    private String getRealpath(String basePath, String relativePath) {
        StringBuilder builder = new StringBuilder();
        builder.append(basePath).append(File.separator).append(relativePath);
        return builder.toString();
    }

    private String getRelativePath(String aditionalPath, String fileName) {
        StringBuilder builder = new StringBuilder();
        builder.append(aditionalPath).append(fileName);
        return builder.toString();

    }

    public void descargarArchivos(String nombreDocumento, String dirFile, String nombreDocumentoDescarga) {
        try {

            File directorio = new File(dirFile + nombreDocumento);

            if (directorio.exists()) {
                FacesContext faces = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();
                response.setHeader("Content-disposition", "attachment; filename=\"" + nombreDocumentoDescarga + "");
                response.setContentType("application/" + dirFile.substring(dirFile.indexOf('.') + 1));
                FileInputStream fileInputStream = new FileInputStream(directorio);

                int i;
                while ((i = fileInputStream.read()) != -1) {
                    response.getOutputStream().write(i);
                }

                fileInputStream.close();
                response.getOutputStream().close();
                faces.responseComplete();
            } else {
                mensajeAlerta("No fue posible descargar el archivo. No existe el archivo: " + directorio);

                return;

            }
        } catch (Exception e) {
            mensajeError("Ocurrio un error al descargar el archivo");
            log.error("Error al descargar el archivo", e);
        }
    }

    public void borrarDirectorio(String nombreDocumento, String dirFile) {
        try {

            dirFile = dirFile + nombreDocumento;
            File file = new File(dirFile);
            System.out.println("Ruta Dir a borrar: " + dirFile);
            if (file.exists()) {
                file.delete();
            }

        } catch (Exception ex) {
            log.error("Error al eliminar el archivo", ex);
            mensajeError("Ocurrio un error al eliminar el archivo.");
        }
    }

    public void eliminarDirectorio(String dirPath) {
        try {
            FileUtils.deleteDirectory(new File(dirPath));
        } catch (Exception ex) {
            log.error(FileManager.class.getName(), ex);
        }
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addToZipFile(String fileName, String zipFileName, ZipOutputStream zos) throws Exception {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(zipFileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    public static void addToZipFile(OutputStream os, String zipFileName, ZipOutputStream zos) throws Exception {

//		System.out.println("Writing '" + fileName + "' to zip file");
        ByteArrayOutputStream bos = (ByteArrayOutputStream) os;
        bos.toByteArray();

        ZipEntry zipEntry = new ZipEntry(zipFileName);
        zos.putNextEntry(zipEntry);

        zos.write(bos.toByteArray());

        zos.closeEntry();

    }

    public String cleanFileName(String originalName){
        String justFileName = originalName.substring(originalName.lastIndexOf(File.separatorChar) + 1);
        justFileName = justFileName.replaceAll("[áéíóúàèìòùñÁÉÍÓÚÀÈÌÒÙÑ]", "_");
        justFileName = justFileName.replaceAll(" ", "_");
        return justFileName;
    }

}
