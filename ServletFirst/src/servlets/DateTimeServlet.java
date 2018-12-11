package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class DateTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String docType = "<!DOCTYPE html>";
        String title = "Date and Time Demo";
        Date currentDate = new Date();
        String userAgent = req.getHeader("User-Agent");


        PrintWriter writer = resp.getWriter();

        writer.println(docType + "<html>" +
                "<head>" +
                "<title>" + title + "</title>" +
                "</head>" +
                "<body>" +"<p>" + "Current time\n: " +
                currentDate.toString() +
                "<br/>" + "User-Agent: "+ userAgent +
                "</body>" +
                "</html>");    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
