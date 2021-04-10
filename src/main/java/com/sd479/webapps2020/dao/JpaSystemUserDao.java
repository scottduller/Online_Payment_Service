/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.entity.SystemUser;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott
 */
public class JpaSystemUserDao extends JpaDao<SystemUser> implements SystemUserDao {

    @Override
    public List findAllSystemUsers() {
        return (List) em.createNamedQuery("findAllSystemUsers").getResultList();
    }

    @Override
    public SystemUser findSystemUserByUsername(String username) {
        return (SystemUser) em.find(entityClass, username);
    }

    @Override
    public void registerSystemUser(SystemUser systemUser) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String pw = systemUser.getPassword();
            md.update(pw.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String passwordDB = sb.toString();
            systemUser.setPassword(passwordDB);
            em.persist(systemUser);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.getLogger(JpaSystemUserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(SystemUser systemUser) {
        em.merge(systemUser);
    }

}
