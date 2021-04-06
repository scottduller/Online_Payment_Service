/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Scott
 */
@Singleton
@Path("/conversion")
public class RSConversion {

    @GET
    @Path("/{currency1}/{currency2}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getConversionRate(@PathParam("currency1") String currency1, @PathParam("currency2") String currency2) {
        switch (currency1) {
            case "GBP":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'GBP':1,'GBP':1}").build();
                    case "USD":
                        return Response.ok("{'GBP':1,'USD':1.3851659}").build();
                    case "EUR":
                        return Response.ok("{'GBP':1,'EUR':1.1694766}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }
            case "USD":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'USD':1,'GBP':0.72197888}").build();
                    case "USD":
                        return Response.ok("{'USD':1,'USD':1}").build();
                    case "EUR":
                        return Response.ok("{'USD':1,'EUR':0.84431811}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            case "EUR":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'EUR':1,'GBP':0.85511829}").build();
                    case "USD":
                        return Response.ok("{'EUR':1,'USD':1.1843985}").build();
                    case "EUR":
                        return Response.ok("{'EUR':1,'EUR':1}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            default:
                return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // TODO: EITHER USE THIS WITH WHAT IM DOING OR SEE IF JAVA HAS A CURRENCY CONVERSION PACKAGE!!!
    @GET
    @Path("/{currency1}/{currency2}/{amount_of_currency1}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getConversionAmount(@PathParam("currency1") String currency1, @PathParam("currency2") String currency2, @PathParam("amount_of_currency1") String amount) {
        switch (currency1) {
            case "GBP":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'GBP':1,'GBP':1,'amount':}").build();
                    case "USD":
                        return Response.ok("{'GBP':1,'USD':1.3851659}").build();
                    case "EUR":
                        return Response.ok("{'GBP':1,'EUR':1.1694766}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            case "USD":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'USD':1,'GBP':0.72197888}").build();
                    case "USD":
                        return Response.ok("{'USD':1,'USD':1}").build();
                    case "EUR":
                        return Response.ok("{'USD':1,'EUR':0.84431811}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            case "EUR":
                switch (currency2) {
                    case "GBP":
                        return Response.ok("{'EUR':1,'GBP':0.85511829}").build();
                    case "USD":
                        return Response.ok("{'EUR':1,'USD':1.1843985}").build();
                    case "EUR":
                        return Response.ok("{'EUR':1,'EUR':1}").build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            default:
                return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
