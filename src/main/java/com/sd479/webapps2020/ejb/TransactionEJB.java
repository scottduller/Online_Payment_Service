/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.conversion.RSConversionClient;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
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
@TransactionAttribute(REQUIRED)
public class TransactionEJB {

    @PersistenceContext
    EntityManager em;

    public BigDecimal getBalance(long id) {
        SystemUser user = em.find(SystemUser.class, id);
        return user.getBalance();
    }

    public void makePayment(long fromId, long toId, BigDecimal amount) {
        SystemUser from = em.find(SystemUser.class, fromId);
        SystemUser to = em.find(SystemUser.class, toId);

        from.setBalance(from.getBalance().subtract(amount));

        BigDecimal convertedAmount = getCurrencyConversionByUsers(from, to, amount);

        to.setBalance(to.getBalance().add(convertedAmount));

        UserTransaction transaction = new UserTransaction(from.getUsername(), to.getUsername(), from.getCurrency(), to.getCurrency(), amount, convertedAmount);

        em.persist(from);
        em.persist(to);
        em.persist(transaction);
    }

    public List<UserTransaction> getTransactionsByUser(SystemUser user) {
        List<UserTransaction> transactions = em.createNamedQuery("findTransactionsByUser").setParameter("username", user.getUsername()).getResultList();
        return transactions;
    }

    public static BigDecimal getCurrencyConversionByUsers(SystemUser from, SystemUser to, BigDecimal amount) {

        return getCurrencyConversion(from.getCurrency(), to.getCurrency(), amount);
    }

    public static BigDecimal getCurrencyConversion(String currencyFrom, String currencyTo, BigDecimal amount) {
        // Connecting to REST service
        RSConversionClient client = new RSConversionClient();

        // Get currency conversion as a String
        String conversion = client.getConversionAmount(currencyFrom, currencyTo, amount.toString());

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
