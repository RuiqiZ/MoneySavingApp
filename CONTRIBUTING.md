# Contributing guidelines

* `master` is our final product branch. **NOTHING** should be directly added to it without a merge request
* `development` serves as our collective working branch. For the most part it should be a completely working version of our application
* Each individual should work on their own seperate branches
* When an implementation is complete or is at a point that it must be added to `development` a **merge** request must be issued

**NO DIRECTLY COMMITING TO DEVELOPMENT (unless for small problems, such as spelling)**

**ALL CODE COMMITED TO DEVELOPMENT MUST WORK AND INCLUDE TESTS**

# Branch naming convensions

* New branches should beging with your **name** and include the **task** that branch accomplishes

```
aaron-home-activity
aaron-password-saver
```

# Commit messages

* Commit messages must be written in present tense
* Both the body and title must use proper grammer
* If more than just a title is required, please add details in the body

```
Added basic set of contibuting guidelines

additional details go here
```

# Merge request guidelines

* The title must describe the goal of the merge request and summarize the changes made
* The body of the merge request must describe the changes made in further detail (if needed)
* The body of the merge request must list the issues that the merge request will close (if applicable)

* When making a merge request, the link to the request **MUST** be sent to the slack #dev channel so everyone is made aware of it

**ADDITIONALLY, 2 PEOPLE MUST LOOK OVER A MERGE REQUEST BEFORE IT GETS MERGED**

```
Title: Start Android Studio project

Body:

- Create a basic project with an empty activity name "MainActivity". This will serve as our project's starting point

Closes issues: #12
```

