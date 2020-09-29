# Duke User Guide
Duke is a command line app that allows you to manage and save your tasks quickly and simply!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello! I'm Duke!
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
## Using Duke on Command Line
1. Navigate to where you downloaded the JAR file from Github.
2. Type 'chcp 65001'
3. Type 'java -Dfile.encoding=UTF-8 -jar IP.jar'
4. If ticks and crosses are shown as a question mark, right click the top bar of the command line and go to 'properties' and then 'font'. Change the font to a supported font like NSimSun.

# Features 

## 1. Todo
Adds a todo type of event into the task list.

Format: `todo [description]`

Example:

Input: `todo go for a run`

Output:
```
___________________________________ 

Got it! I've added this todo: 
[T][✘] go for a run
___________________________________ 

Now you have 1 task(s) in your list!
___________________________________ 
```

## 2. Event
Adds an event type of task into the task list.

Format: `event [description] /at [timing/date]`

Example:

Input: `event party at home /at tuesday 4pm`

Output:
```
___________________________________ 

Got it! I've added this event: 
[E][✘] party at home (at: tuesday 4pm)
___________________________________ 

Now you have 2 task(s) in your list!
___________________________________ 
```

## 3. Deadline
Adds a deadline type of task into the task list.

Format: `deadline [description] /by [timing/date]`

Example:

Input: `deadline complete final report /by Saturday 3rd October`

Output:
```
___________________________________ 

Got it! I've added this deadline: 
[D][✘] complete final report (by: Saturday 3rd October)
___________________________________ 

Now you have 3 task(s) in your list!
___________________________________ 
```

## 4. Done
Sets a task to be done.

Format: `done [task number]`

Example:

Input: `done 1`

Output: 
````
___________________________________ 

Nice! I've marked this task as done:
[T][✓] go for a run
___________________________________ 
````

## 5. List
Lists out all tasks in the current task list.

Format: `list`

Output: 
````
___________________________________ 

Here are the tasks in your list:
1. [T][✓] go for a run
2. [E][✘] party at home (at: tuesday 4pm)
3. [D][✘] complete final report (by: Saturday 3rd October)
___________________________________ 
````

## 6. Delete
Deletes a task from the task list.

Format: `delete [task number]`

Example: 

Input: `delete 2`

Output: 
````
___________________________________ 

Got it! I've deleted this task:
[E][✘] party at home (at: tuesday 4pm)
Now you have 2 task(s) in your list!
___________________________________ 
````

## 7. Find
Finds a keyword in the description of tasks in the task list.

Format: `find [keyword]`

Example: 

Input: `find report`

Output: 
````
___________________________________ 

Here are the matching tasks in your list: 
1. [D][✘] complete final report (by: Saturday 3rd October)
___________________________________ 
````

## 8. Bye
Exits the program.

Format: `bye`

Output: 
````
___________________________________ 

Bye. Hope to see you again soon! :)
___________________________________ 
````

# Summary of Commands

Command | Purpose | Format
-------|---------|-------------
todo | Adds a todo task | `todo [description]`
event | Adds an event task | `event [description] /at [timing/date]`
deadline | Adds a deadline task | `deadline [description] /by [timing/date]`
done | Sets a task as done | `done [task number]`
list | Prints the task list | `list`
delete | Deletes a task | `delete [task number]`
find | Finds a keyword in the task list | `find [keyword]`
bye | Exits program | `bye`

