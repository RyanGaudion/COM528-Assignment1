# Point of Sales Documentation

## Contents
This Project Plan contains the following documentation:
- Use Cases
- Project Plan
- Task Assignment
- List of Features
- Test Plan (including matching to use cases)
- UML class diagrams
- UML Robustness Diagram


## Use Cases
The use cases for this project have been written in the form of "User Stories" which take the form of:
As a `role` I want to `action` so that `reason`

- As a **User** I want to be able to **enter a new transaction** so that **Money can be taken from my account**
- As a **User** I want to be able to **reverse a transaction** so that **Money can be refunded to my account**
- As a **User** I want the app to be able to **check my credit card Lunn Code** so that **I know I haven't accidently entered wrong details**
- As a **User** I want to be able to **Enter my credit card details (Card Number, Name, Expiry Date, CVV Code** so that **the money is taken from the correct account and that I can be associated with the transaction**
- As a **User** I want to be able to **be able to enter a cash amount for a transaction** so that **the correct amount is taken from my account**
- As a **User** I want the app to be able to **show the status of a transaction** so that **I can confirm or deny whether the transaction has been successful**
</br>
- As an **Admin** I want **to be able to configure the device only once** so that **the device uses the same settings everytime** 
- As an **Admin** when I configure the device **I want to be able to supply Identity Credentials** so that **the device is able to authenticate to the API**
- As an **Admin** I want **A log of all transactions in a local log file** so that **I can which transactions were successful and unsuccessful**

</br>
## Project Plan
The plan for the project is to split the application into:

- Webapp UI
- API Connection
- Business Logic
- Device Configuration
- Exception Handling & Logging
</br>
- Documentation (JavaDoc & Markdown)
- Unit Testing
- JavaDoc

The first parts of the project will be split between the members of the team whereas the last 2 'testing' and Documentation will be done throughout the development process.

The application will be a 1 page application:

- The default user page - this page will allow a user to enter transaction details as well as see the status of transactions

### Source Control
The source code for the application will be stored in Github with 2 main branches - `dev` and `master`. All development work will either be complete directly into the dev branch or will be done on an additional branch which is then merged into dev.

Once the application is at a point where all tests pass and the application is in a working state, then the code from the dev branch will be pulled into master.

The Github project will also include automatic builds on all pull requests so, as long as we have good test coverage, we can see the effect of a pull request on the integrity of the code.

## Task Assignment
TODO


## Feature List
### User Page
- Card Information Input
- Transaction Amount Input
- Ability to specify transaction type (payment or refund)
- Check automatically for valid card with Lunn Code checking
- Visible status change after each transaction (successful or failed)

### Configuration
- Logging of transactions to Log file
- Exclude CVV codes from log file

- Store of Identity Credentials in properties file
- Auto reading properties file on startup


## Test Plan
For this application we will complete 2 types of testing 'Unit Testing' and 'UI Testing'. The role of Unit Testing in this application is to test small, lower level modules of code individually in order to make sure that they work as intended.

This will also be useful when running automated builds on the solution in order to know if one developer's change has broken any other part of the application.

The UI Testing will be manual testing against each of the Use Cases defined at the top of this document. Each test will be documented and will make sure that the desired functionality works as intended.

## UML Class Diagram
TODO


##UML Robustness Diagram
TODO