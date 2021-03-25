/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.SystemUserGroup;
import java.security.MessageDigest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Scott
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public UserService() {
    }

    public List<SystemUser> getUserList() {
        List<SystemUser> systemUsers = em.createNamedQuery("findAllUsers").getResultList();
        return systemUsers;
    }

    //TODO:
    public SystemUser getCurrentUser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getUserPrincipal().getName();

        SystemUser currentUser = em.createNamedQuery("findCurrentUser", SystemUser.class).setParameter("username", username).getSingleResult();

        return currentUser;
    }

    public void registerUser(String email, String firstName, String surname, String username, String password, String currency, double balance) {
        try {
            SystemUser systemUser;
            SystemUserGroup systemUserGroup;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String pw = password;
            md.update(pw.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String passwordDB = sb.toString();

            systemUser = new SystemUser(email, firstName, surname, username, passwordDB, currency, balance);
            systemUserGroup = new SystemUserGroup(username, "users");

            em.persist(systemUser);
            em.persist(systemUserGroup);

        } catch (Exception e) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
