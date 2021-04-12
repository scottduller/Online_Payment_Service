/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.conversion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class RSConversionClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:10000/webapps2020/";

    public RSConversionClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("conversion");
    }

    public String getConversionAmount(String currency1, String currency2, String amount_of_currency1) throws ClientErrorException {
        WebTarget resource = webTarget;

        resource = resource.path(java.text.MessageFormat.format("{0}/{1}/{2}", new Object[]{currency1, currency2, amount_of_currency1}));

        String get = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);

        return get;
    }

    public String getConversionRate(String currency1, String currency2) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{currency1, currency2}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }

}
