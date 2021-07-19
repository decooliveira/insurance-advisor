**Simple Insurance Advisor**


**Overview**

The Simple Insurance Advisor is a tool that provides a personal risk profile based on simple questions without any knowledge of the insurance business.

This tool was built in Java 11 on top of Spring boot framework version 2.5.2.

The application is organized in layers. The controller layer receives the input and invokes the business layer which applies the required rules in order to calculate the insurance score and to build the risk profile for a given user.



**Input**

In order to calculate the associate risks and build the user risk profile our tool expects a set of personal information as shown below:

{

`  `"age": 35,

`  `"dependents": 2,

`  `"house": {"ownership\_status": "owned"},

`  `"income": 0,

`  `"marital\_status": "married",

`  `"risk\_questions": [0, 1, 0],

`  `"vehicle": {"year": 2018}

}

You may receive a bad request error if the attributes were out of accepted values range. 
For example, age should be a value grater or equal to zero.

**Output**

After applying all the rules and calculate the risk, a risk profile is returned to the user. Risk Profile entity has the following structure:

{

`  `"auto": "economic":

`  `"disability": "ineligible",

`  `"home": "regular",

`  `"life": "responsible"

}

**Components**


**AdvisorController**

The rest controller of the application. It receives an **PersonalInformation** object as input and then returns a **RiskProfile** object as response.

**RiskCalculator**

A service class of the business layer. It converts the **PersonalInformation** object received as input into a **InsurableData** entity. Then it delegates the rules processing the risk profile building to another component called **RiskProfileBuilder**.

**Insurance**

It’s an interface that represents a generic type with a single operation “**getScore**(). This type is implemented by concrete classes such as **AutoInsurance**, **HomeInsurance**, **DisabilityInsurance** and **LifeInsurance**.

In order to add new lines of insurances we only need to implement **Insurance** **interface**.

**Insurable**

It’s an interface that represents a generic type of items that can have insurance such as Vehicle, House and so on. In future, new items only need to implement this interface, so we can have items like airplanes, boats and others.

**InsurableData**

It’s a generic type of insurable data. It’s implemented by a concrete class called **InsuranceData**. In future this class should be segregated.

**InsuranceData**

It holds together the data of our current insurable items such as **Vehicle**, **User** and **House**. New insurable items could be added to this class. As an improvement this class should be segregated when new items will be added to the tool.

**RiskProfileBuilder**

This class is responsible to build the risk profile object that will be sent as response to user. It uses the **InsuranceFactory** in order to get the insurances and then set each of them to the **RiskProfile** object.

**InsuranceFactory**

Although it is a factory, it delegates the building of insurance objects to calculator objects. As a strategy pattern, it uses the **CalculatorFactory** which builds the appropriate insurance objects.

**CalculatorFactory**

This factory returns calculator objects using strategy pattern. This way, each line of insurance area able to calculate and build their own insurance objects with correct scores.

This strategy pattern makes simple adding new strategies for new insurance lines to the advisor tool


**Usage**

**System Requirements**

Java 11

Maven 3.8.1

Check the installation with the command:

**mvn –version**

Then you should see something like the following:

**Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)**


You may want to check the **Spring Boot** Getting Started docs
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html


We may run the application by building a docker image or without docker.

First install Maven on your local machine

Then clone the following git repository 

**git clone [**https://github.com/decooliveira/insurance-advisor.git](https://github.com/decooliveira/insurance-advisor.git)**
or download it from https://github.com/decooliveira/insurance-advisor and unzip the file

**1) WITHOUT DOCKER**

Once the source code is downloaded, execute the command below:

**cd insurance-advisor/**

At first, if you want to run the tests run execute the following command:

**mvn test**


Now, in order to run the application, execute:

**./mvnw spring-boot:run**

If it fails, please try with the following commands:

**./mvnw install**
and then

**./mvnw spring-boot:run**


After few seconds, you will be able to see the message “**Completed initialization”** in your terminal. 

2021-07-19 16:52:56.483  INFO 30274 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'

2021-07-19 16:52:56.558  INFO 30274 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : **Completed initialization** in 72 ms


Now the API is ready to receive requests and it’s available on <http://localhost:8080>/api/v1/calculate


**2) WITH DOCKER IMAGE**

If you have the docker desktop installed in your machine you can execute the following commands to get the application running:

**./mvnw install**
See more on https://docs.docker.com/desktop/

After maven command completes its execution, build the image with the following command:

**docker build -t insurance/advisor .**

Once image is built, now you can run the docker container.  To do so, execute the command below:

**docker run -p 8080:8080 insurance/advisor**

**When initialization is complete, you can request the API through the URL http://localhost:8080/api/v1/calculate** 

** API requests **

You should use Swagger interface in order to send requests to API. Swagger url: http://localhost:8080/swagger-ui.html

You may use Postman to send request to API if you prefer https://www.postman.com/downloads/
