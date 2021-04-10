/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.RequestEJB;
import com.sd479.webapps2020.ejb.TransactionEJB;
import com.sd479.webapps2020.ejb.UserEJB;
import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.SystemUser;
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

    @EJB
    UserEJB userService;

    @EJB
    RequestEJB requestService;

    @EJB
    TransactionEJB transactionService;

    private String userName;
    private BigDecimal amount;

    public UserRequestBean() {
    }

    public void createRequest() {
        SystemUser currentUser = userService.getLoggedInUser();
        SystemUser toUser = userService.getUserByUsername(userName).get(0);

        requestService.postRequest(currentUser.getId(), toUser.getId(), amount);
    }

    public List<Request> getUserRequests() {
        SystemUser currentUser = userService.getLoggedInUser();
        return requestService.getRequestsByUsername(currentUser.getUsername());
    }

    public void acceptRequest(Request request, boolean accepted) {
        SystemUser toUser = userService.getUserByUsername(request.getUsernameTo()).get(0);
        SystemUser fromUser = userService.getUserByUsername(request.getUsernameFrom()).get(0);

        if (accepted) {
            if (toUser.getBalance().compareTo(request.getAmount()) >= 0) {
                transactionService.makePayment(toUser.getId(), fromUser.getId(), request.getAmount());
                requestService.deleteRequest(request);
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage("Balance is too low"));
            }
        } else {
            requestService.deleteRequest(request);
        }
    }

    public UserEJB getUserService() {
        return userService;
    }

    public void setUserService(UserEJB userService) {
        this.userService = userService;
    }

    public RequestEJB getRequestService() {
        return requestService;
    }

    public void setRequestService(RequestEJB requestService) {
        this.requestService = requestService;
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

}
