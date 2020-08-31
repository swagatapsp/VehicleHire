# Spring Boot Car Hire Project
REST APIs implemented using Spring Boot

## Prerequisite 

* Java Runtime Environment must be installed on your machine

## How to Run

* Build the project by running `./gradlew clean build` inside module
* Once successfully built, run the service by using the following command:
```
java -jar  VehicleHire/VehicleHire-0.0.1-SNAPSHOT.jar
```

## REST APIs Endpoints

### List available vehicles for hire – all vehicles that can be hired today.
```
curl -X GET \
  'http://localhost:8443/vehicles/hire?date=2020-08-31' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 67d6729a-de6b-4bce-834c-f33436dcccda' \
  -d '{"message": "Type any message to include"}'

```

### Calculate cost of hiring a specific vehicle for a provided date range 
```
curl -X GET \
  http://localhost:8443/vehicles/REG1/2020-08-01/2020-09-30 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: b0c9b610-4bd4-2b96-40ae-b34373ae2aa0'

```

### To view Swagger 2 API docs
```
Not implemented yet.
```

### TO Generate keygen
 
```
keytool -genkey -alias selfsigned_localhost_sslserver -keyalg RSA -keysize 2048 -validity 700 -keypass trading -storepass trading -keystore ssl-server.jks
```

### Spring boot actuator to check the health of the micro service 
http://localhost:8443/actuator/health


### To install the micro service in docker
docker-compose build

### To run in docker
docker-compose up -d

### To check the docker process
docker ps