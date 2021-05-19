# academy-registration
Academy Registration App

This is a Spring project demonstrating URL shortener. This is a dockerized solution . The docker image is available in 
the docker hub at: shivasr/academy-app:1.0.0.

Run com.app.academyregistration.AcademyRegistrationApplication as a Java Application.

Runs on default port of Spring Boot - 8080

Application uses h2 database to run the tests.

mvn clean install generate a jar which can be run as a command-line. This application requires a mysql DB. Use 
docker-compose to bring the solution locally.

## How this works
This application is developed using Spring boot framework and launches a server at port, say 8080 and exposes the below REST API endpoints:

1. Create students: 
   POST http://localhost:8080/api/v1/students
2. Create professors: 
   POST http://localhost:8080/api/v1/professors
3. Create courses:
   POST http://localhost:8080/api/v1/courses
4. Assign Professors to the courses:
   PATCH http://localhost:8080/api/v1/courses/1

## How to deploy
This solution is containerized using the docker. Follow the below steps to deploy the solution:
Step 1: Build the project (If you want to use docker image directly from the hub, go to step 3)
```shell
# On Windows
mvnw.cmd clean install

# On Linux
mvn clean install
```
Step 2: Install docker & docker-compose if not available locally
```
Refer https://docs.docker.com/get-docker/ to install docker locally.
```

```
Refer https://docs.docker.com/compose/install/ to install docker-compose locally.
```

Step 3: Run the solution using docker-compose
```shell
docker-compose up --build
```

### How to use the solution
Once the solution is deployed using a REST API client such as postman create a URL mapping record by posting a JSON to
the endpoint, follow below steps:

Step 1: Create student:

POST http://localhost:8080/api/v1/students
Content-Type: application/json

Body:
{
"name": "Shivakumar R"
}

Response:
{
    "id": 1,
    "name": "Shivakumar R"
}
###

Step 2: Create professor
POST http://localhost:8080/api/v1/professors
Content-Type: application/json

Body:
{
"name": "Prof H Richard"
}

Response
{
"id": 1,
"name": "Prof H Richard"
}

###
Step 3: Create course:
POST http://localhost:8080/api/v1/courses
Content-Type: application/json

{
"name": "Certified Solution Architect Associate"
}

Step 4: Assign professors and/or students

###
PATCH http://localhost:8080/api/v1/courses/1
Content-Type: application/json

{
"professors" : [1],
"students": [1]
}
###