# exchange-application

Exchange Application is a RESTful API.
Some actions that can be done are:
- Retrieve exchange rate between two currencies from www.currencylayer.com
- Calculate target amount
- Retrieve all transactions
- Filter transactions by currency
- Sort transactions by date of creation and currency. 

In order to set up the application,
- First run the dependencies and build.gradle;
- Then run the database script and dump data;
- Finally, start EaApplication.

Technical details of the application:
- Database MySQL/MariaDB.
- JDK version 1.8
- SpringBoot framework
- Hibernate/JPA repository in the persistence (repository) layer
- JUnit 

This is the API Documentation: http://localhost:8080/swagger-ui.html