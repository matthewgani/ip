import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        ui = new Ui();
    }

    public int getTaskNumberFromCommand(String command) {
        int taskNumber;
        String[] splitCommand = command.split(" ", 2);
        taskNumber = Integer.parseInt(splitCommand[1]);
        return taskNumber;
    }

    public String[] splitTaskCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }

    public Boolean getUserCommand() {
        Scanner userResponseScanner = new Scanner(System.in);
        String command = userResponseScanner.nextLine().trim();

        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")) {
            taskList.printTaskList();
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
                String[] taskCommands = splitTaskCommand(command);
                taskList.addTaskToList(taskCommands);
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
        } else if (command.startsWith("find")) {
            try {
                String[] taskCommands = splitTaskCommand(command);
                taskList.findTasks(taskCommands[1]);
            } catch (IndexOutOfBoundsException e) {
                ui.printKeywordMissingError();
            }
        } else {
            ui.printInvalidCommand();
        }
        return false;
    }
}
