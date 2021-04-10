/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.RequestDao;
import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userRequestBean")
@RequestScoped
public class UserRequestBean {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    @EJB(name = "requestDao")
    RequestDao requestDao;

    private String userName;
    private BigDecimal amount;

    public UserRequestBean() {
    }

    public void createRequest() {
        SystemUser currentUser = getLoggedInUser();
        SystemUser toUser = systemUserDao.findSystemUserByUsername(userName);

        Request request = new Request(currentUser.getUsername(), toUser.getUsername(), amount);

        requestDao.persist(request);
    }

    public List<Request> getUserRequests() {
        SystemUser currentUser = getLoggedInUser();
        return requestDao.findRequestsByUsernameTo(currentUser.getUsername());
    }

    public void acceptRequest(Request request, boolean accepted) {
        SystemUser fromUser = systemUserDao.findSystemUserByUsername(request.getUsernameFrom());
        SystemUser toUser = systemUserDao.findSystemUserByUsername(request.getUsernameTo());

        if (accepted) {
            if (toUser.getBalance().compareTo(request.getAmount()) >= 0) {
                makePayment(toUser, fromUser, request.getAmount());
                requestDao.remove(request);
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage("Balance is too low"));
            }
        } else {
            requestDao.remove(request);
        }
    }

    public void makePayment(SystemUser from, SystemUser to, BigDecimal amount) {
        BigDecimal convertedAmount = userTransactionDao.getCurrencyConversion(from.getCurrency(), to.getCurrency(), amount);

        BigDecimal fromNewBalance = from.getBalance().subtract(amount);
        BigDecimal toNewBalance = to.getBalance().add(convertedAmount);

        from.setBalance(fromNewBalance);
        to.setBalance(toNewBalance);

        UserTransaction transaction = new UserTransaction(from.getUsername(), to.getUsername(), from.getCurrency(), to.getCurrency(), amount, convertedAmount);

        systemUserDao.update(from);
        systemUserDao.update(to);
        userTransactionDao.persist(transaction);
    }

    public SystemUser getLoggedInUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRemoteUser();

        String currentUserUsername = context.getExternalContext().getRemoteUser();

        SystemUser currentUser = systemUserDao.findSystemUserByUsername(currentUserUsername);

        return currentUser;
    }

}
