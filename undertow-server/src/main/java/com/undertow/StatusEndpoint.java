package com.undertow;

/**
 * Created by thomas on 09/11/16.
 */
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.Person;

@Path("/status")
public class StatusEndpoint {

    private static final Logger logger = Logger.getLogger(StatusEndpoint.class.getName());

    @GET
    public Response getStatus() {

        return Response.status(Response.Status.OK).entity("JO").build();
    }

    @GET
    @Path("/test")
    public String helloWorld() {

        return "hello";
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        Person p = new Person();
        p.setName("test");
        p.setFirstName("t");
        return p;
    }

    @POST
    @Path("/person")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPerson(Person p) {
        System.err.println(p.getName());
    }


}