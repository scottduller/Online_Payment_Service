/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import java.math.BigDecimal;

/**
 *
 * @author Scott
 */
public interface UserTransactionDao extends Dao {

    BigDecimal getCurrencyConversion(String currencyFrom, String currencyTo, BigDecimal amount);
}
