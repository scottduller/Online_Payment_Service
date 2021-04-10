/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.SystemUserDao;
import java.util.Objects;
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

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (systemUserDao.findSystemUserByUsername(value.toString()) != null) {
            throw new ValidatorException(new FacesMessage("Username already exists"));
        }
    }

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.systemUserDao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsernameValidator other = (UsernameValidator) obj;
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        return true;
    }

}
