
# Point of Sales Device
This Point of Sales App is a single page web app that simulates the kind of point of sales device that a shopkeeper would use to take transactions. The app is able to send transactions to the shopkeeper's card as well as refund transactions too.

Our Design Documentation for the app can be found [here](https://github.com/RyanGaudion/COM528-Assignment1/blob/dev/documentation/designDoc.md).

Our Testing Documentation for the app can be found [here](https://github.com/RyanGaudion/COM528-Assignment1/blob/dev/documentation/testDoc.md).

This project is licensed under the Apache License Version 2.0, which can be found [here](https://github.com/RyanGaudion/COM528-Assignment1/blob/dev/LICENSE).

The developers involved in this project where:
- [Kain Peacock](https://github.com/kvpeacock)
- [Lewis Holmes](https://github.com/lewis-holmes-98)
- [Richard Priest](https://github.com/RPriestUK)
- [Steven Hawkins](https://github.com/5hawks48)
- [Ryan Gaudion](https://github.com/RyanGaudion)

The application is built using Enterprise Java technologies with a Multi-Module Maven Project Structure. It uses a Java Server Page for the front end with Javascript to generate the UI. The UI communicates to the JSP using a simple HTML form. The application uses a JAX-RS Client as well as Jersey to communicate to a REST Bank API. All projects in the solution have comprehensive unit tests. 

# Using the app
When using Netbeans the web app is deployed by default to [http://localhost:8080/pointOfSalesDevice](http://localhost:8080/pointOfSalesDevice).

The PinPad uses the number buttons to navigate through the menu to either make a transaction or refund a transaction.  
`Cancel` - Clears the whole user input  
`Undo` - Removes the last digit inputted  
`Confirm` - Submits the inputed data to either move menu or submit/refund a transaction  

> Please note this application is built and tested for 1 browser instance per device. Use cases for multiple browser instances open on the same machine have not been tested and may cause error

## How to setup


## Configure Properties

The default properties file can be located at [pointOfSalesDevice-DAO/src/main/resources/application.default.properties](pointOfSalesDevice-DAO/src/main/resources/application.default.properties).

From here, the user is able to edit the default properties, upon running the application these will then be used with priority. These properties include the following:

`Username` - Username for logging into the application

`Password` - Users password

`API URL` - Rest endpoint for data to be sent to / received from

`Shopkeepercard` - Card number for transactions

> Note - None of these properties are allowed to be blank.

The user is able to create a temporary properties file from within the application. To do this, proceed to [http://localhost:8080/pointOfSalesDevice/properties.jsp](http://localhost:8080/pointOfSalesDevice/properties.jsp). 

This page displays a form from which the user is able to change properties. No field can be blank. Upon clicking `update properties`, a temporary properties file with be created, or overwritten if one already exists with this new data. 

The temporary properties file can be located at [tomcat\apache-tomcat-9.0.53\temp](tomcat\apache-tomcat-9.0.53\temp)


## Logging
Location of normal Logs & Transaction Logs

Logging is handled by Log4j2 and the log config can be found at `web\src\main\resources\log4j2.xml`

2 Log files are created as part of this application - the standard log file containing all logs and a transaction specific log file - more information can be seen in the table below. The `${sys:catalina.base}` variable in the table below relates to your Apache Tomcat installation folder.

| Name      | Level | Location | Description |
| ----------- | ----------- |----------- | ----------- |
| POS-appLog      | `DEBUG` | ${sys:catalina.base}/logs/app/POS-appLog.log       | This contains all logs from level Debug and above from all namespaces of the application | 
| POS-transactionLog   | `INFO` | ${sys:catalina.base}/logs/app/POS-transactionLog.log        | This contains only 5 types of logs. `Sent Transaction` - The transaction and it's response. `Transaction To Refund` the transaction that has been requested to be refunded. `Refund Transaction` the new transaction that refunds the previous transaction. `Transaction Failed` transaction failed due to invalid details (card not found or username & password incorrect). `Refund Failed` refund failed due to invalid details (card not found or username & password incorrect). |

### System Requirements
This system requirements for this program are:
- A system running Windows 10 or newer.
- [Java JDK](https://www.oracle.com/java/technologies/downloads/#java8) version 8 or newer.
- [Apache Maven](https://maven.apache.org/install.html) 3.8 or newer
- One of the following web browsers: Google Chrome, Mozilla Firefox or Microsoft Edge. Internet Explorer is unsupported by this application.

# Building/Testing the App
Running the following command in the project root folder will build the project with Maven and will also run all the tests for the Project Solution:

`mvn clean install`

## JavaDoc Creation
To view JavaDocs, navigate to the project folder within a command prompt and run:

`mvn javadoc:javadoc`

Visit https://maven.apache.org/plugins/maven-javadoc-plugin/index.html for more information.

### Error Note

If you recieve errors for unknown reasons please clear your `tomcat\temp` and your `tomcat\logs` directly and try running the application again.

