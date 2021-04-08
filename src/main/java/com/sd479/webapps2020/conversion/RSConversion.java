/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.conversion;

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
@Path("/")
public class RSConversion {

    @GET
    @Path("/{currency1}/{currency2}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getConversionRate(@PathParam("currency1") String currency1, @PathParam("currency2") String currency2) {
        double GBP_init = 1.0;
        double USD_init = 1.0;
        double EUR_init = 1.0;

        double GBP_conv = 1.0;
        double USD_conv = 1.0;
        double EUR_conv = 1.0;

        String result = "{\"%s\":%f,\"%s\":%f}";

        switch (currency1) {
            case "GBP":
                USD_conv = 1.3851659;
                EUR_conv = 1.1694766;
                switch (currency2) {
                    case "GBP":
                        return Response.ok(String.format(result, "GBP", GBP_init, "GBP", GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "GBP", GBP_init, "USD", USD_conv)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "GBP", GBP_init, "EUR", EUR_conv)).build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }
            case "USD":
                GBP_conv = 0.72197888;
                EUR_conv = 0.84431811;
                switch (currency2) {
                    case "GBP":
                        return Response.ok(String.format(result, "USD", USD_init, "GBP", GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "USD", USD_init, "USD", USD_init)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "USD", USD_init, "EUR", EUR_conv)).build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            case "EUR":
                GBP_conv = 0.85511829;
                USD_conv = 1.1843985;
                switch (currency2) {
                    case "GBP":
                        return Response.ok(String.format(result, "EUR", EUR_init, "GBP", GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "EUR", EUR_init, "USD", USD_conv)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "EUR", EUR_init, "EUR", EUR_conv)).build();
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
        double GBP_init = 1.0;
        double USD_init = 1.0;
        double EUR_init = 1.0;

        double GBP_conv = 1.0;
        double USD_conv = 1.0;
        double EUR_conv = 1.0;

        String result = "{\"%s\":%f,\"%s\":%f,\"original\":" + amount + ",\"conversion\":%f}";

        switch (currency1) {
            case "GBP":
                USD_conv = 1.3851659;
                EUR_conv = 1.1694766;
                switch (currency2) {
                    case "GBP":

                        return Response.ok(String.format(result, "GBP", GBP_init, "GBP", GBP_conv, Float.parseFloat(amount) * GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "GBP", GBP_init, "USD", USD_conv, Float.parseFloat(amount) * USD_conv)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "GBP", GBP_init, "EUR", EUR_conv, Float.parseFloat(amount) * EUR_conv)).build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }
            case "USD":
                GBP_conv = 0.72197888;
                EUR_conv = 0.84431811;
                switch (currency2) {
                    case "GBP":
                        return Response.ok(String.format(result, "USD", USD_init, "GBP", GBP_conv, Float.parseFloat(amount) * GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "USD", USD_init, "USD", USD_init, Float.parseFloat(amount) * USD_conv)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "USD", USD_init, "EUR", EUR_conv, Float.parseFloat(amount) * EUR_conv)).build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            case "EUR":
                GBP_conv = 0.85511829;
                USD_conv = 1.1843985;
                switch (currency2) {
                    case "GBP":
                        return Response.ok(String.format(result, "EUR", EUR_init, "GBP", GBP_conv, Float.parseFloat(amount) * GBP_conv)).build();
                    case "USD":
                        return Response.ok(String.format(result, "EUR", EUR_init, "USD", USD_conv, Float.parseFloat(amount) * USD_conv)).build();
                    case "EUR":
                        return Response.ok(String.format(result, "EUR", EUR_init, "EUR", EUR_conv, Float.parseFloat(amount) * EUR_conv)).build();
                    default:
                        return Response.status(Response.Status.NOT_FOUND).build();
                }

            default:
                return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
