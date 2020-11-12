package ru.sbt.course.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static registration.DataBase.doesPasswordRight;
import static registration.DataBase.doesUserExist;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter write = resp.getWriter();

        ServletContext servletContext = getServletConfig().getServletContext();

        String user = req.getParameter("userName");
        String pass = req.getParameter("userPass");

        if (doesUserExist(user)) {
            if (doesPasswordRight(user, pass)) {
                write.println("<h1>Ok</h1>");
                RequestDispatcher rd = servletContext.getRequestDispatcher("/index.jsp");
                rd.include(req, resp);
            } else {
                write.println("<h1>Password is incorrect</h1>");
                RequestDispatcher rd = servletContext.getRequestDispatcher("/LoginForm.html");
                rd.include(req, resp);
            }
        } else {
            write.println("<h1>User " + user + " doesn't exist. Please, sign up to proceed</h1>");
            RequestDispatcher rd = servletContext.getRequestDispatcher("/LoginForm.html");
            rd.include(req, resp);
        }
    }
}