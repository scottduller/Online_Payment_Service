/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.UserService;
import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.Transaction;
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
    UserService userService;

    private String userName;

    public UserBean() {
    }

//    TODO:
    public List<Request> getCurrentUserRequests() {
        return userService.getCurrentUser().getRequests();
    }

    public List<Transaction> getCurrentUserTransactions() {
        return userService.getCurrentUser().getTransactions();
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
