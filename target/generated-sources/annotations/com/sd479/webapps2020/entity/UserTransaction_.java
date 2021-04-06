package com.sd479.webapps2020.entity;

import com.sd479.webapps2020.entity.SystemUser;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-06T16:04:14")
@StaticMetamodel(UserTransaction.class)
public class UserTransaction_ { 

    public static volatile SingularAttribute<UserTransaction, SystemUser> payementFrom;
    public static volatile SingularAttribute<UserTransaction, BigDecimal> amount;
    public static volatile SingularAttribute<UserTransaction, SystemUser> paymentTo;
    public static volatile SingularAttribute<UserTransaction, Long> id;

}