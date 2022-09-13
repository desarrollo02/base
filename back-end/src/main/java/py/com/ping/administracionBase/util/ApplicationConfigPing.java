package py.com.ping.administracionBase.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author rvallejos
 */
public class ApplicationConfigPing {

    private static Properties configProp;
    private static URL url;

    static {
        try {
            configProp = new Properties();
            InputStream in = ApplicationConfigPing.class.getClassLoader().getResourceAsStream("config.properties");
            configProp.load(in);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuracion");
        }
    }

    public static String getArchivoParametrosGenerales() {
        try {
            return configProp.getProperty("ARCHIVO_PARAMETROS_GENERALES").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro ARCHIVO_PARAMETROS_GENERALES");
        }
        return "utilitarios.properties";
    }

    public static String getArchivoInternacionalizacion() {
        try {
            return configProp.getProperty("ARCHIVO_INTERNACIONALIZACION").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro ARCHIVO_INTERNACIONALIZACION");
        }
        return "i18n";
    }

    public static String getSeparadorWindows() {
        try {
            return configProp.getProperty("SEPARADOR_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro SEPARADOR_WINDOWS");
        }
        return "\\";
    }

    public static String getCarpetaWindows() {
        try {
            return configProp.getProperty("CARPETA_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_WINDOWS");
        }
        return "c:\\inv\\propiedades";
    }

    public static String getCarpetaLinux() {
        try {
            return configProp.getProperty("CARPETA_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_LINUX");
        }
        return "/home/server/ping/propiedades/";
    }

    public static String getCarpetaReportesWindows() {
        try {
            return configProp.getProperty("CARPETA_REPORTES_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_REPORTES_WINDOWS");
        }

        return "C:\\Cosmesoft\\Proyectos\\YkuaSati\\Implementacion PingERP\\ykuasati\\reportes";

    }

    public static String getCarpetaProcesosWindows() {
        try {
            return configProp.getProperty("CARPETA_PROCESOS_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_REPORTES_WINDOWS");
        }
        return "C:\\Cosmesoft\\Proyectos\\FDM\\fdm\\procesos";
        //  return "J:\\proyectos-cosmesoft\\siceamso\\procesos";

    }

    public static String getCarpetaDocRRHHWindows() {
        try {
            return configProp.getProperty("CARPETA_DOCUMENTOS_RRHH_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_DOCUMENTOS_RRHH_WINDOWS");
        }
        return "D:\\ProyectosCeamso\\\\siceamso\\rrhh";
        //  return "J:\\proyectos-cosmesoft\\siceamso\\procesos";

    }

    public static String getCarpetaRDCWindows() {
        try {
            return configProp.getProperty("CARPETA_RDC_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_RDC_WINDOWS");
        }
        return "C:\\inv\\procesos\\rdc";

    }

    public static String getCarpetaPropuestasWindows() {
        try {
            return configProp.getProperty("CARPETA_PROCESOS_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_PROPUESTAS_WINDOWS");
        }
        return "F:\\Proyectos Ping\\Ceamso\\Sistema\\siceamso\\procesos";
//        return "C:\\inv\\procesos\\propuestas";

    }

    public static String getCarpetaReportesLinux() {
        try {
            return configProp.getProperty("CARPETA_REPORTES_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_REPORTES_LINUX");
        }
        return "/home/server/ping/reportes";
    }

    public static String getCarpetaProcesosLinux() {
        try {
            return configProp.getProperty("CARPETA_PROCESOS_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_PROCESOS_LINUX");
        }
        return "/home/server/ping/procesos";
    }

    public static String getCarpetaDocRRHHLinux() {
        try {
            return configProp.getProperty("CARPETA_DOCUMENTOS_RRHH_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_DOCUMENTOS_RRHH_LINUX");
        }
        return "/home/server/ping/rrhh";
    }

    public static String getCarpetaRDCLinux() {
        try {
            return configProp.getProperty("CARPETA_RDC_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_RDC_LINUX");
        }
        return "/home/server/ping/procesos/rdc";
    }

    public static String getCarpetaPropuestasLinux() {
        try {
            return configProp.getProperty("CARPETA_PROPUESTAS_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_PROPUESTAS_LINUX");
        }
        return "/home/server/ping/procesos/propuestas";
    }

    /**
     * Retorna el path completo donde deberia estar el reporte. <b>La idea es
     * evitar que en todas partes se pregunte que sistema operativo se esta
     * utilizando.
     *
     * @param reportName el nombre del reporte
     * @return el path completo donde se deberia encontrar el reporte
     */
    public static String getReportesPath(String reportName) {
        String separator = File.separator;
        StringBuilder basePathBuilder = new StringBuilder();
        if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesWindows()).append(separator);
        } else {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesLinux()).append(separator);
        }
        return basePathBuilder.toString() + reportName;
    }

    /**
     * Retorna el path completo donde deberia estar la carpeta de reportes.
     * <b>La idea es evitar que en todas partes se pregunte que sistema
     * operativo se esta utilizando.</b>
     *
     * @return el path completo donde se deberia encontrar la carpeta de
     * reportes
     */
    public static StringBuilder getBasePathBuilder() {

        String separator = File.separator;
        StringBuilder basePathBuilder = new StringBuilder();
        if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesWindows()).append(separator);
        } else {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesLinux()).append(separator);
        }
        return basePathBuilder;
    }

      public static MailConfigDto getMailConfig() {
        MailConfigDto mailConfigDto = null;
        try {
            mailConfigDto = new MailConfigDto();
            mailConfigDto.setFrom(ApplicationConstant.from);
            mailConfigDto.setHost(ApplicationConstant.host);
            mailConfigDto.setPort(ApplicationConstant.port);
            mailConfigDto.setTls(ApplicationConstant.tls);
            mailConfigDto.setAuth(ApplicationConstant.auth);
            mailConfigDto.setUser(ApplicationConstant.user);
            mailConfigDto.setPwd(ApplicationConstant.pwd);
            mailConfigDto.setActive(ApplicationConstant.active);
            mailConfigDto.setIsGmail(ApplicationConstant.isGmail);
            mailConfigDto.setBcc(ApplicationConstant.bcc);

        } catch (Exception e) {
            System.out.println("Error al leer parametros");
        }

        return mailConfigDto;
    }

    public static MailConfigDto getMailLLamadoConfig() {
        MailConfigDto mailConfigDto = null;
        try {
            mailConfigDto = new MailConfigDto();
            mailConfigDto.setFrom(configProp.getProperty("mail_llamado.from"));
            mailConfigDto.setHost(configProp.getProperty("mail_llamado.host"));
            mailConfigDto.setPort(configProp.getProperty("mail_llamado.port"));
            mailConfigDto.setTls(configProp.getProperty("mail_llamado.tls"));
            mailConfigDto.setAuth(configProp.getProperty("mail_llamado.auth"));
            mailConfigDto.setUser(configProp.getProperty("mail_llamado.user"));
            mailConfigDto.setPwd(configProp.getProperty("mail_llamado.pwd"));
            mailConfigDto.setActive(configProp.getProperty("mail_llamado.active"));
            mailConfigDto.setIsGmail(configProp.getProperty("mail_llamado.isGmail"));

        } catch (Exception e) {
            System.out.println("Error al leer parametros");
        }

        return mailConfigDto;
    }
          public static String getCarpetaDocIndicadoresLinux() {
        try {
            return configProp.getProperty("CARPETA_DOCUMENTOS_INDICADOR_LINUX").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_DOCUMENTOS_INDICADOR_LINUX");
        }
        return "/home/ceamso/ping/rrhh";
    }
      
         public static String getCarpetaIndicadorWindows() {
        try {
            return configProp.getProperty("CARPETA_INDICADOR_WINDOWS").trim();
        } catch (Exception e) {
            System.out.println("Error al leer el parametro CARPETA_RDC_WINDOWS");
        }
        return "C:\\inv\\procesos\\rdc";

    }
}
