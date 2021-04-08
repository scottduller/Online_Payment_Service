/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.conversion.RSConversionClient;
import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.SystemUser;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RequestEJB {

    @PersistenceContext
    EntityManager em;

    public List<Request> getRequestsByUser(SystemUser user) {
        List<Request> request = em.createNamedQuery("getRequestsByUser").setParameter("user", user).getResultList();
        return request;
    }

    public Request getRequestById(long id) {
        Request request = em.find(Request.class, id);
        return request;
    }

    public void postRequest(long fromId, long toId, BigDecimal amount) {
        SystemUser from = em.find(SystemUser.class, fromId);
        SystemUser to = em.find(SystemUser.class, toId);

        BigDecimal amountConverted = getCurrencyConversion(from, to, amount);

        Request request = new Request(from, to, amountConverted);

        em.persist(request);
    }

    public void deleteRequest(Request request) {
        if (!em.contains(request)) {
            request = em.merge(request);
        }

        em.remove(request);
    }

    public void putRequest(Request request) {
        em.merge(request);
    }

    public static BigDecimal getCurrencyConversion(SystemUser from, SystemUser to, BigDecimal amount) {
        // Connecting to REST service
        RSConversionClient client = new RSConversionClient();

        // Get currency conversion as a String
        String conversion = client.getConversionAmount(from.getCurrency(), to.getCurrency(), amount.toString());

        // Convert String to JsonObject
        JsonObject conversionJson = stringToJson(conversion);

        // Get converted currency as BigDecimal
        BigDecimal amountConverted = new BigDecimal(conversionJson.getJsonNumber("conversion").doubleValue(), new MathContext(13, RoundingMode.CEILING));

        amountConverted = amountConverted.setScale(2, RoundingMode.HALF_UP);

        client.close();

        return amountConverted;
    }

    public static JsonObject stringToJson(String str) {
        JsonReader jsonReader = Json.createReader(new StringReader(str));
        JsonObject json = jsonReader.readObject();

        jsonReader.close();

        return json;
    }
}
