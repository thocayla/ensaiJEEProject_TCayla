package com.undertow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.RProgram;
import jpa.EntityManagerHelper;

public class LoadProgram extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<RProgram> progs =  EntityManagerHelper.getEntityManager().createQuery("select e from RProgram as e").getResultList();
		
		
		PrintWriter out = response.getWriter();

		out.println("<HTML>\n<BODY>\n");
		for (RProgram r : progs){
			out.println("<a href=\"loadr/" + r.getId() + "\">"+ r.getAuthorName() + " : "+ r.getName()+ "</a>"+ "<BR>");
		}
		
		out.println("</BODY></HTML>");
		out.close();
	}
}