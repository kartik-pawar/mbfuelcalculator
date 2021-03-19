# mbfuelcalculator
An API service to calculate fuel intake in cars based on events provided
## Manual Setup
### Steps
1. Clone this repository
2. Install Java, Maven, Redis, Mysql and Apache Kafka on system.
4. Start Redis and MySQL as deamon service.
4. Execute the following queries in MySQL to add user authentication table:
    * create database fuelcostcalculator;
    * CREATE TABLE Users (user_name VARCHAR(255), password VARCHAR(255), roles VARCHAR(255), active boolean default true, PRIMARY KEY (username));
    * INSERT INTO Users values("admin","password", "USER_ROLE", true);
5. Create car_event topic in Kafka and start Zookeeper and Kafka-server:
    * bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic car_event --create --partitions 1 --replication-factor 1
    * bin/zookeeper-server-start.sh config/zookeeper.properties
    * bin/kafka-server-start.sh config/server.properties
6. Go to the project location and resolve the maven dependencies using:
    * mvn clean install
7. A jar should be generated in target folder, execute it using:
    * java -jar fuelcostcalculator
   This should start the spring boot application. You can now start submitting requests.
   
### Demo Request
1. Use any rest client like Postman and create a new POST request.
2. Choose Basic Auth as authentication mechanism and use the added username and password.
3. In the body section choose request type as JSON.
4. To submit new fueling event use the following JSON in body:
```
{
    "fueling":true,
    "city":"Pune",
    "date": "2012-04-23T18:55:43.511Z"
}
```
5. To close the fueling event use the following JSON in body with updated time:
```
{
    "fueling":false,
    "city":"Pune",
    "date": "2012-04-23T18:58:43.511Z"
}
```
6. On submission of close event, a log will be generated in the backend stating cost of fuel filled.

#### Other Features
This application has swagger and swagger-ui integrated. To access the documentation visit the following URL: http://localhost:8080/swagger-ui.html

    
