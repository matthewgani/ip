import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        ui = new Ui();

    }

    /**
     * Returns a task number that is parsed from a command that is supposed to have a task number following it.
     *
     * @param command The full string of command entered by user.
     * @return task number.
     */
    public int getTaskNumberFromCommand(String command) {
        int taskNumber;
        String[] splitCommand = command.split(" ", 2);
        taskNumber = Integer.parseInt(splitCommand[1]);
        return taskNumber;
    }

    /**
     * Returns an array of strings where the command entered i split into 2.
     *
     * @param command The full string of the command entered by the user.
     * @return An array of 2 strings, split after the first space bar.
     */
    public String[] splitTaskCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }

    /**
     * Returns whether or not Duke should continue to accept the User's commands.
     * It is used to make sense of what the user enters and tells Duke which command to do.
     *
     * @return A boolean for whether or not Duke should stop running, true means stop running.
     */
    public Boolean getUserCommand() {
        Scanner userResponseScanner = new Scanner(System.in);
        String command = userResponseScanner.nextLine().trim();

        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")) {
            ui.printTaskList(taskList.getTaskList());
        } else if (command.startsWith("done")) {
            try {
                int taskNumber = getTaskNumberFromCommand(command);
                taskList.setTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                ui.printTaskNumberParseError();
            } catch (IndexOutOfBoundsException e) {
                ui.printTaskNumberNotFound();
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            try {
                String[] taskCommand = splitTaskCommand(command);
                taskList.addTaskToList(taskCommand);
            } catch (DukeException e) {
                ui.printExceptionMessage(e.toString());
            }
        } else if (command.startsWith("delete")) {
            try {
                int taskNumber = getTaskNumberFromCommand(command);
                taskList.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                ui.printTaskNumberParseError();
            } catch (IndexOutOfBoundsException e) {
                ui.printTaskNumberNotFound();
            }
        } else {
            ui.printInvalidCommand();
        }
        return false;
    }
}
