package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean newUser = true;
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                newUser = false;
                login = cookie.getValue();
            }
        }
        if (newUser) {
            login = req.getParameter("login");
            String password = req.getParameter("password");
            File file = new File(getServletContext().getRealPath("users.txt"));
            List<User> usersList = new ArrayList<>();
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader br = new BufferedReader(fileReader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    StringTokenizer strTokenizer = new StringTokenizer(line, " ");
                    usersList.add(new User(strTokenizer.nextToken(), strTokenizer.nextToken()));
                    line = br.readLine();
                }
            }
            int retVal = usersList.indexOf(new User(login, password));

            if ((retVal == -1)) {
                resp.setContentType("text/html");
                resp.sendRedirect("/CookieTask_war_exploded/create.html");
            } else { if (usersList.get(retVal).getPassword().equals(password)) {
                Cookie cookie = new Cookie("login", login);
                cookie.setMaxAge(86400);
                resp.addCookie(cookie);
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println("Welcome " + login  + "The password is correct!  " + "<br/>" +
                        "<a href=\"/CookieTask_war_exploded/\"> return</a>");
          }   else {
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println("<h2>Invalid password</h2>" + "<br/>" +
                        "<a href=\"/CookieTask_war_exploded/\">return</a>");

            }
            }


        } else {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("Welcome " + login + " The password is correct" + "<br/>" +
                    "<a href=\"/CookieTask_war_exploded/\">return</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
