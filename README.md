
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

# Using the app
When using Netbeans the single app is deployed to [http://localhost:8080/pointOfSalesDevice](http://localhost:8080/pointOfSalesDevice).

The PinPad uses the number buttons to navigate through the menu to either make a transaction or refund a transaction.  
`Cancel` - Clears the whole user input  
`Undo` - Removes the last digit inputted  
`Confirm` - Submits the inputed data to either move menu or submit/refund a transaction  

> Please not this application is built and tested for 1 browser instance per device. Use cases for multiple browser instances open on the same machine have not been tested and may cause error

## How to setup

Configure Properties from Web & Location of Properties File & Explanation of Default and non-default proeperties  

--ToDo---

## Logging
Location of normal Logs & Transaction Logs   

--ToDo--

### Unsupported Browsers
This application has been tested with Microsoft Edge and is approved to work for this browser. Other browsers may also work however the following won't work:
- Internet Explorer


# Building/Testing the App
Running the following command in the project root folder will build the project with Maven and will also run all the tests for the Project Solution:

`mvn clean install`

## JavaDoc Creation
---ToDo--



