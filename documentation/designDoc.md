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
| UC3			| User 		| User fails to enter data. | The app displays a helpful error message informing the user of what went wrong, and allows the user to try again. |
| UC4			| User 		| User enters incorrect card data. | The web page must tell the user that their input was invalid, and allows them to try again. |
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
During this project we have split the Software Development Lifecycle (SDLC) into 3 main sections - Design/Documentation, Development/Unit Testing and Testing/Documentation.

Each developer has responsibilities in each of these 3 areas which can be seen below:

### Design
| Task      | Description | Assigned To |
| ----------- | ----------- | ----------- |
| Use Cases      | Define the use cases for the application and also write up the uses cases in the form of user stories.       | Steven Hawkins      |
| Features Definement   | Match Use Cases and User Stories to feature implementations for developers to write code for.        | Steven Hawkins       |
| Page Design   | Create UI Wireframe mockup designs for each page in the application.   | Kain Peacock    |
| UML Diagrams  | Create class diagrams and define relationships between models in the form of UML Diagrams.  | Lewis Holmes    |
| UML Diagrams  | Create a UML Robusteness Diagram to visualise the Business Logic within the application.  | Lewis Holmes    |
| Git Strategy  | Research, Analyse and Define a standard git strategy including branching techniques, approval/review process as well as CI pipeline/ automated build implementation.  | Ryan Gaudion    |
| Coding Standard  | Research and Select a Java specific coding standard to implement during the development of this project.  | Ryan Gaudion    |
| SDLC Methodology  | Analyse and Define a software development lifecycle technique that we will use as a team.  | Ryan Gaudion    |

### Development
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| ToDo        | ToDo        | ToDo        |

### Testing
Every developer is responsible for writing tests for the code they implement. For example, the developer who writes a Model class should write the unit tests required to test every Get and Set method inside of that class; as well as any business logic methods the class implements. By making sure that all classes have Unit tests we can ensure our code has a great code coverage. This is especially important and useful when implementing Continous Integration as it allows us to identify breaking changes before they are even pulled into our development branch.

There are however additional testing Tasks that need to be complete outside of the inital Unit tests per class which you can see below:
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| ToDo        | ToDo        | ToDo        |


## Coding Standard
Due to the fact there are 4 developers working on one project our code could look very different if we don't implement a standard for elements of the code such as class naming conventions and variable naming conventions.

We compared both the [Oracle Java Naming Conventions][1] and the [Google Java Naming Conventions here][2] and decided due to many reasons that we'd implement the Google conventions in our code. One of the first reasons was due to the fact that the [Oracle Conventions][1] are now for "Archive Purposes Only" and were last updated in April 1999. This is compared to Google's Java Naming Convention which was last updated May 2018 as seen by the `f9347e1` commit in their [styleguide][3] repository. The other reasons we decided upon the Google style guide was due to the ease of which each standard could be understood. You can see below a simplified wording of this naming convention which the team have agreed to follow.


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
| Non-constant field names        | Non-constant field names (static or otherwise) are written in lowerCamelCase.        | `computedValues` or  `index`|
| Non-constant field names        | These names are typically nouns or noun phrases.        | |
| Parameter names        | Parameter names are written in lowerCamelCase.        | `loader` or  `minNumber`|
| Parameter names        |One-character parameter names in public methods should be avoided.       | not `x` or `a`  |
| Local variable names        | Local variable names are written in lowerCamelCase.        | `message` or `totalAmount`  |
| Local variable names        | Even when final and immutable (but Non-Static) , local variables are not considered to be constants, and should not be styled as constants.       |  |
| Local variables        | A local variable in Java is a variable that’s declared within the body of a method.        |  |

## Git Strategy

The purpose of a Git strategy is to make all Developers aware of the process that code changes should go through before they are committed to the `master` branch. When using a well defined Git Strategy, no code should be able to enter the `master` branch without first passing all tests and in some cases be manually reviewed. The purpose of the stategy is to ensure broken code is not in the master branch.  

One of the first Git Strategies to be adopted by the Developer Community was Vincent Driessen's "Git Flow" which was described in "[A successful Git branching model][4]". Out of familiarity, the team would have used this strategy, however a recent addition to the blog post in March 2020 (10 years after it's original release) suggests that GitFlow is not the best solution for applications that don't need to be "explicitly versioned". He also specifically points out "Web Apps" as "not the class of software that I had in mind when I wrote the blog post" and instead suggests a "much simpler workflow".  

The workflow that he suggests as an alternative is "[Github Flow][5]" which is a much simpler workflow to follow. [GitHub Flow][5] suggests that the `master` branch should always be deployable meaning it always builds and all tests pass. The strategy also suggests that branches should be branched off of master for any feature or fix. This is the point at which we would need to adapt the Github Flow branching model to meet our needs better. We instead would need a branch called Dev the is branched off of master which all our feature/fix branches branch from. This is due to the fact that the way we are splitting up development between our team means that 1 developer's code might be dependent on the code of another developer. In an ideal situation, each developer would work on a stand alone piece of the project. This may work in the future once the core of the project is built however, to begin with, there will be dependencies between developers. In order to resolve this our dev branch will be the equivalent to the master branch in "Github Flow" without the requirement that the "branch is always deployable".  

If this project was to be a Production application then we would use the dev branch for the initial development of the application however, once the initial application was built, we would then use true [Github Flow][5] for any future features or fixes.  

### Branching, Pull Requests and Approvals

Our adapted GitHub Strategy workflow for features/fixes can be seen below:
1. **Create a Branch** - Use a descriptive name for the branch - it should describe what the fix/feature does
2. **Add Commits** - Work on your code in the branch - making sure to use descriptive commit messages
3. **Open a Pull Request** - Make sure to describe the solution provided by the branch in the Pull Request description
4. **Discuss and review your code** - For a branch to be committed we have decided that at least 1 other developer in the team has to review the code changes before merging and all automated tests will have to pass.
5. **Deploy** - We also have decided that 1 developer should be able to successfully build the code, run all tests successfully as well as test the feature/bug fix that has been implemented.
6. **Merge** - Once steps 4 & 5 have been complete, the code is ready to be merged into dev. Once merged, the original issue (if created) can now also be closed.


### Ci Pipeline

In order to assist with the testing of new code an automated workflow has been setup using Github Actions in order to automatically build the code with Maven which in turn automatically runs all unit tests in from the codebase. This is why it is important for us in order to write tests as we write our code so we can identify any issues in the codebase as they arise before their associated pull request is even merged.  

The workflow is setup in order to execute on code pushes to all branches as well as any pull requests to dev or master. The benefits of this CI (Continuous Integration) are:
- All Code is validated that it can build before it is merged into our main working branch (dev) and our production branch (master). This means that no code with syntaxical errors should ever enter our working branch. This means that developers should never have to spend time debugging another developers code just so they can test their own code.

- All Tests are run on all code for all new pull requests to dev. Due to the fact that within this project code should only enter the dev branch through a pull request - we are able to only accept code where all tests pass. In addition to this we have stated that all developers are responsible for writing tests for their own code, meaning that all pull requests would automatically test every developers code. These automated tests should catch a large majority of the cases where a new feature/fix accidently breaks another developers code.


In a 2016 paper named "[Usage, Costs, and Benefits of Continuous Integration in Open-Source Projects][6]" where over 34,500 open source repositories and over 1,500,000 automated builds were studied, it was found that, within Open Source Projects, when a CI pipeline is used the rate of releases was "double the release rate" of a project not using CI pipelines. However, it was also found that projects with CI pipelines are 5% less likely to accept pull requests. The explanation given in the paper is due to the fact that with CI all pull requests provide build information, meaning code that doesn't pass all tests won't be merged; whereas in a project with no CI it is more likely for code that doesn't pass tests to still be merged. The paper also concludes that pull requests on projects that use CI are usually merged over 25% faster than those without CI pipelines.

From this paper, we can conclude that by using a CI pipeline in our code we can expect:
- A greater release rater of features and fixes
- Quicker identification of bugs
- Faster time for code to get from feature/fix branches to our shared codebase


## Agile Methodology

Order to provide process to the development work we were doing, it was decided as a team that we should implement a software development lifecycle (SDLC) in order to manage the development work that we were completing as a team. We decided that in order to improve collaboration and communication within the development team we would follow an Agile development lifecycle. The improved collaboration this provided would be critical due to the fact that as a team we would be completing development work individually at different times of the day/week.

We decided upon Agile after reading a Whitepaper from "Alon Koch" (the Course Director of "Global Knowledge") named "[12 Advantages of Agile Software Development][7]". Within this paper the main benefits that would relate most to a project of this size were a "Productive Development Team", "Good Quality Software" as well as "On Time" delivery of software.

According to whitepaper, these 3 benefits were to be achieved through:
- Short iterations of development with a "milestone" at the end of each iteration
- An improved "focus on testing" and practices such as "coding standards" and "peer reviews" 

In order to gain these benefits within our development process we have decided to implement a simplified version of a "full-time" Agile framework. The reason we have not implemented a full framework is due to the fact that we are working only a small part of our work week on this project meaning that we want to be able to maximise the development time spent on the project. If we tried to implement a full framework then the majority of our time spent on the project would be working on the Agile process rather than the actual work.  

In order to get the best balance between our framework and our development we have decided to adapt a version of [Scrum][8]. As described by Atlassian, there are 6 events that take part in a scrum cycle. However, we are able to condense these into the following 4 stages:

- Sprint Planning
- Daily Scrum
- Sprint Review
- Sprint Retrospective

In order to meet each of these 4 stages we have setup the following:

- A sprint length of 1 week
- Weekly Sprint meetings were we can have our planning, review and sprint retrospective.
- A Github Projects board in order to keep track of our backlog and work todo
- Teams channel for daily updates

We will have a sprint length of 1 week starting on a Wednesday with out Sprint Planning meeting. During this meeting we will organise our backlog and assign tasks to all developers for that week. We will then work Wednesday-Tuesday on our assigned tasks. During this sprint we don't have time for daily standup meetings as we will only be working on the project for a couple of hours. Instead we'll use our group's communication channel as a way to discuss status updates and any blockers anyone might have.  

Usually in a Scrum you review the work that has been done at the end of every sprint as part of the "Sprint Review", however for us the review of pull requests counts as our sprint review. This is due to the fact that most pull requests should be ready to close at the end of each sprint anyway. During these pull request review we'll check the code of other developers together to check for standard adherence, code quality as well as the fact that the feature or fix does as it was intended to.

Finally, we come back to our Wednesday meetings where we will start with our Sprint Retrospective - a time where we can reflect on the previous sprint and find areas in our process that could be improved for the next sprint.

### Task Tracking

In order to keep track of milestones and deadlines we have leveraged the "Project" tool that is provided by Github. It provides a kanban style board for use in your project in order to keep track of tasks and task states. For our project we have the following columns:
- Backlog - All tasks start in the backlog, this is our "todo" list of all tasks that have not yet been started.
- In Progress - These are tasks that have been assigned for the sprint that week.
- Pending Review - These are tasks that the developer has completed but need to be reviewed by another member of the team.
- Done - These are tasks that have been both completed by the developer as well as reviewed by another team member

Each week during our planning meeting we will update the state of all tasks, add any additional tasks to the backlog and then assign tasks for the next sprint.

Due to the fact that we have a short timeline for this project our task board is really important in visualising how much work is left to complete as well as the progress of work during a week. It will allow us to see our "burndown rate" - how much work we are going through per week and will also allow us to speed up development if we are falling behind our deadline.




[1]: "https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html"
[2]: "https://google.github.io/styleguide/javaguide.html#s5-naming"
[3]: "https://github.com/google/styleguide"
[4]: "https://nvie.com/posts/a-successful-git-branching-model/"
[5]: "https://guides.github.com/introduction/flow/"
[6]: "http://cope.eecs.oregonstate.edu/papers/OpenSourceCIUsage.pdf"
[7]: "https://cs.anu.edu.au/courses/comp3120/public_docs/WP_PM_AdvantagesofAgile.pdf"
[8]: "https://www.atlassian.com/agile/scrum"