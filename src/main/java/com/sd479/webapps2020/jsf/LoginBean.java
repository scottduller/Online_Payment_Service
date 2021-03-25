/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Scott
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    public LoginBean() {

    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
            facesContext.addMessage(null, new FacesMessage("User is logged out"));
            return "index";
        } catch (ServletException e) {
            facesContext.addMessage(null, new FacesMessage("Logout failed."));
        }
        return null;
    }

}
