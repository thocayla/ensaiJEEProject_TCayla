package com.undertow;

/**
 * Created by thomas on 08/11/16.
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserComments extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();


        out.println("<HTML>\n<BODY>\n" +
                "<H1>Commentary</H1>\n" +
                "<UL>\n" +
                " <LI>Your comments: "
                + request.getParameter("comments") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}