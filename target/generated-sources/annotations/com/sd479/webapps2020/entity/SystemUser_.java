package com.sd479.webapps2020.entity;

import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.UserTransaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-03-30T19:22:00")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, String> firstName;
    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, Double> balance;
    public static volatile SingularAttribute<SystemUser, String> surname;
    public static volatile ListAttribute<SystemUser, UserTransaction> userTransactions;
    public static volatile SingularAttribute<SystemUser, String> currency;
    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile ListAttribute<SystemUser, Request> requests;
    public static volatile SingularAttribute<SystemUser, String> email;
    public static volatile SingularAttribute<SystemUser, String> username;

}