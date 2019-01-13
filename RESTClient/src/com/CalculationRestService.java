package com;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calc")
public class CalculationRestService {
    @GET
    @Path("/add/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String add(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a + b;
        return "<html>" +
                "<title>" + "sum of two numbers\n" + "</title>" +
                "<body><h1> Sum of two numbers:   " + a + "+" + b + "=" + result + "</h1></body>" +
                "</html>";
    }

    @POST
    @Path("/add/{a}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPost(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a + b;
        return Response.status(200).entity(result).build();
    }


    @GET
    @Path("/substraction/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String substract(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a - b;
        return "<html>" +
                "<title>" + "Subtraction of two numbers\n" + "</title>" +
                "<body><h1> Subtraction of two numbers:   " + a + "-" + b + "=" + result + "</h1></body>" +
                "</html>";
    }


    @POST
    @Path("/substraction/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public Response subPost(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a - b;
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/mult/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String multiplication(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a * b;
        return "<html>" +
                "<title>" + "Multiplication of two numbers\n" + "</title>" +
                "<body><h1> Multiplication of two numbers:   " + a + "*" + b + "=" + result + "</h1></body>" +
                "</html>";
    }


    @POST
    @Path("/mult/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public Response multPost(@PathParam("a") int a, @PathParam("b") int b) {
        int result = a * b;
        return Response.status(200).entity(result).build();
    }


    @GET
    @Path("/div/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String div(@PathParam("a") double a, @PathParam("b") double b) {
        double result = 0;
        if (b != 0) {
            result = a / b;
        }


        return "<html>" +
                "<title>" + "division of two numbers\n" + "</title>" +
                "<body><h1> division of two numbers:   " + a + "/" + b + "=" + result + "</h1></body>" +
                "</html>";
    }


    @POST
    @Path("/div/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public Response divPost(@PathParam("a") double a, @PathParam("b") double b) {
        double result = 0;
        if (b != 0) {
            result = a / b;
        }
        return Response.status(200).entity(result).build();
    }
}
