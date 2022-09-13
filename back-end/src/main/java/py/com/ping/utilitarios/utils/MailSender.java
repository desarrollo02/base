package py.com.ping.utilitarios.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MailSender {
    private static final Logger log =
            LoggerFactory.getLogger(MailSender.class);

    private final String username;
    private final String password;
    private final String host;
    private final int port;
    private String useTls;
    private String from;
    private String to;
    private String subject;
    private String message;
    private Map<String[], byte[]> attachments;

    private String emailDebug;

    public MailSender(){
        this.username = "";
        this.password = "";
        this.host = "";
        this.port = 0;
    }

    public MailSender(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public MailSender(MailConfigDto mailConfigDto) {
        this.username = mailConfigDto.getUser();
        this.host = mailConfigDto.getHost();
        this.port = Integer.parseInt(mailConfigDto.getPort());
        this.password = mailConfigDto.getPwd();
        this.useTls = mailConfigDto.getTls();
        this.from = mailConfigDto.getFrom();
        this.emailDebug = mailConfigDto.getEmailDebug();
    }

    public void setContent(String to, String subject, String message, Map<String[], byte[]> attachments){
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.attachments = attachments;
    }

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", this.useTls);
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendMailWithAttachment(Session session) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.from));
        message.setSubject(this.subject);
        if(this.emailDebug != null && !this.emailDebug.equals("")){
            log.info("Se enviara correo a email de depuración ".concat(this.emailDebug));
            this.to = this.emailDebug;
        }
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));

        if(!attachments.isEmpty()){
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(this.message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            attachments.forEach((attachmentData, attachment) -> {
                try {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(createTempFileFromByteArray(attachmentData, attachment));
                    multipart.addBodyPart(attachmentPart);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            message.setContent(multipart);
        }else{
            message.setText(this.message);
        }
        Transport.send(message);
    }

    private File createTempFileFromByteArray(String[] attachmentData, byte[] byteArray) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile(attachmentData[0], attachmentData[1]);
            Files.write(tempFile, byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile.toFile();
    }
    private File getFile(String filename) {
        try {
            URI uri = this.getClass()
              .getClassLoader()
              .getResource(filename)
              .toURI();
            return new File(uri);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to find file from resources: " + filename);
        }
    }
}
