package com.sd479.webapps2020.entity;

import com.sd479.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-03-25T21:53:46")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Double> amountSent;
    public static volatile SingularAttribute<Transaction, SystemUser> recievedFrom;
    public static volatile SingularAttribute<Transaction, SystemUser> sentTo;
    public static volatile SingularAttribute<Transaction, Long> id;
    public static volatile SingularAttribute<Transaction, Double> amountRecieved;

}