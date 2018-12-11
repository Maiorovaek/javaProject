package mail.unity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class User {
    private final String username = "servletapimail@gmail.com";
    private final String password = "servlet123";
    private final String host = "smtp.gmail.com";
    private final String port = "587";

    private Properties properties = new Properties();
    private Session session;
    public User() {
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.host", host);
        this.properties.put("mail.smtp.port", port);
        this.properties.put("mail.smtp.starttls.enable", "true");
    }

    public Session getSession() {
        this.session = Session.getDefaultInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }
    public String getUsername() {
        return username;
    }
}