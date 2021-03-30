/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
// Must be used as a named bean instead of faces validator for EJB injections to work
@Named(value = "usernameValidator")
@RequestScoped
public class UsernameValidator implements Validator {

    @EJB
    UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!userService.getUserByUsername(value.toString()).isEmpty()) {
            throw new ValidatorException(new FacesMessage("Username already exists"));
        }
    }
}
