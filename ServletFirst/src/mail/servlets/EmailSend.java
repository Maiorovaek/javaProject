package mail.servlets;

import mail.unity.EmailTool;
import mail.unity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class EmailSend extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        EmailTool parametrs = new EmailTool(req);

        User user = new User();

        PrintWriter out = resp.getWriter();

        if (parametrs.isValidEmail()) {



            try {
                MimeMessage message = new MimeMessage(user.getSession());
                message.setFrom(new InternetAddress(user.getUsername()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametrs.getTo()));
                if (parametrs.isCc()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(parametrs.getCc()));
                }
                message.setSubject(parametrs.getSubject());
                message.setText(parametrs.getText());
                Transport.send(message);
                out.print("<h1>Email successfully sent</h1>");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {

            out.print("<h2>Error! Repeat input!</h2>");

        }

    }
}