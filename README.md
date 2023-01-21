# Order Management System

## About The Project
This is a web service for an order management system.  
####key features:
1. Users and Products can be created, read, updated and deleted.
Products will have a limited number of quantities.
   
2. A user can place an order with any type of product but the same
product with maximum quantity 4 within the same order.
   
3. A user can give a review with a rating for a product.

4. Users can search products by name, SKU and category.

5. The list of user’s orders can be seen.

6. Only the admin can see all orders and can filter by a particular product
or category.

7. The system should support Authentication and Authorization using the
JWT token. There should be two Roles in the system: User and Admin.
No API should be public other than login API.

## Tools and Technologies used
1. Java 11
2. Spring boot
3. Flyway
4. Docker
5. Github Actions

## Build Locally. Follow the steps one after the other.
1. Download dependency
    ```
    git clone https://github.com/<user_github_account>/order-management-system.git
    ``` 
1. Go to project directory
    ```
    cd /order-management-system
    ``` 
1. Build project with test:
    ```
    mvn clean install
    ```
1. Build project without test:
    ```
    mvn clean install -DskipTest=true
    ```
1. jar will be found under this folder:
    ```
    /target/order-management-system.jar
    ```
1. Running the project with the JAR:
    ```
    java -jar order-management-system.jar --spring.profiles.active=dev
    ```
1. Swagger url:
    ```
    http://localhost:8081/swagger-ui/
    ```
1. Default admin:
    ```
    username: admin
    pass: admin
    ```