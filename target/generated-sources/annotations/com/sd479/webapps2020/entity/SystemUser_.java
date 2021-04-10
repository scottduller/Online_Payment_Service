package com.sd479.webapps2020.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-10T16:26:30")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, String> firstName;
    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, BigDecimal> balance;
    public static volatile SingularAttribute<SystemUser, String> surname;
    public static volatile SingularAttribute<SystemUser, String> currency;
    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile SingularAttribute<SystemUser, Long> version;
    public static volatile SingularAttribute<SystemUser, String> email;
    public static volatile SingularAttribute<SystemUser, String> username;

}