package com.sd479.webapps2020.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-10T21:43:57")
@StaticMetamodel(UserTransaction.class)
public class UserTransaction_ { 

    public static volatile SingularAttribute<UserTransaction, BigDecimal> amountTo;
    public static volatile SingularAttribute<UserTransaction, BigDecimal> amountFrom;
    public static volatile SingularAttribute<UserTransaction, Long> id;
    public static volatile SingularAttribute<UserTransaction, String> usernameTo;
    public static volatile SingularAttribute<UserTransaction, String> currencyTo;
    public static volatile SingularAttribute<UserTransaction, String> usernameFrom;
    public static volatile SingularAttribute<UserTransaction, String> currencyFrom;

}