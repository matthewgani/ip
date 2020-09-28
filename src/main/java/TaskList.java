import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Boolean isLoadingFromFile;
    Ui ui;

    public TaskList () {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Sets if the tasks are being loaded from the txt file.
     *
     * @param booleanValue The boolean value to set or clear isLoadingFromFile.
     */
    public void setIsLoadingFromFile(Boolean booleanValue) {
        isLoadingFromFile = booleanValue;
    }

    /**
     * Returns the current ArrayList of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a Todo type of task into the taskList
     *
     * @param taskDetails String input including Todo Description.
     */
    public void addTodo(String taskDetails) {
        Task newTodo = new Todo(taskDetails);
        taskList.add(newTodo);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this todo: ");
            System.out.println(newTodo);
            ui.printDividerLine();
        }
    }

    /**
     * Adds a Deadline type of task into the taskList
     *
     * @param taskDetails String input including Deadline description and by.
     * @throws MissingInformationException If the user did not enter '/by' in a deadline command.
     */
    public void addDeadline(String taskDetails) throws MissingInformationException {
        String[] deadlineDetails = taskDetails.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new MissingInformationException("The deadline 'by' date/time was undetected!");
        }
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(newDeadline);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this deadline: ");
            System.out.println(newDeadline);
            ui.printDividerLine();
        }
    }

    /**
     * Adds a Event type of task into the taskList
     *
     * @param taskDetails String input including Event description and at.
     * @throws MissingInformationException If the user did not enter '/at' in an event command.
     */
    public void addEvent(String taskDetails) throws MissingInformationException{
        String[] eventDetails = taskDetails.split("/at", 2);
        if (eventDetails.length < 2) {
            throw new MissingInformationException("The event 'at' date/time was undetected!");
        }
        String eventDescription = eventDetails[0].trim();
        String eventAt = eventDetails[1].trim();
        Task newEvent = new Event(eventDescription, eventAt);
        taskList.add(newEvent);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this event: ");
            System.out.println(newEvent);
            ui.printDividerLine();
        }
    }

    /**
     * Parses what type of task is added and calls the respective functions
     *
     * @param taskCommand An array string that includes the task name and its details like description/by/at.
     * @throws MissingInformationException If there is no description for the tasks to be added.
     */
    public void addTaskToList(String[] taskCommand) throws MissingInformationException {
        if (taskCommand.length < 2) {
            throw new MissingInformationException("The details of a " + taskCommand[0] + " cannot be empty!");
        }
        String taskType = taskCommand[0];
        String taskDetails = taskCommand[1];
        switch (taskType) {
        case "todo":
            addTodo(taskDetails);
            break;
        case "deadline":
            try {
                addDeadline(taskDetails);
            } catch (MissingInformationException e) {
                ui.printExceptionMessage(e.toString());
            }
            break;
        case "event":
            try {
                addEvent(taskDetails);
            } catch (MissingInformationException e) {
                ui.printExceptionMessage(e.toString());
            }
            break;
        }
        if (!isLoadingFromFile) {
            ui.printTaskNumberMessage();
            ui.printDividerLine();
        }

    }

    /**
     * Sets a task as done.
     *
     * @param taskNumber Task number to be set as done.
     */
    public void setTaskAsDone(int taskNumber) {
        ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to mark as done!");
        }
        ui.printDividerLine();

    }

    /**
     * Deletes a task from the taskList.
     *
     * @param taskNumber Task number to be deleted.
     */
    public void deleteTask(int taskNumber) {
        ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            Task.decreaseNumberOfTasks();
            System.out.println("Got it! I've deleted this task:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to delete!");
        }
        ui.printTaskNumberMessage();
        ui.printDividerLine();
    }

    /**
     * Resets the taskList into an empty ArrayList and resets number ot tasks.
     */
    public void resetTaskList() {
        taskList.clear();
        Task.resetNumberOfTasks();
    }

    public void findTasks(String keyword) {
        int taskCount;
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        ui.printDividerLine();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTaskList.add(task);
            }
        }
        if(matchingTaskList.size() > 0) {
            System.out.println("Here are the matching tasks in your list: ");
            for (taskCount = 0; taskCount < matchingTaskList.size(); taskCount++) {
                System.out.println(taskCount + 1 + ". " + matchingTaskList.get(taskCount));
            }
        } else {
            System.out.println("There are no tasks that match to '" + keyword + "' in your list!");
        }
        ui.printDividerLine();
    }

}
