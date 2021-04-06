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
        return requestService.getRequestsByUser(currentUser);
    }

    public void acceptRequest(Request request, boolean accepted) {
        if (accepted) {
            transactionService.makePayment(request.getRequestTo().getId(), request.getRequestFrom().getId(), request.getAmount());
        }
        requestService.deleteRequest(request);
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
