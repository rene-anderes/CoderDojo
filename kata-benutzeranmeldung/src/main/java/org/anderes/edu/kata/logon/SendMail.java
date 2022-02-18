package org.anderes.edu.kata.logon;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static javax.mail.Message.*;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    
    private final Path DEFAULT_PROPERTIES = Paths.get("configuration.properties");
    private Path activeProperties = DEFAULT_PROPERTIES;

    public SendMail(Path sendMailProperties) {
        activeProperties = sendMailProperties;
    }

    public boolean sendMessage(String emailTo, String messageText, String subject) {
        try {
            send(getSendEmailProperties(), emailTo, messageText, subject);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private Properties getSendEmailProperties() throws IOException {
        final Properties props = new Properties();
        try (Reader inputStream = new FileReader(activeProperties.toAbsolutePath().toString())) {
            props.load(inputStream );
        }
        return props;
    }

    private void send(final Properties sendEmailProperties, String to, String messageText, String subject) throws MessagingException {
      
        /**
         *  Setzen der Properties für das Versenden der E-Mail
         *  https://javamail.java.net/nonav/docs/api/com/sun/mail/smtp/package-summary.html
         */
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", sendEmailProperties.getProperty("mail.smtp.host", "localhost"));
        properties.setProperty("mail.smtp.port", sendEmailProperties.getProperty("mail.smtp.port", "25"));
        properties.setProperty("mail.transport.protocol", sendEmailProperties.getProperty("mail.transport.protocol", "smtp"));
        if (!sendEmailProperties.getProperty("mail.user", "").isEmpty()) {
            properties.setProperty("mail.user", sendEmailProperties.getProperty("mail.user"));
            properties.setProperty("mail.password", sendEmailProperties.getProperty("mail.password", ""));
        }

        final String from = sendEmailProperties.getProperty("from");
        final Session session = Session.getDefaultInstance(properties);
        final MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(messageText);

        Transport.send(message);
    }
}
