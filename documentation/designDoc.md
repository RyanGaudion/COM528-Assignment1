# Point of Sales - Design Doc & Project Plan


<details open="open">
    <summary> <h2>Table of Contents</h2> </summary>
    <ol>
        <li>
            <a href="#requirements">Requirements</a>
            <ul>
                <li><a href="#software-design">Software Design</a></li>
                <li><a href="#use-cases-and-features">Use Cases</a></li>
            </ul>
        </li>
        <li>
            <a href="#project-diagrams">Project Diagrams</a>
            <ul>
                <li><a href="#uml-class-diagrams">UML Class Diagrams</a></li>
                <li><a href="#uml-robustness-diagram">UML Robustness Diagram</a></li>
                <li><a href="#ui-wireframes">UI Wireframes</a></li>
                <li><a href="#menu-system-flow">Menu System Flow</a></li>
            </ul>
        </li>
        <li>
            <a href="#task-assignment">Task Assignment</a>
            <ul>
                <li><a href="#design">Design</a></li>
                <li><a href="#development">Development</a></li>
                <li><a href="#testing">Testing</a></li>
            </ul>
        </li>
        <li><a href="#coding-standard">Coding Standard</a></li>
        <li>
            <a href="#git-strategy">Git Strategy</a>
            <ul>
                <li><a href="#branching-pull-requests-and-approvals">Branching, Pull Requests and Approvals</a></li>
                <li><a href="#ci-pipeline">Ci Pipeline</a></li>
            </ul>
        </li>
        <li>
            <a href="#agile-methodology">Agile Methodology</a>
            <ul>
                <li><a href="#task-tracking">Task Tracking</a></li>
            </ul>
        </li>
    </ol>
</details>


## Objectives and requirements
The overall objective of this application is to enable a user to submit a transaction with their card, removing the funds from their account and transferring it to the account associated with the POS device.
Users must be able to enter in their card details (card number, name, expiry date, CVV code). Our solution must validate the card number using the Luhn algorithm.
In addition to this, refunding transactions must be possible.

The following section outlines the requirements and features the application must have for it to meet the [design brief](https://learn.solent.ac.uk/pluginfile.php/2938474/mod_resource/content/1/Assessment%20Brief%20-%20COM528-504%20-%20AE1%20-%20GROUP%20%282021-2022%29%201.0.pdf).

### Software Design
This application must be created using the following technologies:
* A [RESTful](https://www.codecademy.com/articles/what-is-rest) web app.
* The application must access the API located here: http://com528bank.ukwest.cloudapp.azure.com:8080/index.html
* Uses Java technologies for the backend.
* JavaScript & CSS for the web interface.
* Unit testing coverage for each module of the project.
* Use a logging framework for debugging.
* All classes need to be documented with Javadoc.
* Error handling that provides meaningful messages for the user.

### Use Cases and Features
The basic features required for this application are:
* A single Pinpad UI page allowing the user to do the following and provide meaningful responses back to the user:
    * Enter a new transaction including card number, amount, expiry date and card CVV
    * Reverse a transaction
    * Check a credit card Lunn Code
* A single properties page which allows the configuration of the device (loading details from application properties file)
* The ability to log all transactions to a single transactions log file


The following requirements are written from the perspective of what different users need from the application:

| Use case ID 	| Actor 	| Action | Software Reaction |
| ----------- 	| ----------| ----------- | ----------- |
| UC1			| User 		| User enters in the [URL](http://localhost:8080/pointOfSalesDevice/) for the banking application. | A web page opens, providing a numpad interface that allows the user to navigate the application. |
| UC2			| User 		| User selects the option allowing them to pay for their purchase.	| The application requests the user's card number, name, expiry date, CVV code. <br /> Fields are to only allow input of the correct format and should use input methods that enforce this. |
| UC3			| User 		| User fails to enter data. | The app displays a helpful error message informing the user of what went wrong and allows the user to try again. |
| UC4			| User 		| User enters incorrect card data. | The web page must tell the user that their input was invalid and allows them to try again. |
| UC5			| User 		| User enters in all of the credit card data. | The application verifies the validity of the credit card data by using the [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm). <br /> If the data is valid, the system proceeds with the transaction. |
| UC6			| User 		| The user confirms a purchase transaction. | The application transfers money from the user’s credit card equal to the total amount required by the order. If there is not enough money in the account, the transaction does not complete, and the users is shown an error message.  |
| UC7			| User 		| The user confirms a purchase transaction. | The application stores all transactions locally in a log file. The CVV number must not be stored by the application. |
| UC8			| User 		| A user visits a page allowing them to reverse their previous transactions. | The application displays their previous transactions, and the gives them the option to reverse (refund) it. |
| UC9			| Admin		| Admin tries to configure a device. | The admin enters the configuration settings URL and can edit the properties file from there. |
| UC10			| Admin		| Admin tries to view a log of all transactions. | All transactions, both successful and unsuccessful, undertaken by users must be stored locally in a logfile; this logfile should be accessible for admins and stored locally. |
| UC11			| User		| User requests the app to perform any action. | The program must be able to complete user requests within 1 second. |
| UC12			| Admin 	| Admin configures the device. | The identity credentials of the device need to be stored securely in a local properties file. |
| UC13			| Admin		| Admin starts up the application.	| The properties file is read on start-up, automatically recognising the device details. |

## Project Diagrams

### Use Case Diagrams

A use case diagram provides a high-level summary of the program and highlights the scope of the application and what systems it interacts with. From the diagram, use cases which go into more detail can be created.

![](/documentation/Images/Use_Case_Diagram.drawio.png)

### UML Class Diagrams

A class diagram is used to describe classes and their relationships within the application. They correspond with the classes in the source code. This allows for users and developers to quickly glance at a class, and its relationships with other classes without having to look into the source code, which is often separated into many locations throughout the application.

![](/documentation/Images/pos.model.service_classDiagram.PNG)

The above class diagram was designed before development of the project. As you can see via the class diagram below - the models changed during the development of the project. This is okay due to the fact we are using an Agile methodology - our needs changed, or we had unforeseen issues that required the changes seen below.

![](/documentation/Images/NewSimplePOSClassDiagram2.png)

### UML Robustness Diagram

Robustness diagrams describe the system usage within the context of the object model. This allows for a clear and unambiguous explanation on how the model, its classes, and how the operational processes work within the application. To create a robustness diagram, one must first identify the main classes, abstractions and objects which will be used. Once identified and the use cases are understood, the robustness diagram can be created, demonstrating a technical overview and explanation on the uses of the application. 

![](/documentation/Images/BankAccountRobustness.PNG)

As development progressed and our codebase evolved, we realised our original robustness diagram was no longer fit for purpose. As such, a new one was created, ensuring it accurately portrays how the system will be utlised by clients within the context of the overarching object model.

![](/documentation/Images/BankAccountRobustnessMk2.png)

### UI Wireframes

UI Wireframes are incredibly useful during the design stage, as it allows for quick mockups of the proposed design that can quickly be reviewed and edited. Once the wireframes are finalised, the development team will use the designs as templates when creating the front-end UI. The first wireframes created were for a full bank service client, including pages for creating accounts, managing transactions and requesting refunds.

![](/documentation/Images/Wireframes/Archive/Account_List.png)

![](/documentation/Images/Wireframes/Archive/Account_Viewer.png)

![](/documentation/Images/Wireframes/Archive/Transaction_Viewer.png)

![](/documentation/Images/Wireframes/Archive/JSON_Viewer.png)

![](/documentation/Images/Wireframes/Archive/New_Transaction.png)

Sadly, the assessment criteria was misunderstood, and these wireframes are not relevant to the solution. As the solution requires a pin-pad style UI, a new wireframe diagram had to be created.

![](/documentation/Images/Wireframes/Pin_Pad.png)

Upon testing and developing the proposed pin pad design, we realised a few improvements could be made. The gold buttons were distracting, the square buttons harsh and imposing, and there was no way of inputting a decimal number. As such, the buttons were rounded slightly, the button text was set to white and the buttons to black (emulating a card reader), and a new button for a decimal point was included.

![](/documentation/Images/Wireframes/Pin_Pad_Mk2.png)

#### Menu System Flow
Menu system flow diagram:

![](/documentation/Images/MenuSystemFlow.png)

## Task Assignment
During this project we have split the Software Development Lifecycle (SDLC) into 3 main sections - Design/Documentation, Development/Unit Testing and Testing/Documentation.

Each developer has responsibilities in each of these 3 areas which can be seen below:

### Design
| Task      | Description | Assigned To |
| ----------- | ----------- | ----------- |
| Use Cases      | Define the use cases for the application and also write up the use cases in the form of user stories.       | Steven Hawkins      |
| Features Definition   | Match Use Cases and User Stories to feature implementations for developers to write code for.        | Steven Hawkins       |
| Page Design   | Create UI Wireframe mockup designs for each page in the application.   | Kain Peacock    |
| UML Diagrams  | Create class diagrams and define relationships between models in the form of UML Diagrams.  | Lewis Holmes    |
| UML Diagrams  | Create a UML Robustness Diagram to visualise the Business Logic within the application.  | Lewis Holmes    |
| Git Strategy  | Research, Analyse and Define a standard git strategy including branching techniques, approval/review process as well as CI pipeline/ automated build implementation.  | Ryan Gaudion    |
| Coding Standard  | Research and Select a Java specific coding standard to implement during the development of this project.  | Ryan Gaudion    |
| SDLC Methodology  | Analyse and Define a software development lifecycle technique that we will use as a team.  | Ryan Gaudion    |

### Development
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| Models & Interfaces | Create the DTO (Data Transfer Objects), Service Objects and interfaces | Ryan Gaudion |
| Service & Client Factory | Create the Factory class and implement the Singleton Pattern for both the Banking Service and the REST Client | Ryan Gaudion |
| Banking Service | Create an abstraction upon the client as well as the Luhn Algorithm code in order to access all functionality through a single banking service class | Ryan Gaudion |
| Build REST Client    | Code the REST Client and make it use our DTO models | Kain Peacock & Ryan Gaudion |
| Properties File Reading | Setup the DAO (Data Access Object) to be able to read and write data from the Properties File| Lewis Holmes |
| Transaction Logging | Develop code that allows all transactions to be logged to their own file | Ryan Gaudion |
| Luhn Algorithm | Develop the Luhn Algorithm validation class in order to verify the legitimacy of Card | Steven Hawkins |
| Pin Pad UI | Develop a Pin Pad UI that displays the entered numbers on the screen | Richard Priest |
| UI Menu | Develop a menu system to allow all required functionality to be able to be inputted via the Pin Pad | Richard Priest & Ryan Gaudion |
| UI to backend communication | Write code on the JSP frontend that allows each menu action to be processed via the Java JSP backend  | Richard Priest & Ryan Gaudion |

### Testing
Every developer is responsible for writing tests for the code they implement. For example, the developer who writes a model class should write the unit tests required to test every Get and Set method inside of that class; as well as any business logic methods the class implements. By making sure that all classes have Unit tests we can ensure our code has a great code coverage. This is especially important and useful when implementing Continuous Integration as it allows us to identify breaking changes before they are pulled into our development branch.

There are however additional testing Tasks that need to be complete outside of the initial Unit tests per class which you can see below:
| Task        | Description | Assigned To |
| ----------- | ----------- | ----------- |
| Standard Unit Tests       | All methods must have a corresponding unit test. Creators of the method are responsible for the creation of the unit tests for that method.        | Everyone       |
| Develop Test Plan        | Develop a test plan for the entire scope of the work, including edge cases.        | Kain Peacock        |
| Carry Out Test Plan        | Carry out all the tests detailed within the Test Plan and document the results.        | Lewis Holmes        |

## Coding Standard
Due to the fact there are 4 developers working on one project our code could look very different if we don't implement a standard for elements of the code such as class naming conventions and variable naming conventions.

We compared both the [Oracle Java Naming Conventions][oracle-naming] and the [Google Java Naming Conventions here][google-naming] and decided due to many reasons that we'd implement the Google conventions in our code. One of the first reasons was due to the fact that the [Oracle Conventions][oracle-naming] are now for "Archive Purposes Only" and were last updated in April 1999. This is compared to Google's Java Naming Convention which was last updated May 2018 as seen by the `f9347e1` commit in their [styleguide][google-naming-github] repository. The other reasons we decided upon the Google style guide was due to the ease of which each standard could be understood. You can see below a simplified wording of this naming convention which the team have agreed to follow.


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
| Local variable names        | Even when final and immutable (but non-Static) , local variables are not considered to be constants, and should not be styled as constants.       |  |
| Local variables        | A local variable in Java is a variable that’s declared within the body of a method.        |  |

## Git Strategy

The purpose of a Git strategy is to make all Developers aware of the process that code changes should go through before they are committed to the `master` branch. When using a well-defined Git Strategy, no code should be able to enter the `master` branch without first passing all tests and in some cases be manually reviewed. The purpose of the strategy is to ensure broken code is not in the master branch.  

One of the first Git Strategies to be adopted by the Developer Community was Vincent Driessen's "Git Flow" which was described in "[A successful Git branching model][successful-git]". Out of familiarity, the team would have used this strategy, however a recent addition to the blog post in March 2020 (10 years after it's original release) suggests that GitFlow is not the best solution for applications that don't need to be "explicitly versioned". He also specifically points out "Web Apps" as "not the class of software that I had in mind when I wrote the blog post" and instead suggests a "much simpler workflow".  

The workflow that he suggests as an alternative is "[Github Flow][github-flow]" which is a much simpler workflow to follow. [GitHub Flow][github-flow] suggests that the `master` branch should always be deployable meaning it always builds and all tests pass. The strategy also suggests that branches should be branched off of master for any feature or fix. This is the point at which we would need to adapt the GitHub Flow branching model to meet our needs better. We instead would need a branch called Dev the is branched off of master which all our feature/fix branches branch from. This is due to the fact that the way we are splitting up development between our team means that 1 developer's code might be dependent on the code of another developer. In an ideal situation, each developer would work on a stand-alone piece of the project. This may work in the future once the core of the project is built however, to begin with, there will be dependencies between developers. In order to resolve this our dev branch will be the equivalent to the master branch in "GitHub Flow" without the requirement that the "branch is always deployable".  

If this project was to be a Production application, then we would use the dev branch for the initial development of the application however, once the initial application was built, we would then use true [Github Flow][github-flow] for any future features or fixes.  

### Branching, Pull Requests and Approvals

Our adapted GitHub Strategy workflow for features/fixes can be seen below:
1. **Create a Branch** - Use a descriptive name for the branch - it should describe what the fix/feature does
2. **Add Commits** - Work on your code in the branch - making sure to use descriptive commit messages
3. **Open a Pull Request** - Make sure to describe the solution provided by the branch in the Pull Request description
4. **Discuss and review your code** - For a branch to be committed we have decided that at least 1 other developer in the team has to review the code changes before merging and all automated tests will have to pass.
5. **Deploy** - We also have decided that 1 developer should be able to successfully build the code, run all tests successfully as well as test the feature/bug fix that has been implemented.
6. **Merge** - Once steps 4 & 5 have been complete, the code is ready to be merged into dev. Once merged, the original issue (if created) can now also be closed.


### Ci Pipeline

In order to assist with the testing of new code an automated workflow has been setup using GitHub Actions in order to automatically build the code with Maven which in turn automatically runs all unit tests in from the codebase. This is why it is important for us in order to write tests as we write our code so we can identify any issues in the codebase as they arise before their associated pull request is even merged.  

The workflow is setup in order to execute on code pushes to all branches as well as any pull requests to dev or master. The benefits of this CI (Continuous Integration) are:
- All Code is validated that it can build before it is merged into our main working branch (dev) and our production branch (master). This means that no code with syntaxial errors should ever enter our working branch. This means that developers should never have to spend time debugging another developer’s code just so they can test their own code.

- All Tests are run on all code for all new pull requests to dev. Due to the fact that within this project code should only enter the dev branch through a pull request - we are able to only accept code where all tests pass. In addition to this we have stated that all developers are responsible for writing tests for their own code, meaning that all pull requests would automatically test every developer’s code. These automated tests should catch a large majority of the cases where a new feature/fix accidently breaks another developer’s code.


In a 2016 paper named "[Usage, Costs, and Benefits of Continuous Integration in Open-Source Projects][ci-usage]" where over 34,500 open source repositories and over 1,500,000 automated builds were studied, it was found that, within Open Source Projects, when a CI pipeline is used the rate of releases was "double the release rate" of a project not using CI pipelines. However, it was also found that projects with CI pipelines are 5% less likely to accept pull requests. The explanation given in the paper is due to the fact that with CI all pull requests provide build information, meaning code that doesn't pass all tests won't be merged; whereas in a project with no CI it is more likely for code that doesn't pass tests to still be merged. The paper also concludes that pull requests on projects that use CI are usually merged over 25% faster than those without CI pipelines.

From this paper, we can conclude that by using a CI pipeline in our code we can expect:
- A greater release rater of features and fixes
- Quicker identification of bugs
- Faster time for code to get from feature/fix branches to our shared codebase


## Agile Methodology

Order to provide process to the development work we were doing; it was decided as a team that we should implement a software development lifecycle (SDLC) in order to manage the development work that we were completing as a team. We decided that in order to improve collaboration and communication within the development team we would follow an Agile development lifecycle. The improved collaboration this provided would be critical due to the fact that as a team we would be completing development work individually at different times of the day/week.

We decided upon Agile after reading a Whitepaper from "Alon Koch" (the Course Director of "Global Knowledge") named "[12 Advantages of Agile Software Development][agile-advantages]". Within this paper the main benefits that would relate most to a project of this size were a "Productive Development Team", "Good Quality Software" as well as "On Time" delivery of software.

According to whitepaper, these 3 benefits were to be achieved through:
- Short iterations of development with a "milestone" at the end of each iteration
- An improved "focus on testing" and practices such as "coding standards" and "peer reviews" 

In order to gain these benefits within our development process we have decided to implement a simplified version of a "full-time" Agile framework. The reason we have not implemented a full framework is due to the fact that we are working only a small part of our work week on this project meaning that we want to be able to maximise the development time spent on the project. If we tried to implement a full framework then the majority of our time spent on the project would be working on the Agile process rather than the actual work.  

In order to get the best balance between our framework and our development we have decided to adapt a version of [Scrum][agile-scrum]. As described by Atlassian, there are 6 events that take part in a scrum cycle. However, we are able to condense these into the following 4 stages:

- Sprint Planning
- Daily Scrum
- Sprint Review
- Sprint Retrospective

In order to meet each of these 4 stages we have setup the following:

- A sprint length of 1 week
- Weekly Sprint meetings where we can have our planning, review and sprint retrospective.
- A GitHub Projects board in order to keep track of our backlog and work to-do
- Teams channel for daily updates

We will have a sprint length of 1 week starting on a Wednesday with out Sprint Planning meeting. During this meeting we will organise our backlog and assign tasks to all developers for that week. We will then work Wednesday-Tuesday on our assigned tasks. During this sprint we don't have time for daily standup meetings as we will only be working on the project for a couple of hours. Instead, we'll use our group's communication channel as a way to discuss status updates and any blockers anyone might have.  

Usually in a Scrum you review the work that has been done at the end of every sprint as part of the "Sprint Review", however for us the review of pull requests counts as our sprint review. This is due to the fact that most pull requests should be ready to close at the end of each sprint anyway. During these pull request review we'll check the code of other developers together to check for standard adherence, code quality as well as the fact that the feature or fix does as it was intended to.

Finally, we come back to our Wednesday meetings where we will start with our Sprint Retrospective - a time where we can reflect on the previous sprint and find areas in our process that could be improved for the next sprint.

### Task Tracking

In order to keep track of milestones and deadlines we have leveraged the "Project" tool that is provided by GitHub. It provides a Kanban style board for use in your project in order to keep track of tasks and task states. For our project we have the following columns:
- Backlog - All tasks start in the backlog, this is our "to-do" list of all tasks that have not yet been started.
- In Progress - These are tasks that have been assigned for the sprint that week.
- Pending Review - These are tasks that the developer has completed but need to be reviewed by another member of the team.
- Done - These are tasks that have been both completed by the developer as well as reviewed by another team member

Each week during our planning meeting we will update the state of all tasks, add any additional tasks to the backlog and then assign tasks for the next sprint.

Due to the fact that we have a short timeline for this project our task board is really important in visualising how much work is left to complete as well as the progress of work during a week. It will allow us to see our "burndown rate" - how much work we are going through per week and will also allow us to speed up development if we are falling behind our deadline.


[oracle-naming]: https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html "Oracle Naming Conventions"
[google-naming]: https://google.github.io/styleguide/javaguide.html#s5-naming "Google Naming Conventions"
[google-naming-github]: https://github.com/google/styleguide "Google Styleguide Github"
[successful-git]: https://nvie.com/posts/a-successful-git-branching-model/ "GitFlow"
[github-flow]: https://guides.github.com/introduction/flow/ "GitHub Flow"
[ci-usage]: http://cope.eecs.oregonstate.edu/papers/OpenSourceCIUsage.pdf "Open Source CI Usage"
[agile-advantages]: https://cs.anu.edu.au/courses/comp3120/public_docs/WP_PM_AdvantagesofAgile.pdf "Advantages of Agile"
[agile-scrum]: https://www.atlassian.com/agile/scrum "Scrum"
