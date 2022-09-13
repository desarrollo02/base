/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utils;

import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.util.ApplicationConstant;

/**
 *
 * @author rudy
 */
public class ReportUtils {

    public static void addLogo(FieldsMetadata fieldsMetadata, IContext context, StringBuilder basePathBuilder, BswEmpresas empresas, boolean esHorizontal) {
        IImageProvider logo_izq = null;
        IImageProvider logo_der = null;

        try {
//            logo_izq = new FileImageProvider(new File(basePathBuilder.toString() + ApplicationConstant.LOGO_PATH + File.separator + ApplicationConstant.LOGO_IZQ));
//            logo_izq.setSize(200f, 100f);
        } catch (Exception e) {
        }

        try {
//            logo_der = new FileImageProvider(new File(basePathBuilder.toString() + ApplicationConstant.LOGO_PATH + File.separator + ((sucursal!=null && sucursal.getPathImagen()!=null && !sucursal.getPathImagen().isEmpty())?sucursal.getPathImagen():ApplicationConstant.LOGO_DER)));
//            logo_der.setSize(200f, 100f);

//            System.out.println("nombrelogo=" + ((sucursal != null && sucursal.getPathImagen() != null && !sucursal.getPathImagen().isEmpty() ? sucursal.getPathImagen() : ApplicationConstant.LOGO_DER)));
//            logo_der = new FileImageProvider(new File(basePathBuilder.toString() + ApplicationConstant.LOGO_PATH + File.separator + ((sucursal != null && sucursal.getPathImagen() != null && !sucursal.getPathImagen().isEmpty()) ? sucursal.getPathImagen() : ApplicationConstant.LOGO_DER)));
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            String realPath = servletContext.getRealPath("") + File.separatorChar + "resources"
                    + File.separatorChar + "serenity-layout" + File.separatorChar + "images" + File.separatorChar
                    + "tmp" + File.separatorChar;

            logo_der = new FileImageProvider(new File(realPath + ((empresas != null && empresas.getNombreImagen() != null && !empresas.getNombreImagen().isEmpty()) ? empresas.getNombreImagen() : ApplicationConstant.LOGO_DER)));

            if (!esHorizontal) {
                logo_der.setWidth(ApplicationConstant.LOGO_ANCHO);
                logo_der.setHeight(ApplicationConstant.LOGO_ALTO);
            } else {
                logo_der.setWidth(ApplicationConstant.LOGO_ANCHO_HORIZONTAL);
                logo_der.setHeight(ApplicationConstant.LOGO_ALTO_HORIZONTAL);
            }
        } catch (Exception e) {
        }

//        if (logo_izq != null) {
//            fieldsMetadata.addFieldAsImage("logo_izq");
//            context.put("logo_izq", logo_izq);
//        }
        if (logo_der != null) {
            fieldsMetadata.addFieldAsImage("logo_der");
            context.put("logo_der", logo_der);
        }

    }
}
