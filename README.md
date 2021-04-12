# Web Applications Project 2020/21
A simple payments app using JavaEE, EJB, JSF and JPA impementing the DAO and DTO design patterns.

Within the project contains:
- The main project - the main payment service
- RESTful Web Service - A currency conversion service called using HTTP
- Timestamping Thrift Server - A seperate thrift server deployed with the main server to timestamp transactions 

## Summary
The application is secured using JDBCRealms so users cannot access admin pages.

The /faces/ portion of the URL has been removed for convenience.

An initial admin is created with Username: admin1, Password: admin1.

All source files are within the package java/com/sd479/webapps2020. This folder contains the packages:

### REST service 
URL: baseURL/conversion/{currency1}/{currency2}/{amount} for conversion

URL: baseURL/conversion/{currency1}/{currency2} for conversion rate

When you call one of the urls amove you will be returned with a JSON object containing the exchange rate and the converted value.
Currencies included are GBP, EUR, USD.

### JPA entity classes 
In the entities folder contains all of the JPA entities for the users, user gorups, transactions and requests.
To implement DTO there are no reference to other entites within each enitity only a username which can be looked up for individual requests.

### DAO patterns with EJBs
The EJBs are arranged using a DAO interface pattern where all commonly used entity requests are pushed up into higher interfaces that get implemented in a generic JpaDao file.

### JSF CDI beans
CDI beans are used to implement business logic of the xhtml pages and send requests to the server by injecting the backend EJBs.

### Thrift Server
The thrift service code was auto generated using the apache thift compiler. The handler implements the timestamping logic for transactions while the the timestamping server allows multiple clients to connect at once using threads.
