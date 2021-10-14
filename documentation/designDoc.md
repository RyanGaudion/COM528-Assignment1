# Point of Sales - Design Doc & Project Plan

## Task Assignment
Just split up the tasks in the Project and assign them below - for documentation

Also add development tasks that are unassigned.

Talk about everyone is responsbile for writing tests for their specfic coding work



## Use Cases

### User Stories

## List of Features

### Deliverables



## Project Diagrams

### UML Class Diagrams

### UML Robustness Diagram

### UI Wireframes

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