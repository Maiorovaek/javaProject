package mail.unity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class EmailTool {

    private String toEmail;
    private String subject;
    private String cc;
    private String text;
    private boolean validEmail = false;
    public EmailTool(HttpServletRequest req) {
        if(isEmail(req.getParameter("email")) & (isEmail(req.getParameter("cc")) | "".equals(req.getParameter("cc")))) {
            this.toEmail = req.getParameter("email");
            this.cc = req.getParameter("cc");
            this.validEmail = true;
        }
        this.subject = req.getParameter("subject");
        this.text = req.getParameter("text");
    }



    public String getTo() {
        return toEmail;
    }
    public String getSubject() {
        return subject;
    }
    public String getCc() {
        return cc;
    }
    public String getText() {
        return text;
    }



    private boolean isEmail(String str) {
        if(!str.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return false;
        }
        return true;
    }



    public boolean isValidEmail() {
        return validEmail;
    }
    public boolean isCc() {
        if("".equals(cc)) {
            return false;
        }
        return true;
    }
}