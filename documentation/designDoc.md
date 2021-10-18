# Point of Sales - Design Doc & Project Plan


## Table of Content


## Use Cases

### User Stories

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

There are however additional testing Tasks that need to be complete outside of the iniital Unit tests per class which you can see below:
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

The purpose of a Git strategy is to make all Developers aware of the process that code changes should go through before they are commited to the `master` branch. When using a well defined Git Strategy, no code should be able to enter the `master` branch without first passing all tests and in some cases be manually reviewed. The purpose of the stategy is to ensure broken code is not in the master branch.  

One of the first Git Strategies to be adopted by the Developer Community was Vincent Driessen's "Git Flow" which was described in "[A successful Git branching model][4]". Out of familiarity, the team would have used this strategy, however a recent addition to the blog post in March 2020 (10 years after it's original release) suggests that GitFlow is not the best solution for applications that don't need to be "explicitly versioned". He also specifically points out "Web Apps" as "not the class of software that I had in mind when I wrote the blog post" and instead suggests a "much simplier workflow".  

The workflow that he suggests as an alternative is "[Github Flow][5]" which is a much simplier workflow to follow. [GitHub Flow][5] suggests that the `master` branch should always be deployable meaning it always builds and all tests pass. The strategy also suggests that branches should be branched off of master for any feature or fix. This is the point at which we would need to adapt the Github Flow branching model to meet our needs better. We instead would need a branch called Dev the is branched off of master which all our feature/fix branches branch from. This is due to the fact that the way we are splitting up development between our team means that 1 developer's code might be dependent on the code of another developer. In an ideal situation, each developer would work on a stand alone piece of the project. This may work in the future once the core of the project is built however, to begin with, there will be dependencies between developers. In order to resolve this our dev branch will be the equivilent to the master branch in "Github Flow" without the requirement that the "branch is always deployable".  

If this project was to be a Production application then we would use the dev branch for the initial development of the application however, once the initial application was built, we would then use true [Github Flow][5] for any future features or fixes.  

Our adapted GitHub Strategy workflow for features/fixes can be seen below:
1. **Create a Branch** - Use a descriptive name for the branch - it should describe what the fix/feature does
2. **Add Commits** - Work on your code in the branch - making sure to use descriptive commit messages
3. **Open a Pull Request** - Make sure to describe the solution provided by the branch in the Pull Request description
4. **Discuss and review your code** - For a branch to be commited we have decided that at least 1 other developer in the team has to review the code changes before merging.
5. **Deploy** - We also have decided that 1 developer should be able to successfully build the code, run all tests successfully as well as test the feature/bug fix that has been implemented.
6. **Merge** - Once steps 4 & 5 have been complete, the code is ready to be merged into dev. Once merged, the original issue (if created) can now also be closed.

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


[1]: "https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html"
[2]: "https://google.github.io/styleguide/javaguide.html#s5-naming"
[3]: "https://github.com/google/styleguide"
[4]: "https://nvie.com/posts/a-successful-git-branching-model/"
[5]: "https://guides.github.com/introduction/flow/"