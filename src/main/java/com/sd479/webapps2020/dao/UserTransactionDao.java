/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.entity.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Scott
 */
public interface UserTransactionDao extends Dao<UserTransaction> {

    BigDecimal getCurrencyConversion(String currencyFrom, String currencyTo, BigDecimal amount);

    List<UserTransaction> findUserTransactionsByUsername(String username);
}
