package com.undertow;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.jsp.JspServletBuilder;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

public class ServletServer {

	public static final String MYAPP = "/myapp";
	private static final Logger logger = Logger.getLogger(ServletServer.class.getName());

	public static void main(final String[] args) {
		try {

			DeploymentInfo servletBuilder = deployment().setClassLoader(ServletServer.class.getClassLoader())
					.setContextPath(MYAPP).setDeploymentName("test.war")
					.setResourceManager(new FileResourceManager(new File("src/main/webapp"), 1024))
					.addServlets(
					          servlet("renjin", Renjin.class)
                              .addMapping("/rservlet"),
					          servlet("rload", LoadProgram.class)
                              .addMapping("/load"),                              
                              servlet("rloadrprograme", LoadSpecificR.class)
                              .addMapping("/loadr/*"),
							  servlet("comments", UserComments.class)
									  .addMapping("/UserComments"),
                              JspServletBuilder.createServlet("Default Jsp Servlet", "*.jsp")

			);

			DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			HttpHandler servletHandler = manager.start();
			PathHandler path = Handlers.path(Handlers.redirect(MYAPP)).addPrefixPath(MYAPP, servletHandler);
			Undertow server = Undertow.builder().addHttpListener(8080, "localhost").setHandler(path).build();
			server.start();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}

		UndertowJaxrsServer ut = new UndertowJaxrsServer();

		TestApplication ta = new TestApplication();

		ut.deploy(ta);

		ut.start(
				Undertow.builder()
						.addHttpListener(8081, "localhost")

		);
		logger.info("JAX-RS based micro-service running!");
	}

}
