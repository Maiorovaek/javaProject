package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServletLogin extends HttpServlet {
    String login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            }
        }

        int retVal = usersList.indexOf(new User(login, password));

        if ((retVal == -1)) {
            usersList.add(new User(login, password));
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                User user = new User(login, password);
                StringBuilder sb = new StringBuilder();
                sb.append(user.getLogin()).append(" ").append(user.getPassword());
                fileWriter.write(sb.toString());
                fileWriter.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("Welcome  " + login + "!" + "<a href=\"/CookieTask_war_exploded/\"> return</a>");
        } else {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<p>Welcome " + login + "</p>" +
                    "<a href=\"/CookieTask_war_exploded/\">return</a>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
