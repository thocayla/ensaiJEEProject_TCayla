package com.undertow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.persistence.EntityTransaction;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.RProgram;
import jpa.EntityManagerHelper;

public class Renjin extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// Redirect standard output
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		ScriptEngineManager manager = new ScriptEngineManager();
		// create a Renjin engine:
		ScriptEngine engine = manager.getEngineByName("Renjin");
		// check if the engine has loaded correctly:
		if (engine == null) {
			throw new RuntimeException("Renjin Script Engine not found on the classpath.");
		}
		engine.getContext().setWriter(new PrintWriter(buffer));
		;

		try {
			engine.eval(request.getParameter("program"));

			EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
			t.begin();
			RProgram p = new RProgram();
			p.setAuthor("me");
			p.setAuthorName(request.getParameter("authorname"));
			p.setEmail(request.getParameter("email"));
			p.setName(request.getParameter("name"));
			p.setProgram(request.getParameter("program"));
			p.setResult(buffer.toString());
			EntityManagerHelper.getEntityManager().persist(p);
			t.commit();

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		out.println("<HTML>\n<BODY>\n" + buffer.toString().replaceAll("\n", "<BR>\n") +
				"<button onclick=\"window.location.href='/myapp/index.html'\">Back</button>"+

				"</BODY></HTML>");
	}
}