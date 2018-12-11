package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class FirstServlets extends HttpServlet {

String login;
    public static final String loginuser = "password";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        login = req.getParameter("login");
        PrintWriter writer = resp.getWriter();
        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<h2>"+"Hello " + login + "</h2>");
        sb.append("<html>");
        writer.println(sb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            login = req.getParameter("login");
            String password = req.getParameter("password");
        if(password.equals(loginuser)){
            PrintWriter writer = resp.getWriter();
            writer.println("Hello " + login + "!");}
        else{
            resp.sendError(404);
        }
        }

}
