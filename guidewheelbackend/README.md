## How to Run the application
### Option 1
1. Install Java 17.
2. Install Intellij IDEA.
3. Open the `guidwheelbackend` directory in IntelliJ idea and run the GuidewheelbackendApplication.

### Option 2
1. Install Java 17.
2. Install maven.
3. cd to the `guidewheelbackend` directory in terminal
4. run `mvn spring-boot:run`

Note: The port 8080 must be available for the application to run properly


## API Specification
There are 2 APIS in this application

### 1. GET /api/machine_data
The API url is http://localhost:8080/api/machine_data?fromDate=2021-01-27&fromTime=04:00:00&toDate=2021-01-28&toTime=12:55:00  
The query params are:
1. fromDate
2. fromTime
3. toDate
4. toTime

The response is of the form:  
`{
"loaded": 0,
"unloaded": 0,
"idle": 0,
"off": 1976
}`

It gives the number of loaded, unloaded, idle, off instants between from date and time and to date and time.

### 2. GET /api/machine_time_series_data
The API URL is http://localhost:8080/api/machine_time_series_data?fromDate=2021-01-27&fromTime=04:00:00  
The query params are:
1. fromDate
2. fromTime

The response is of the form:  
`{
"2021-01-27T04:00": 156.1384886666666,
"2021-01-27T04:01": 155.459202,
"2021-01-27T04:02": 154.40332466666666,
"2021-01-27T04:03": 153.78212799999997,
"2021-01-27T04:04": 154.97344399999994,
"2021-01-27T04:05": 155.0791573333333,
}`  

It gives the power consumption at every instant for a period of 12 hrs starting 
from the from date and time supplied in the query params.
