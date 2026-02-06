# Read Me First
This is a maven based project in java language.

The code can be compiled and run by
mvn clean spring-boot:run

The test cases can be executed by
mvn clean test

Once the application is up, the web api can be accessed via swagger
http://localhost:8080/swagger-ui/index.html

The project uses in memory DB with in built data
The following example can be tested

• 10 CE for $41.90
  o 2 packages of 5 items ($20.95 each)
• 14 HM for $78.85
  o 1 package of 8 items ($40.95 each)
  o 1 package of 5 items ($29.95 each)
  o 1 package of 1 item ($7.95 each)
• 3 SS for $35.85
  o 3 packages of 1 item ($11.95 each)

  by submitting following payload to create order API

  [
  {
    "code": "CE",
    "quantity": 10
  },
{
    "code": "HM",
    "quantity": 14
  },
{
    "code": "SS",
    "quantity": 3
  }
]
