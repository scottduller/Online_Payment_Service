/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.SystemUserGroup;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
@Stateless
public class AdminService {

    @PersistenceContext
    EntityManager em;

    public AdminService() {
    }

    public void registerAdmin(String email, String firstName, String surname, String username, String password, String currency, double balance) {
        try {
            System.out.println(email);
            System.out.println(firstName);
            System.out.println(surname);
            System.out.println(username);
            System.out.println(password);

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
            systemUserGroup = new SystemUserGroup(username, "admins");

            em.persist(systemUser);
            em.persist(systemUserGroup);

        } catch (Exception e) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
