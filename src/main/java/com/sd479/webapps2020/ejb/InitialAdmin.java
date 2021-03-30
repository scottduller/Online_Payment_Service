/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Scott
 */
@Startup
@Singleton
public class InitialAdmin {

    @Inject
    AdminService adminService;

    @Inject
    UserService userService;

    @PostConstruct
    public void init() {
        if (userService.getUserByUsername("admin1").isEmpty()) {
            adminService.registerAdmin("admin1@admin.com", "admin", "admin", "admin1", "admin1", "GBP", 1000.0);
        }
    }
}
