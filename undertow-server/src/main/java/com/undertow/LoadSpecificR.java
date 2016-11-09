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

public class LoadSpecificR extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RProgram prog =  EntityManagerHelper.getEntityManager().
				find(RProgram.class, Long.parseLong(request.getPathInfo().replaceAll("/", "")));
		
		System.err.println(request.getPathInfo());
		
		
		PrintWriter out = response.getWriter();


		out.println("<HTML>" +
				"<head>" +
						"<script src=\"../bower_components/codemirror/lib/codemirror.js\"></script>" +
						"<link rel=\"stylesheet\" href=\"../bower_components/codemirror/lib/codemirror.css\">" +
						"<script src=\"../bower_components/codemirror/mode/r/r.js\"></script>" +
						"</head>"+
				"<BODY>");
		out.println("<FORM Method=\"POST\" Action=\"/myapp/rservlet\">"+
				"Author Name: <INPUT type=text size=30 name=\"authorname\"  value=\""+ prog.getAuthorName() +" \"><BR>"+
				"Email Address: <INPUT type=text size=30 name=\"email\"  value=\""+ prog.getEmail() +" \"><BR>"+
				"R Program Name : <INPUT type=text size=30 name=\"name\"  value=\""+ prog.getName()  +" \"><BR>"+
				"R Program : <textarea id=\"myTextArea\" name=\"program\" cols=\"40\" rows=\"60\">" + prog.getProgram() +"</textarea>"+
				"<BR>"+
				"<script type=\"text/javascript\">" +
				"window.onload = function(){" +
				"var myTextArea = document.getElementById(\"myTextArea\");" +
				"editor = CodeMirror.fromTextArea(myTextArea, {" +
				"lineNumbers: true," +
				"mode: \"r\"" +
				"});" +
				"};" +
				"</script>"+
				"<BR>"+
				"<INPUT type=\"submit\""+
				"value=\"Run\"></input>"+
				"</FORM>");
		out.println("</BODY></HTML>");
		out.close();
	}
}