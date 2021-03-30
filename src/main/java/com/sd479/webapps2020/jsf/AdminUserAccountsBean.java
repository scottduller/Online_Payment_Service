/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.UserService;
import com.sd479.webapps2020.entity.SystemUser;
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
    UserService userService;

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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser() {
        this.systemUser = userService.getUserByUsername(userName).get(0);
    }

}
