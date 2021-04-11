package com.sd479.webapps2020.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-11T18:49:20")
@StaticMetamodel(Request.class)
public class Request_ { 

    public static volatile SingularAttribute<Request, BigDecimal> amount;
    public static volatile SingularAttribute<Request, Long> id;
    public static volatile SingularAttribute<Request, String> usernameTo;
    public static volatile SingularAttribute<Request, String> usernameFrom;

}