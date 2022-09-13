/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import py.com.ping.administracionBase.jpa.BswSucursales;

/**
 *
 * @author gdb
 */
public class MailUtils {

    public static void sendEmail(ArrayList<String> toList, String subject, String body) throws Exception {
        MailConfigDto mailConfigDto = ApplicationConfigPing.getMailConfig();
        if (mailConfigDto != null && mailConfigDto.getIsGmail()) {
            sendMailGmail(toList, subject, body, mailConfigDto);
        } else if (mailConfigDto != null && !mailConfigDto.getIsGmail()) {
            sendMail(toList, subject, body, mailConfigDto);
        }
    }

    public static void sendEmail(List<String> toList, String subject, String body, List<String> fileNames, List<String> mimeType, List<OutputStream> filesStream) throws Exception {
        MailConfigDto mailConfigDto = ApplicationConfigPing.getMailConfig();
        if (mailConfigDto != null && mailConfigDto.getIsGmail()) {
            sendMailGmail(toList, null, subject, body, mailConfigDto, fileNames, mimeType, filesStream);
        } else if (mailConfigDto != null && !mailConfigDto.getIsGmail()) {
            sendMail(toList, null, subject, body, mailConfigDto, fileNames, mimeType, filesStream);
        }

    }

    private static void sendMail(ArrayList<String> toList, String subject, String body, MailConfigDto mailConfigDto) throws Exception {
        try {

            if (!mailConfigDto.getActive().equalsIgnoreCase("true")) {
                System.out.println(">>>>> SendMail: not sending, mail notification disabled");
            }

            System.out.println(">>>>> SendMail: " + subject);

            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", mailConfigDto.getHost());
            //props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", mailConfigDto.getPort());
            //props.setProperty("mail.smtp.user", "julio");
            props.setProperty("mail.smtp.auth", "false");
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfigDto.getFrom()));
            for (String to : toList) {
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(to));
            }
            if (mailConfigDto.getBcc() != null && !mailConfigDto.getBcc().trim().equals("")) {
                message.addRecipient(
                        Message.RecipientType.BCC,
                        new InternetAddress(mailConfigDto.getBcc()));
            }
            message.setSubject(subject);
            message.setText(body);

            // Enviar mail
            Transport t = session.getTransport("smtp");
            t.connect();
            t.sendMessage(message, message.getAllRecipients());

            // Cierre
            t.close();
            System.out.println(">>>>> End SendMail");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    private static void sendMailGmail(ArrayList<String> toList, String subject, String body, MailConfigDto mailConfigDto) throws Exception {

        try {
            mailConfigDto = ApplicationConfigPing.getMailConfig();
            if (!mailConfigDto.getActive().equalsIgnoreCase("true")) {
                System.out.println(">>>>> SendMail: not sending, mail notification disabled");
            }

            System.out.println(">>>>> SendMail: " + subject);

            final String username = mailConfigDto.getUser();
            final String password = mailConfigDto.getPwd();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailConfigDto.getAuth());
            if (mailConfigDto.getTls().equals("true")) {
                props.put("mail.smtp.starttls.enable", mailConfigDto.getTls());
            }
            props.put("mail.smtp.host", mailConfigDto.getHost());
            props.put("mail.smtp.port", mailConfigDto.getPort());

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfigDto.getFrom()));
            for (String to : toList) {
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(to));
            }
            if (mailConfigDto.getBcc() != null && !mailConfigDto.getBcc().trim().equals("")) {
                message.addRecipient(
                        Message.RecipientType.BCC,
                        new InternetAddress(mailConfigDto.getBcc()));
            }
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println(">>>>> End SendMail");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private static void sendMailGmail(List<String> toList, List<String> ccoList, String subject, String body, MailConfigDto mailConfigDto, List<String> fileName, List<String> mimeTipe, List<OutputStream> filesStream) throws Exception {

        try {
            mailConfigDto = ApplicationConfigPing.getMailConfig();
            if (!mailConfigDto.getActive().equalsIgnoreCase("true")) {
                System.out.println(">>>>> SendMail: not sending, mail notification disabled");
            }

            System.out.println(">>>>> SendMail: " + subject);

            final String username = mailConfigDto.getUser();
            final String password = mailConfigDto.getPwd();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailConfigDto.getAuth());
            if (mailConfigDto.getTls().equals("true")) {
                props.put("mail.smtp.starttls.enable", mailConfigDto.getTls());
            }
            props.put("mail.smtp.host", mailConfigDto.getHost());
            props.put("mail.smtp.port", mailConfigDto.getPort());

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfigDto.getFrom()));
            for (String to : toList) {
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(to));
            }
            if (mailConfigDto.getBcc() != null && !mailConfigDto.getBcc().trim().equals("")) {
                message.addRecipient(
                        Message.RecipientType.BCC,
                        new InternetAddress(mailConfigDto.getBcc()));
            }

            message.setSubject(subject);
            Multipart multipart = new MimeMultipart();
            BodyPart attachmentBodyPart = new MimeBodyPart();

            /*PDF*/
//            ByteArrayOutputStream baos = (ByteArrayOutputStream) filesStreamPDF;
//            //baos.writeTo(fileStream);
//            attachmentBodyPart = new MimeBodyPart();
//            DataSource source = new ByteArrayDataSource(baos.toByteArray(), "application/vnd.ms-word.document.macroEnabled.12");
//            attachmentBodyPart.setDataHandler(new DataHandler(source));
//            attachmentBodyPart.setFileName("tdr.docm");
//            multipart.addBodyPart(attachmentBodyPart);

            /*DOCX*/
            for (int i = 0; i < fileName.size(); i++) {
                ByteArrayOutputStream baos = (ByteArrayOutputStream) filesStream.get(i);
                //baos.writeTo(fileStream);
                attachmentBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(baos.toByteArray(), mimeTipe.get(i));
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(fileName.get(i));
                multipart.addBodyPart(attachmentBodyPart);
            }
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(body, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlBodyPart);

            message.setContent(multipart);
            if (ccoList != null && !ccoList.isEmpty()) {
                for (String cco : ccoList) {
                    message.addRecipient(RecipientType.BCC, new InternetAddress(
                            cco));
                }
            }
            Transport.send(message);
            System.out.println(">>>>> End SendMail");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    
    private static void sendMail(List<String> toList, List<String> ccoList, String subject, String body, MailConfigDto mailConfigDto, List<String> fileName, List<String> mimeTipe, List<OutputStream> filesStream) throws Exception {
        try {

            if (!mailConfigDto.getActive().equalsIgnoreCase("true")) {
                System.out.println(">>>>> SendMail: not sending, mail notification disabled");
            }

            System.out.println(">>>>> SendMail: " + subject);

            final String username = mailConfigDto.getUser();
            final String password = mailConfigDto.getPwd();
            
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", mailConfigDto.getHost());
            props.setProperty("mail.smtp.starttls.enable", mailConfigDto.getTls());
            props.setProperty("mail.smtp.port", mailConfigDto.getPort());
            props.setProperty("mail.smtp.user", mailConfigDto.getUser());
            props.setProperty("mail.smtp.auth", mailConfigDto.getAuth());
            if (mailConfigDto.getTls().equals("true")) {
                props.put("mail.smtp.starttls.enable", mailConfigDto.getTls());
            }
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }});

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfigDto.getFrom()));
            for (String to : toList) {
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(to));
            }
            if (mailConfigDto.getBcc() != null && !mailConfigDto.getBcc().trim().equals("")) {
                message.addRecipient(
                        Message.RecipientType.BCC,
                        new InternetAddress(mailConfigDto.getBcc()));
            }
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            BodyPart attachmentBodyPart = new MimeBodyPart();

            /*PDF*/
//            ByteArrayOutputStream baos = (ByteArrayOutputStream) filesStreamPDF;
//            //baos.writeTo(fileStream);
//            attachmentBodyPart = new MimeBodyPart();
//            DataSource source = new ByteArrayDataSource(baos.toByteArray(), "application/vnd.ms-word.document.macroEnabled.12");
//            attachmentBodyPart.setDataHandler(new DataHandler(source));
//            attachmentBodyPart.setFileName("tdr.docm");
//            multipart.addBodyPart(attachmentBodyPart);
            for (int i = 0; i < fileName.size(); i++) {
                ByteArrayOutputStream baos = (ByteArrayOutputStream) filesStream.get(i);
                //baos.writeTo(fileStream);
                attachmentBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(baos.toByteArray(), mimeTipe.get(i));
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(fileName.get(i));
                multipart.addBodyPart(attachmentBodyPart);
            }
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(body, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlBodyPart);

            message.setContent(multipart);

            // Enviar mail
            if (ccoList != null && !ccoList.isEmpty()) {
                for (String cco : ccoList) {
                    message.addRecipient(RecipientType.BCC, new InternetAddress(
                            cco));
                }
            }
            Transport t = session.getTransport("smtp");
            t.connect();
            t.sendMessage(message, message.getAllRecipients());

            // Cierre
            t.close();
            System.out.println(">>>>> End SendMail");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static String getEnviarVoucherMailBody(String nombre) {
        StringBuilder builder = new StringBuilder();
        builder.append("<p>Estimado/a: ").append(nombre).append("</p><br/>");;
        builder.append("<p>Adjuntamos su comprobante de reserva</p><br/>");
        
        builder.append("<p>El comprobante deberá ser entregado a la persona que le reciba al llegar, a modo que pueda ubicarle en la casa asignada, y estar a su disposición por cualquier eventualidad.</p><br/>");
        
        builder.append("<p>Gracias por su preferencia!</p><br/>");
        builder.append("<p>Posada Pedazo de Cielo</p>");
        builder.append("<p>www.pedazodecielo.com.py</p><br/>");
        return builder.toString();

    }
    
    public static String getAdminNuevoBlogMailBody(String titulo, String tipo, String Descripcion) {
        StringBuilder builder = new StringBuilder();
        builder.append("Se creó un nuevo blog que requiere su revisión:\n");
        builder.append("Tipo : ").append(tipo).append("\n");
        builder.append("Titulo : ").append(titulo).append("\n");;
        builder.append("Descripcion :").append(Descripcion).append("\n\n\n\n\n\n");;
        builder.append("Portal OfertasPyme ");
        return builder.toString();

    }

    public static String getCierreCajaMailBody(String sucursal, String habilitacion,
            String caja, String fecha, String hora,
            String operador, String montoFacturado, String montoTotalFacturado) {
        StringBuilder builder = new StringBuilder();
        builder.append("Resumen de cierre de caja:\n");
        builder.append("Sucursal : ").append(sucursal).append("\n");
        builder.append("Fecha Cierre: ").append(fecha).append("\n");
        builder.append("Hora Cierre: ").append(hora).append("\n");
        builder.append("Habilitacion: ").append(habilitacion).append("\n");;
        builder.append("Caja: ").append(caja).append("\n");
        builder.append("Cajero: ").append(operador).append("\n");
        builder.append("Monto Facturado en Caja: Gs.").append(montoFacturado).append("\n");
        builder.append("Monto Total Facturado hasta el cierre: Gs.").append(montoTotalFacturado).append("\n\n\n");
        return builder.toString();

    }

    public static String getAceptacionRDAMailBody(int rdaNro, String remitente, BswSucursales sucursal) {
        StringBuilder builder = new StringBuilder();
        builder.append("Requerimiento de Adquisición (RDA) pendiente de su aprobación:\n");
        builder.append("Programa: ").append(sucursal.getDescripcion()).append("\n");
        builder.append("RDA Nro: ").append(rdaNro).append("\n");

        builder.append("Usuario Remitente: ").append(remitente).append("\n");
        builder.append("Ingrese al sistema para su aprobación.").append("\n");
        return builder.toString();

    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String getPendientePublicacionMailBody(int rdaNro, String remitente) {
        StringBuilder builder = new StringBuilder();
        builder.append("Solicitud Pendiente de Publicacion:\n");
        builder.append("RDA Nro: ").append(rdaNro).append("\n");
        builder.append("Usuario Remitente: ").append(remitente).append("\n");
        builder.append("Ingrese al sistema para su aprobación.").append("\n");
        return builder.toString();

    }

    public static String getAceptacionProductoAsistenteMailBody(String contratoNro, int productoNro,
            String nombreProveedor, String remitente, BswSucursales sucursal) {
        StringBuilder builder = new StringBuilder();
        builder.append("Aceptación de Producto Pendiente de su aprobación:\n");
        builder.append("Programa: ").append(sucursal.getDescripcion()).append("\n");
        builder.append("Contrato Nro: ").append(contratoNro).append("\n");
        builder.append("Producto Nro: ").append(productoNro).append("\n");
        builder.append("Proveedor: ").append(nombreProveedor).append("\n");
        builder.append("Usuario Remitente: ").append(remitente).append("\n");
        builder.append("Ingrese al sistema para su aprobación.").append("\n");
        return builder.toString();

    }

    public static String getSolicitudAdendaMailBody(String mensaje, String nroRDC,
            String titulo, String userremitente) {
        StringBuilder builder = new StringBuilder();
        builder.append("Solicitud de adenda de RDC-" + nroRDC + " -" + titulo + "\n");
        builder.append("\n");
        builder.append("").append(mensaje).append("\n");
        builder.append("Enviado por: ").append(userremitente).append("\n");
        builder.append("\n").append("\n");
        builder.append("Obs: Debe responder la consulta en el sistema para continuar el proceso del llamado.").append("\n");
        return builder.toString();

    }

    public static String getPreguntaRDCMailBody(String pregunta, String nroRDC,
            String titulo, String remitente, String userremitente) {
        StringBuilder builder = new StringBuilder();
        builder.append("Consulta sobre RDC-" + nroRDC + " -" + titulo + "\n");
        builder.append("\n");
        builder.append("Pregunta: ").append(pregunta).append("\n");
        builder.append("Remitente: ").append(remitente).append("\n");
        builder.append("Usuario: ").append(userremitente).append("\n");
        builder.append("\n").append("\n");
        builder.append("Obs: Debe responder la consulta en el sistema para continuar el proceso del llamado.").append("\n");
        return builder.toString();

    }

    public static String getAdminNuevoProductoMailBody(String codigo, String titulo, String empresa, String operador) {
        StringBuilder builder = new StringBuilder();
        builder.append("Se creó un nuevo producto que requiere su revisión::\n");
        builder.append("Codigo : ").append(codigo).append("\n");
        builder.append("Titulo : ").append(titulo).append("\n");;
        builder.append("Empresa :").append(empresa).append("\n");
        builder.append("Operador : ").append(operador).append("\n\n\n\n");
        builder.append("Portal OfertasPyme ");
        return builder.toString();

    }

    public static String getAdminNuevoContactoMailBody(String nombreContacto, String emailContacto, String mensaje) {
        StringBuilder builder = new StringBuilder();
        builder.append("Nombre : ").append(nombreContacto).append("\n");
        builder.append("Email : ").append(emailContacto).append("\n");;
        builder.append("Mensaje :").append(mensaje).append("\n\n\n\n");
        builder.append("Portal OfertasPyme ");
        return builder.toString();

    }

    public static String getOperadorNuevaOrdenMailBody(Integer id, String nombreCliente, String emailCliente) {
        StringBuilder builder = new StringBuilder();
        builder.append("ID de la Orden de Compra : ").append(id).append("\n");
        builder.append("Nombre : ").append(nombreCliente).append("\n");
        builder.append("Email del cliente: ").append(emailCliente).append("\n\n\n\n");
        builder.append("Portal OfertasPyme ");
        return builder.toString();

    }

}
