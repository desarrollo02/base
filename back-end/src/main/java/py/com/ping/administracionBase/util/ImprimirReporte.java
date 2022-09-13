/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import com.lowagie.text.pdf.PdfWriter;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.primefaces.PrimeFaces;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.utilitarios.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author inv
 */
public class ImprimirReporte extends Reporte implements Serializable {

    public static final String REPORTE_FACTURA = "MisetFacturaElectronica";
    public static final String REPORTE_NOTA_CREDITO = "MisetNotaCreditoElectronica";
    public static final String REPORTE_AUTO_FACTURA = "DEAutoFacturaElectronica";
    public static final String REPORTE_NOTA_DEBITO = "MisetNotaDebitoElectronica";
    public static final String REPORTE_REMISION = "kude_nota_remision";
    private static final Logger logger =
            LoggerFactory.getLogger(ImprimirReporte.class);

    private String nombreReporte;
    private String nombreReporteSinJasper;
    private Map<String, Object> parametros;
    private BswEmpresas empresaLogueada;
    private FacesContext faces;
    private HttpServletResponse response;
    BaseLecturaArchivos archivoConfig;
    SimpleDateFormat sdf;
//    private String imagenPath;
    private String nombreImagen;
    Connection conn;

    //Seteada a true cuando se desea imprimir directamente el ticket en la impresora
    private Boolean imprimirTicket;

    private IContext iContext;
    private FieldsMetadata fieldsMetadata;

    public final void inicializaVariables() {
        nombreReporte = "PonerNombreReporte";
        parametros = new HashMap<String, Object>();
        sdf = new SimpleDateFormat("yyyyMMdd_HHmm");

        try {
            archivoConfig = new BaseLecturaArchivos();
            archivoConfig.setNombreArchivo(ApplicationConfigPing.getArchivoParametrosGenerales());
            nombreImagen = archivoConfig.getPropiedad(ApplicationConstant.NOMBRELOGO);
        } catch (FileNotFoundException ex) {
            logger.error("Error al intentar leer el archivo", ex);
        } catch (IOException ex) {
            logger.error("Error de accesso al archivo", ex);
        }
    }

    public ImprimirReporte() {
        inicializaVariables();
    }

    public ImprimirReporte(BswEmpresas emp) {
        empresaLogueada = emp;
        inicializaVariables();

    }

    public Boolean isImprimirTicket() {
        return imprimirTicket == null ? false : imprimirTicket;
    }

    public void setImprimirTicket(Boolean imprimirTicket) {
        this.imprimirTicket = imprimirTicket;
    }

    @Override
    public byte[] generarReporte() {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesWindows() + separator);

            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesLinux() + separator);

            }
            String pt = ImprimirReporte.class.getResource("/reports/").getPath(); //relative path
            parametros.put("SUBREPORT_DIR", pt);
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            nombreReporte = nombreReporte + ".jasper";
            path += separator + nombreReporte; //path de los reportes
            logger.info("path kuede = " + path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            try {
                getConnectionDS();
            } catch (Exception ex) {
                logger.error("Error al obtener conexion para el reporte.", ex);
            }

            logger.info("parametros de kude " + parametros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
            if (isImprimirTicket()) {
                generarTicket(jasperPrint);
            } else {
                return generarPdfYRetornar(jasperPrint);
            }

        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al intentar cerrar la conexion.", ex);
            }
        }

        return null;
    }

    @Override
    public void imprimir() {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesWindows() + separator);

            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesLinux() + separator);

            }

            nombreReporteSinJasper = nombreReporte; // para q al imprimir el nombre del reporte no aparezca con la extension .jasper
            nombreReporte = nombreReporte + ".jasper";
            path += separator + nombreReporte; //path de los reportes

            parametros.put("nombreImagen", nombreImagen);
            //parametros.put("impresoPor", BaseCalendar.getAhora());
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("imagenPath", imagenPath + separator);
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            System.out.println("************los parametros son " + parametros);

//            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            getConnectionDS();
            //  JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
            if (isImprimirTicket()) {
                generarTicket(jasperPrint);
            } else {
                generarPdf(jasperPrint);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al intentar generar el reporte.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al intentar cerrar la conexion.", ex);
            }
        }
    }

    public void imprimirDirect(String nombreImpresora, float margenIzq, float margenDer, float margenPie, float margenArriba, boolean horizontal) {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
            }
            nombreReporteSinJasper = nombreReporte; // para q al imprimir el nombre del reporte no aparezca con la extension .jasper
            nombreReporte = nombreReporte + ".jasper";
            path += separator + nombreReporte; //path de los reportes
            parametros.put("imagenPath", imagenPath + separator);

            parametros.put("nombreImagen", nombreImagen);
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            getConnectionDS();
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
            //generarPdf(jasperPrint);
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            int selectedService = 0;
            for (int i = 0; i < services.length; i++) {
                if (services[i].getName().toUpperCase().contains(nombreImpresora.toUpperCase())) {
                    selectedService = i;
                }
            }
            //Insets insets=new Insets(5, 10, 10, 5);
            float leftMargin = margenIzq;// insets.top;
            float rightMargin = margenDer;
            float topMargin = margenArriba;
            float bottomMargin = margenPie;
            job.setPrintService(services[selectedService]);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            //MediaSizeName mediaSizeName = MediaSize.findMedia(4, 4, MediaPrintableArea.INCH);
            printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
            MediaSize mediaSize = MediaSize.NA.LETTER;
            float mediaWidth = mediaSize.getX(Size2DSyntax.MM);
            float mediaHeight = mediaSize.getY(Size2DSyntax.MM);
            //printRequestAttributeSet.add(mediaSizeName.NA_LETTER);
            printRequestAttributeSet.add(new Copies(1));
            if (horizontal) {
                printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
            } else {
                printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
            }

            printRequestAttributeSet.add(new MediaPrintableArea(
                    leftMargin, topMargin,
                    (mediaWidth - leftMargin - rightMargin),
                    (mediaHeight - topMargin - bottomMargin), Size2DSyntax.MM));
            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();

        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al intentar cerrar la conexion.", ex);
            }
        }
    }

    public void imprimir(List registros) {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();;
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
            }
            nombreReporteSinJasper = nombreReporte; // para q al imprimir el nombre del reporte no aparezca con la extension .jasper
            nombreReporte = nombreReporte + ".jasper";
            path += separator + nombreReporte; //path de los reportes
            parametros.put("imagenPath", imagenPath + separator);

//                ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))))
//                parametros.put("foto", empresaLogueada.getFoto());
            parametros.put("nombreImagen", nombreImagen);
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(registros));

            generarPdf(jasperPrint);

        } catch (Exception ex) {
            logger.error("Error al intentar generar el reporte.", ex);
        }

    }

    public void imprimirMatricial() {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();;
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
            }

            nombreReporte = nombreReporte + ".jasper";

            path += separator + nombreReporte; //path de los reportes
            parametros.put("imagenPath", imagenPath + separator);

            parametros.put("nombreImagen", nombreImagen);
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            //  JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            getConnectionDS();
            String defaultPDFFont = "Arial";

            //JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            //JRProperties.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);

            generarTicket(jasperPrint);

        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al intentar cerrar la conexion.", ex);
            }
        }
    }

    public void imprimirMatricial(List registros) {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();;
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
            }

            nombreReporte = nombreReporte + ".jasper";

            path += separator + nombreReporte; //path de los reportes
            parametros.put("imagenPath", imagenPath + separator);
            parametros.put("nombreImagen", nombreImagen);

            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            // JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(registros));
            JasperPrintManager.printReport(jasperPrint, false);

        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        }
    }

    public void imprimirXLS() {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();;
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesWindows() + separator);
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
                parametros.put("SUBREPORT_DIR", ApplicationConfigPing.getCarpetaReportesLinux() + separator);
            }

            parametros.put("imagenPath", imagenPath + separator);
            parametros.put("nombreImagen", nombreImagen);
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(path + separator + nombreReporte + ".jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path + separator + nombreReporte + ".jasper");

            getConnectionDS();
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parametros, conn);

            generarExcel(jp, xlsReport);

        } catch (JRException jre) {
            logger.error("Error al intentar generar el reporte.", jre);
        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al intentar cerrar la conexion.", ex);
            }
        }
    }

    public void imprimirXLS(List registros) {
        String path = "";
        String imagenPath = "";
        String separator = File.separator;
        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
        try {
            if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
                path = ApplicationConfigPing.getCarpetaReportesWindows();;
                imagenPath = ApplicationConfigPing.getCarpetaWindows();
            } else {
                path = ApplicationConfigPing.getCarpetaReportesLinux();
                imagenPath = ApplicationConfigPing.getCarpetaLinux();
            }

            parametros.put("imagenPath", imagenPath + separator);
            parametros.put("nombreImagen", nombreImagen);
            parametros.put("DiaHora", BaseCalendar.getAhora());
            parametros.put("parametros", "Los parametros utilizados para generar el informe fueron: " + parametros);
            if (empresaLogueada != null) {
                if (empresaLogueada.getFoto() != null) {
                    try (InputStream inputStream = new ByteArrayInputStream(empresaLogueada.getFoto())) {
                        parametros.put("foto", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
                    } catch (JRException | IOException e) {
                        throw new RuntimeException("Failed to load image", e);
                    }
                }
                parametros.put("empresa_descipcion", empresaLogueada.getDescripcion());
            }
            // JasperReport jasperReport = (JasperReport) JRLoader.loadObject(path + separator + nombreReporte + ".jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path + separator + nombreReporte + ".jasper");

            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(registros));

            generarExcel(jp, xlsReport);

        } catch (JRException jre) {
            logger.error("Error al intentar generar el reporte.", jre);
        } catch (Exception e) {
            logger.error("Error al intentar generar el reporte.", e);
        }
    }

    public void beforeReport(String vparametros, String maquina, Long idEmpresa, Long idUsuario, String codUsuario, String nombreReporte) {

        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("");
            Connection conn = ds.getConnection();
            // carga los parametros de entrada IN
            CallableStatement sentencia = conn.prepareCall("{call Actualiza_auditoria_repor_w(?,?,?,?,?,?)}");
            sentencia.setLong(1, idEmpresa);
            sentencia.setLong(2, idUsuario);
            sentencia.setString(3, nombreReporte);
            sentencia.setString(4, vparametros);
            sentencia.setString(5, maquina);
            sentencia.setString(6, codUsuario);
            sentencia.executeQuery();

        } catch (Exception e) {
            logger.error("Error al llamar al procedimiento beforeReport", e);
        }

    }

    @Override
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
        parametros.put("nombreReporte", nombreReporte);
    }

    @Override
    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
        if (nombreReporte != null && !nombreReporte.equals("")) {
            this.parametros.put("nombreReporte", nombreReporte);
        }
    }

    private void generarPdf(JasperPrint jp) throws Exception {
        faces = FacesContext.getCurrentInstance();
        byte[] report = JasperExportManager.exportReportToPdf(jp);
        response = (HttpServletResponse) faces.getExternalContext().getResponse();
        response.setHeader("Content-disposition", "attachment; filename=" + nombreReporteSinJasper + "_" + sdf.format(new Date()) + ".pdf");
        response.setContentType("application/pdf");
        response.setContentLength(report.length);
        ServletOutputStream out = response.getOutputStream();
        out.write(report);
        faces.responseComplete();
        out.flush();
        out.close();
    }

    private byte[] generarPdfYRetornar(JasperPrint jp) throws Exception {
        return JasperExportManager.exportReportToPdf(jp);
    }

    private void generarPdfPrint(JasperPrint jp) throws Exception {
        faces = FacesContext.getCurrentInstance();
        byte[] report = JasperExportManager.exportReportToPdf(jp);
        response = (HttpServletResponse) faces.getExternalContext().getResponse();
        response.setHeader("Content-disposition", "inline; filename=" + nombreReporteSinJasper + "_" + sdf.format(new Date()) + ".pdf");
        response.setContentType("application/pdf");
        response.setContentLength(report.length);
        ServletOutputStream out = response.getOutputStream();
        out.write(report);
        faces.responseComplete();
        out.flush();
        out.close();
    }

//    private void generarTicket(JasperPrint jasperPrint) throws JRException, UnsupportedEncodingException, PrinterException {
//        PrinterJob job = PrinterJob.getPrinterJob();
//        PrintService services = PrintServiceLookup.lookupDefaultPrintService();
//        job.setPrintService(services);
//
//        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services);
//        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services.getAttributes());
//        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
//        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
//
//        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-15");
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//        //        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 80);
//        //        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 64);
//        exporter.exportReport();
//        byte[] report = baos.toByteArray();//JasperPrintManager.printReportToPdf(jasperPrint);
//        String encodeReport = Base64.encodeBytes(report);
//        RequestContext requestContext = RequestContext.getCurrentInstance();
//        requestContext.execute("print64('" + encodeReport + "')");
//
//    }
    private void generarTicket(JasperPrint jasperPrint) throws JRException, UnsupportedEncodingException, MessagingException, IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
        configuration.setPdfJavaScript("this.print();");

        exporter.setConfiguration(configuration);
        exporter.exportReport();

//        byte[] report = JasperExportManager.exportReportToPdf(jp);
        byte[] report = os.toByteArray();
//        byte[] report = JasperExportManager.exportReportToPdf(jasperPrint);
        String encodeReport = Base64.encodeBytes(report);
        PrimeFaces.current().executeScript("print64('" + encodeReport + "')");
    }

    private void generarExcel(JasperPrint jp, ByteArrayOutputStream byteReporte) throws JRException, Exception {
        faces = FacesContext.getCurrentInstance();
        JRXlsExporter xlsExporter = new JRXlsExporter();
        response = (HttpServletResponse) faces.getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=\"" + nombreReporte + "_" + System.currentTimeMillis() + ".xls\"");
        xlsExporter.setExporterInput(new SimpleExporterInput(jp));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteReporte));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        xlsExporter.setConfiguration(configuration);

        xlsExporter.exportReport();

        byte[] report = byteReporte.toByteArray();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(report);
        faces.responseComplete();
        outputStream.flush();
        outputStream.close();
    }

    public void getConnectionDS() throws Exception {
        // Obtain the initial Java Naming and Directory Interface
        // (JNDI) context.
        InitialContext initCtx = new InitialContext();
        // Perform JNDI lookup to obtain the resource.
        DataSource ds = (DataSource) initCtx.lookup("");
        conn = ds.getConnection();
    }

    public void imprimirReporteDocx(String templateFileName, Map<String, Class<?>> fieldMetadata, Map<String, Object> icontextValues, String outputFileName) {

        faces = FacesContext.getCurrentInstance();
//        HttpServletResponse response;
        String aux;

        String separator = File.separator;
        StringBuilder basePathBuilder = new StringBuilder();
        if (separator.equals(ApplicationConfigPing.getSeparadorWindows())) {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesWindows()).append(separator);
        } else {
            basePathBuilder.append(ApplicationConfigPing.getCarpetaReportesLinux()).append(separator);
        }
        aux = basePathBuilder.toString() + templateFileName;

        response = (HttpServletResponse) faces.getExternalContext().getResponse();

        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-Disposition", "inline; filename=" + outputFileName + "\"");

        try {
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            InputStream in = new FileInputStream(aux);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                    in, TemplateEngineKind.Velocity);

            fieldsMetadata = report.createFieldsMetadata();
            for (Map.Entry<String, Class<?>> entry : fieldMetadata.entrySet()) {
                fieldsMetadata.load(entry.getKey(), entry.getValue());
            }

            // 3) Create context Java model
            iContext = report.createContext();
            for (Map.Entry<String, Object> entry : icontextValues.entrySet()) {
                iContext.put(entry.getKey(), entry.getValue());
            }

            // 4) Generate report by merging Java model with the Docx
            OutputStream out = response.getOutputStream();
            report.process(iContext, out);

            faces.responseComplete();

        } catch (IOException | XDocReportException e) {
            logger.error("Error al intentar generar el documento", e);
        }

    }
}
