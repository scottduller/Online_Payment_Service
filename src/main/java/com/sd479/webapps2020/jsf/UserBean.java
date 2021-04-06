/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.TransactionEJB;
import com.sd479.webapps2020.ejb.UserEJB;
import com.sd479.webapps2020.entity.SystemUser;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    @EJB
    UserEJB userService;

    @EJB
    TransactionEJB transactionService;

    private String userName;

    public UserBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SystemUser> getUserList() {
        SystemUser currentUser = userService.getLoggedInUser();

        List<SystemUser> users = userService.getUserList();
        users.remove(currentUser);

        return users;
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

    public SystemUser getLoggedInUser() {
        return userService.getLoggedInUser();
    }

    public BigDecimal getCurrentUserBalance() {
        return transactionService.getBalance(getLoggedInUser().getId());
    }

}
