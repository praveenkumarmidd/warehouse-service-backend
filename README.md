Warehouse Service (Backend)
---------------------------
Warehouse service is a backend application to create, store, and search products with product location(box). 

Prerequisite:
1. Java 8
3. apache Maven 3.3.9

TechStack:
1. Java 8
2. Springboot 2.5.8
3. apache Maven 3.3.9
4. H2 Database
5. Swagger - 2.9.2
6. Junit
7. Docker

Git hub Repository:
------------------
https://github.com/praveenkumarmidd/warehouse-service-backend.git

Steps to run (Git)
1. Build the warehouse Service project using "mvn clean install" in the project root directory
2. Run the application using "mvn spring-boot:run"
3. The warehouse-service is accessible via localhost:8080
4. Login credentials username - Demo and password - Demo123
-------------------------------------------------------------------------------------------------------

Docker Hub Repository:
----------------------
docker image praveenkumarmiddi/warehouse-service-backend:v1

Steps to run (Docker image):
1. To run only warehouse backend service:
   docker pull praveenkumarmiddi/warehouse-service-backend:v1
   docker run -p 8080:8080 praveenkumarmiddi/warehouse-service-backend:v1
   
2. To run Frontend and Backend:
"docker-compose -f docker-compose.yml up" in the project root directory

-------------------------------------------------------------------------------------------------------

Application URL:
---------------
Swagger URL: http://localhost:8080/swagger-ui.html#/
Api Docs: http://localhost:8080/v2/api-docs

Authentication:
Basic Auth
Username: Demo
Password: Demo123

Endpoints:
1. Create Box endpoint to create the box of the product
URL http://localhost:8080/warehouse/v1/box/createBox
    HTTP Method: Post
        Json Request:
               {
               "boxName": "testBox",
               "boxCapacity": 1
               }

2. Add Product endpoint to add product to the given box
URL: http://localhost:8080/warehouse/v1/product/addProduct
   HTTP Method: Put
        Json Request:
               {
               "productName": "testProductName",
               "boxName": "testBox"
               }
   
3. Get Products by product name search
URL: http://localhost:8080/warehouse/v1/product/getProducts?productName=testProductName
   HTTP Method: Get
   
4. Fetch box's with minimum available capacity
URL: http://localhost:8080/warehouse/v1/box/getAvailableBox
   HTTP Method: Get
---------------------------------------------------------------------------------------------------------

Application Monitoring URL:
---------------------------
http://localhost:8080/actuator/
    1.http://localhost:8080/actuator/health
    2.http://localhost:8080/actuator/prometheus
    3.http://localhost:8080/actuator/metrics
