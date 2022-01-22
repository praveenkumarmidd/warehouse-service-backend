FROM java:8
EXPOSE 8080
ADD target/warehouseservice.jar warehouseservice.jar
ENTRYPOINT ["java","-jar","/warehouseservice.jar"]