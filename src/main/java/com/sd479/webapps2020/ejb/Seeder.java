/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import java.math.BigDecimal;
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
public class Seeder {

    @Inject
    AdminEJB adminService;

    @Inject
    UserEJB userService;

    @Inject
    TransactionEJB transactionService;

    @PostConstruct
    public void init() {

        // Initial Admin
        if (userService.getUserByUsername("admin1").isEmpty()) {
            adminService.registerAdmin("admin1@admin1.com", "admin1", "admin1", "admin1", "admin1", "GBP", new BigDecimal(1000));
        }
    }
}
