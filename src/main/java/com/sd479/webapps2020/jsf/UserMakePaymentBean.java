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
@Named(value = "userMakePaymentBean")
@RequestScoped
public class UserMakePaymentBean {

    @EJB
    UserEJB userService;

    @EJB
    TransactionEJB transactionService;

    private String userNameTo;
    private BigDecimal amount;

    public UserMakePaymentBean() {
    }

    public String getUserNameTo() {
        return userNameTo;
    }

    public void setUserNameTo(String userNameTo) {
        this.userNameTo = userNameTo;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUserService(UserEJB userService) {
        this.userService = userService;
    }

    public void makePayment() {
        SystemUser currentUser = userService.getLoggedInUser();
        SystemUser userTo = userService.getUserByUsername(userNameTo).get(0);

        transactionService.makePayment(currentUser.getId(), userTo.getId(), amount);
    }
}
