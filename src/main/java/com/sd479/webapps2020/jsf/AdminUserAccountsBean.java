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
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "adminUserAccountsBean")
@RequestScoped
public class AdminUserAccountsBean {

    @EJB
    UserEJB userService;

    @EJB
    TransactionEJB transactionService;

    private String userName;
    private SystemUser systemUser;

    public AdminUserAccountsBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SystemUser> getUserList() {
        return userService.getUserList();
    }

    public UserEJB getUserService() {
        return userService;
    }

    public void setUserService(UserEJB userService) {
        this.userService = userService;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser() {
        this.systemUser = userService.getUserByUsername(userName).get(0);
    }

    public TransactionEJB getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionEJB transactionService) {
        this.transactionService = transactionService;
    }

    public BigDecimal getUsersBalance() {
        if (systemUser != null) {
            return transactionService.getBalance(systemUser.getId());
        }
        return null;
    }

    public List<UserTransaction> getUserTransactions() {
        if (systemUser != null) {
            return transactionService.getTransactionsByUser(systemUser);
        }
        return null;
    }

}
