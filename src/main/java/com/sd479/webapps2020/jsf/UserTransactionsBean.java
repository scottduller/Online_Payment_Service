/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.TransactionEJB;
import com.sd479.webapps2020.ejb.UserEJB;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userTransactionsBean")
@RequestScoped
public class UserTransactionsBean {

    @EJB
    UserEJB userService;

    @EJB
    TransactionEJB transactionService;

    public UserTransactionsBean() {
    }

    public List<UserTransaction> getTransactions() {
        SystemUser currentUser = userService.getLoggedInUser();
        List<UserTransaction> transactions = transactionService.getTransactionsByUser(currentUser);

        return transactions;
    }

    public UserEJB getUserService() {
        return userService;
    }

    public void setUserService(UserEJB userService) {
        this.userService = userService;
    }

    public TransactionEJB getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionEJB transactionService) {
        this.transactionService = transactionService;
    }

}
