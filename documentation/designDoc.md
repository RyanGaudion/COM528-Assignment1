# Point of Sales - Design Doc & Project Plan


## Table of Contents

## Requirements
The following section outlines the requirements for the software to meet the [design brief](https://learn.solent.ac.uk/pluginfile.php/2938474/mod_resource/content/1/Assessment%20Brief%20-%20COM528-504%20-%20AE1%20-%20GROUP%20%282021-2022%29%201.0.pdf).

### Software Design Requirements
This application must be created using the following technologies:
* A [RESTful](https://www.codecademy.com/articles/what-is-rest) web app.
* The application must access the API located here: http://com528bank.ukwest.cloudapp.azure.com:8080/index.html
* Uses Java technologies for the backend.
* Javascript & css for the web interface.
* Unit testing coverage for each module of the project.
* Use a logging framework for debugging.
* All classes need to be documented with Javadoc.
* Error handling that provides meaningful messages for the user.

### Use Cases
The following requirements are written from the perspective of what different users need from the application:

| Use case ID  	| Actor 	| Action | Software Reaction |
| ----------- 	| ----------| ----------- | ----------- |
| UC1			| User 		| User enters in the [web address](url) for the banking application. | A web page opens, providing a numpad interface that allows the user to navigate the application. |
| UC2			| User 		| User selects the option allowing them to pay for their purchase.	|  The application requests the user's card number, name, expiry date, cvv code. <br /> Fields are to only allow input of the correct format, and should use input methods that enforce this. |
| UC3			| User 		| User fails to enter data into the form. | The app displays a helpful error message informing the user of what went wrong, and allows the user to try again. |
| UC4			| User 		| User enters incorrect card data into the form. | The web page must tell the user that their input was invalid, and allows them to try again. |
| UC5			| User 		| User enters in all of the credit card data. | The application verifies the validity of the credit card data by using the [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm). <br /> If the data is valid, the system proceeds with the transaction. |
| UC6			| User 		| The user confirms a purchase transaction. | The application transfers money from the users credit card equal to the total amount required by the order. If there is not enough money in the account, the transaction does not complete and the users is shown an error message.  |
| UC7			| User 		| The user confirms a purchase transaction. | The application stores all transactions locally in a log file. The CVV number must not be stored by the application. |
| UC8			| User 		| A user visits a page allowing them to reverse their previous transactions. | The application displays their previous transactions, and the gives them the option to reverse (refund) it. |
| UC9			| Admin		| Admin tries to configure a device. | The application needs to verify the credentials of the admin, so that they can access and modify the configuration settings. |
| UC10			| Admin		| Admin tries to view a log of all transactions. | All transactions, both successful and unsuccessful, undertaken by users must be stored locally in a logfile, this logfile should be accssible for admins. |
| UC11			| User		| User requests the app to perform any action. | The program must be able to complete user requests within 1 second. |
| UC12			| Admin 	| Admin configures the device. | The identify credentials of the device need to be stored securely in a properties file. |
| ?-UC13-?			| Admin		| Admin starts up the application.	| The properties file is read on start-up, automatically recognising the device details. |

<!--
### User Stories
The use cases for this project have been written in the form of "User Stories" which take the form of: As a role I want to action so that reason

    As a User I want to be able to enter a new transaction so that Money can be taken from my account
    As a User I want to be able to reverse a transaction so that Money can be refunded to my account
    As a User I want the app to be able to check my credit card Lunn Code so that I know I haven't accidently entered wrong details
    As a User I want to be able to Enter my credit card details (Card Number, Name, Expiry Date, CVV Code so that the money is taken from the correct account and that I can be associated with the transaction
    As a User I want to be able to be able to enter a cash amount for a transaction so that the correct amount is taken from my account
    As a User I want the app to be able to show the status of a transaction so that I can confirm or deny whether the transaction has been successful


    As an Admin I want to be able to configure the device only once so that the device uses the same settings everytime
    As an Admin when I configure the device I want to be able to supply Identity Credentials so that the device is able to authenticate to the API
    As an Admin I want A log of all transactions in a local log file so that I can which transactions were successful and unsuccessful
	
#### Requirements

* The CVV number must not be stored.
* Users must only be able to access data & transactions associated with their account.
* The web page must complete user submissions within 1 second.

-->

## Project Diagrams

### UML Class Diagrams

### UML Robustness Diagram

### UI Wireframes

## List of Features

### Deliverables


## Task Assignment
During this project we have split the Software Development Lifecycle (SDLC) into 3 main parts - Design, Development and Testing.

Each developer has responsibilities in each of these 3 areas.

### Design
| Task      | Description | Assigned To |
| ----------- | ----------- | ----------- |
| Use Cases      | Define the use cases for the application and also write up the uses cases in the form of user stories       | Steven Hawkins      |
| Features Definement   | Match Use Cases and User Stories to feature implementations for developers to write code for        | Steven Hawkins       |
| Page Design   | Create UI Wireframe mockup designs for each page in the application   | Kain Peacock    |
| UML Diagrams  | Create class diagrams and define relationships between models in the form of UML Diagrams as well as create UML Robusteness Diagrams to visualise Business Logic  | Lewis Holmes    |
| Git Strategy  | Research, Analyse and Define a standard git strategy including branching techniques, approval/review process as well as CI pipeline/ automated testing implementation  | Ryan Gaudion    |
| Coding Standard  | Research and Select a Java specific coding standard to implement during the development of this project  | Ryan Gaudion    |
| SDLC Methodology  | Analyse and define a software development lifecycle technique that we will use as a team.  | Ryan Gaudion    |

### Development
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| ToDo        | ToDo        | ToDo        |

### Testing
Every developer is responsible for writing tests for the code they implement. For example, the developer who writes a Model class should write the unit tests required to test every Get and Set method as well as any business logic methods the class implements. By making sure that all classes have Unit tests we can ensure our code has a great code coverage. This is especially important and useful when implementing Continious Integration as it allows us to identify breaking changes before they are even pulled into our development branch.

There are however additional testing Tasks that need to be complete outside of the iniital Unit tests per class which you can see below:
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| ToDo        | ToDo        | ToDo        |


## Coding Standard
Due to the fact there are 4 developers working on one project our code could look very different if we don't implement a standard for things like class naming conventions and variable naming conventions.

We compared both the Oracle Java Naming Conventions here - https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html
as well as teh Google Java Naming Conventions here - https://google.github.io/styleguide/javaguide.html#s5-naming

and decided that due to simplicity, ease of understanding as well as the readibility it provides, we'll implement the Google Java Naming Conventions. A simplified wording of this Naming convention has been shown below which all developers have agreed to follow

| Type        | Rule      | Examples    |
| ----------- | ----------- | ----------- |
| Packages        | Package names are all lowercase, with consecutive words simply concatenated together (no underscores)        | `com.example.deepspace` not `com.example.deepSpace` or `com.example.deep_space`        |
| Class Names        | Class names are written in UpperCamelCase.       | `Character` or `ImmutableList`      |
| Class Names        | Class names are typically nouns or noun phrases      | |
| Test Classes        | Named starting with the name of the class they are testing, and ending with Test      | `HashTest` or `HashIntegrationTest`      |
| Method Names        | Method names are written in lowerCamelCase.        | `sendMessage` or `stop`      |
| Method Names        | Method names are typically verbs or verb phrases.       | |
| Constant Names        | Constant names use CONSTANT_CASE: all uppercase letters, with each word separated from the next by a single underscore        | `NAMES` or   `EMPTY_ARRAY`   |
| Constants        | Constants are static final fields whose contents are deeply immutable (unchanging) and whose methods have no detectable side effects.        |  |
| Non-constant field names        | Non-constant field names (static or otherwise) are written in lowerCamelCase.        | `computedValues ` or  `index`|
| Non-constant field names        | These names are typically nouns or noun phrases.        | |
| Parameter names        | Parameter names are written in lowerCamelCase.        | `loader` or  `minNumber`|
| Parameter names        |One-character parameter names in public methods should be avoided.       | not `x` or `a`  |
| Local variable names        | Local variable names are written in lowerCamelCase.        | `message` or `totalAmount`  |
| Local variable names        | Even when final and immutable (but Non-Static) , local variables are not considered to be constants, and should not be styled as constants.       |  |
| Local variables        | A local variable in Java is a variable that’s declared within the body of a method.        |  |

## Git Strategy

Talk about Git-Flow was the de-facto default https://nvie.com/posts/a-successful-git-branching-model/ - why??
However in March 2020 Vincent Driessen added a note of reflection that git-flow was for applications where mulitple versions had to be maintained - not for something like web applications that only 1 version is every in deployment.

Vincient points to Github-Flow which he says is a much simplier workflow instead of trying to shoe-horn into Git-flow


"There's only one rule: anything in the main branch is always deployable."

Slight difference is we will be branching from dev instead of master as we each have tasks that rely on each other - that way dev doesn't have to be fully working however we will have approval to get into main.

### Branching/ Pull Request Rules/Approval

Work should never be don directly in the dev or master branch (unless done by automation - such as a versioning pipeline).
Team members should create a branch from dev to do their work and create a pull request back into dev once done.
We then have 3 steps of before approval can be complete.
Step 1 - Automated Unit Tests have to all pass - Explain ...
Step 2 - Manual Review of code changes for quality, comments/javadoc and test coverage
Step 3 - Manual User Test of Code to check for desired Functionality

If the output branch is master then --> must be reviewed by 2 developers to check all works as intended 


Like most software projects there will be a review & approval stage on every pull Request


### Ci Pipeline

Added a Github Workflow pipeline in order to automatically build and subseqently run our tests on all commits to all branches and pull requests to dev and master.

Benefits of this

Reference to benefits of CI 

## Methodology - Agile & Scrum - Weekly Standups

Using a condensed version of Scrum without the different roles
https://www.atlassian.com/agile/scrum

4 Parts of Scrum are:
- Sprint Planning
- Daily Scrum
- Sprint Review
- Sprint Retrospective

We are adapting this "full time" methodology to work for a team that's working part-time.

We will have a sprint length of a week starting on a Wedensday with our Sprint Planning meeting:
- During our sprint planning - We organsise our backlog and assign tasks for the week

We then work Thursday-Tuesday on the tasks in our Sprint
During this sprint we don't have the time for daily standups as we're only working on the project a couple of hours a day.
Instead we'll use our group communication channel as a way to discuss anything that would have been used in daily standups.

Sprint Review - Usually in Scrum you review the work that has been done at the end of every sprint. This naturally works for us as pull requests should be ready to be closed at the end of each sprint - this is where we'll review the work together ... quality

Sprint Retrospective - this will be the start of our Weekly meeting where we look back on the previous sprint ... what could be dont better, improvements ...

### Milestones & Deadlines

Talk about - only 5 sprints for the project ...

Want to get all features implemented by X - deadlines 

...

### Task Board in Github

To manage our sprints/task assignment - use a github project which allows us to define a backlog, active tasks and then also tasks to be reviewed ...