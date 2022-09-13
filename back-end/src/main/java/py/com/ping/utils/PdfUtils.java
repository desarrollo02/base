/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utils;

import org.primefaces.shaded.commons.io.FilenameUtils;

import java.io.File;

/**
 *
 * @author Rudy
 */
public class PdfUtils {
    private static final String PATH_SOFFICE_WIN = "C:\\Program Files\\LibreOffice\\program\\soffice";
    private static final String PATH_SOFFICE_LINUX = "/libreoffice/program/soffice";
    public enum FamilyDocType{DOC, XLS};
    
    public static void convertXlsxToPdf2(String xlsxPath, String pdfPath) {
        boolean esLinux = !File.separator.equals("\\");
        StringBuilder builder = new StringBuilder();
        if (esLinux) {
            builder.append(PATH_SOFFICE_LINUX);
        } else {
            builder.append(PATH_SOFFICE_WIN);
        }

        builder.append(" --headless --convert-to pdf:calc_pdf_Export --outdir ");
        builder.append(pdfPath).append(" ");
        builder.append(xlsxPath);

        try {
            Runtime.getRuntime().exec(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public static void convertXlsxToPdf(String xlsxPath, String pdfPath) {
    	officeToPdf(xlsxPath, pdfPath, FamilyDocType.XLS);
    }
    
    
    /**
     * Recibe como parametro el path de un archivo de tipo office 
     * y genera un nuevo archivo pdf correspondiente  
     * @param inputFilePath path del archivo de entrada  
     * @param outputPdfPath path donde sera generado el archivo de salida  
     * @param type es el tipo de documento de entrada  
     */
	public static void officeToPdf(final String inputFilePath, final String outputPdfPath, FamilyDocType type) {
		String soffice = getOfficePathBinary();
		StringBuilder cmd = new StringBuilder();
		cmd.append(soffice).append(" --headless").append(" --convert-to");
		switch (type) {
		case DOC:
			cmd.append(" pdf:writer_pdf_Export");
			break;
		case XLS:
			cmd.append(" pdf:calc_pdf_Export");
			break;
		}
		//getPdfExtension(outputPdfPath, true)
		cmd.append(" --outdir ")
			.append(outputPdfPath )
			.append(" ")
			.append(inputFilePath);
				
		
		try {
			Process exec = Runtime.getRuntime().exec(cmd.toString());
			exec.waitFor();
			int result = exec.exitValue();
			System.out.println(">>>>>>>> result value " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	private static String getOfficePathBinary() {
		boolean esLinux = !File.separator.equals("\\");
		if (esLinux) {
			return PATH_SOFFICE_LINUX;
		}
		return PATH_SOFFICE_WIN;
	}
	
	
	/**
	 * Retorna la extension "pdf" a ser agregada sólo si esta es necesaria
	 * @param filename 
	 * @param 'true' devolverá ".pdf" , mientras que false solamente "pdf"
	 * @return cadena vacia "" o el literal "pdf" 
	 */
	public static String getPdfExtension(final String filename, boolean withDot) {
		if (FilenameUtils.getExtension(filename).toLowerCase().equals("pdf"))
			return "";
		return withDot ? ".pdf" : "pdf";
	}
	
}