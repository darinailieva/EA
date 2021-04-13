# _EA_

EA is a RESTful API.
Some actions that can be done are:
- Retrieve exchange rate between two currencies from www.currencylayer.com;
- Calculate target amount;
- Retrieve all transactions;
- Retrieve all transactions on different pages;
- Filter transactions by currency;
- Sort transactions by date of creation and currency;
- Filter transactions by date on different pages.
 
In order to set up the application,
- First run the dependencies and build.gradle;
- Then run the database script and dump data;
- Finally, start EaApplication.

Technical details of the application:
- Database MariaDB.
- JDK version 1.8
- SpringBoot framework
- JPA repository in the persistence (repository) layer
- JUnit 

This is the API Documentation: http://localhost:8080/swagger-ui.html