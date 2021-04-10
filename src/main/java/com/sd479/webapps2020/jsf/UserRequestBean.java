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
import java.util.Objects;
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

        FacesContext facesContext = FacesContext.getCurrentInstance();

        BigDecimal convertedAmount = userTransactionDao.getCurrencyConversion(currentUser.getCurrency(), toUser.getCurrency(), amount);

        Request request = new Request(currentUser.getUsername(), toUser.getUsername(), convertedAmount);

        requestDao.persist(request);
        facesContext.addMessage(null, new FacesMessage("Request Sent"));

    }

    public List<Request> getUserRequests() {
        SystemUser currentUser = getLoggedInUser();
        return requestDao.findRequestsByUsernameTo(currentUser.getUsername());
    }

    public void acceptRequest(Request request, boolean accepted) {
        SystemUser fromUser = systemUserDao.findSystemUserByUsername(request.getUsernameFrom());
        SystemUser toUser = systemUserDao.findSystemUserByUsername(request.getUsernameTo());

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (accepted) {
            if (toUser.getBalance().compareTo(request.getAmount()) >= 0) {
                makePayment(toUser, fromUser, request.getAmount());

                requestDao.remove(request);
                facesContext.addMessage(null, new FacesMessage("Payment Made"));

            } else {
                facesContext.addMessage(null, new FacesMessage("Balance is too low"));
            }
        } else {
            requestDao.remove(request);
            facesContext.addMessage(null, new FacesMessage("Request Declined"));
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

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    public UserTransactionDao getUserTransactionDao() {
        return userTransactionDao;
    }

    public void setUserTransactionDao(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.systemUserDao);
        hash = 67 * hash + Objects.hashCode(this.userTransactionDao);
        hash = 67 * hash + Objects.hashCode(this.requestDao);
        hash = 67 * hash + Objects.hashCode(this.userName);
        hash = 67 * hash + Objects.hashCode(this.amount);
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
        final UserRequestBean other = (UserRequestBean) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        if (!Objects.equals(this.requestDao, other.requestDao)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

}
