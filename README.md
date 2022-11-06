# Drone Manager

## Run the project
#### Java version 19.0.1 is used to develop the application

### Using source code
Run `DroneManagerApplication.java` file using the IDE.

### Use JAR file
After running `mvn clean install` command on the project folder.
Open command prompt on project-root-folder/target folder and run `java -jar drone-manager-0.0.1-SNAPSHOT.jar`

### View Database
H2 database is used for the application. <br />
After running the project successfully you will be able to view the database using this link http://localhost:8080/h2-ui/

username - sa <br />
password - 1234

![image](https://user-images.githubusercontent.com/8392195/200192212-d94d127b-2601-4318-b219-447643552760.png) <br />

### View swagger UI
You can view the swagger UI using this link http://localhost:8080/swagger-ui/index.html#/

## Functionalities implemented

### 1. Register drone (POST)
#### Request payload
```json
{
  "serialNo": "TEST_DRONE_1",
  "model": "LIGHTWEIGHT",
  "weightLimit": 100,
  "batteryCapacity": 100,
  "state": "IDLE"
}
```

### 2. Load drone (POST)
#### Request payload
```json
{
  "drone": "TEST_DRONE_1",
  "state": "IN_PROGRESS",
  "medications": [
    {
      "name": "Antihistamine",
      "weight": 10,
      "code": "R06"
    },
    {
      "name": "Mucinex DM",
      "weight": 20,
      "code": "NDC 63824-056-32"
    }
  ]
}
```

### 3. Check medication on given drone (GET)
#### Path parameter
`
drone = TEST_DRONE_1
`

### 4. Battery level of given drone (GET)
#### Path parameter
`
drone = TEST_DRONE_1
`

### 5. Get available drones (GET)
