# Order Management System

## Pre-requisite
1. Java 11

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