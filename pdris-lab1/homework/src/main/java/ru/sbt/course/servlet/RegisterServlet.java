package ru.sbt.course.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static registration.DataBase.addUser;
import static registration.DataBase.doesUserExist;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/RegisterForm.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write = resp.getWriter();

        ServletContext servletContext = getServletConfig().getServletContext();

        String user = req.getParameter("userName");
        String pass = req.getParameter("userPass");
        if (user.isEmpty() || pass.isEmpty()) {
            write.println("<h1>Please, write login and password.</h1>");
            RequestDispatcher rd = servletContext.getRequestDispatcher("/RegisterForm.html");
            rd.include(req, resp);
        } else {
            if ("admin".equals(user) || doesUserExist(user)) {
                write.println("<h1>Please, change your login.</h1>");
                RequestDispatcher rd = servletContext.getRequestDispatcher("/RegisterForm.html");
                rd.include(req, resp);
            } else {
                addUser(user, pass);
                write.println("<h1>You have successfully registered.</h1>");
                RequestDispatcher rd = servletContext.getRequestDispatcher("/LoginForm.html");
                rd.include(req, resp);
            }
        }
    }
}
