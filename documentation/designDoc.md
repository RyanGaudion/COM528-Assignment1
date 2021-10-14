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


## Coding Standard - RG

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