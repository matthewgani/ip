import java.util.Scanner;

public class Parser {

    public static int getTaskNumberFromCommand(String command) {
        int taskNumber;
        String[] splitCommand = command.split(" ", 2);
        taskNumber = Integer.parseInt(splitCommand[1]);
        return taskNumber;
    }

    public static String[] splitTaskCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }

    public static Boolean getUserCommand() {
        Scanner userResponseScanner = new Scanner(System.in);
        String command = userResponseScanner.nextLine().trim();

        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")) {
            TaskList.printTaskList();
        } else if (command.startsWith("done")) {
            try {
                int taskNumber = Parser.getTaskNumberFromCommand(command);
                TaskList.setTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                Ui.printTaskNumberParseError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printTaskNumberNotFound();
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            try {
                String[] taskCommand = Parser.splitTaskCommand(command);
                TaskList.addTaskToList(taskCommand);
            } catch (DukeException e) {
                Ui.printExceptionMessage(e.toString());
            }
        } else if (command.startsWith("delete")) {
            try {
                int taskNumber = Parser.getTaskNumberFromCommand(command);
                TaskList.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                Ui.printTaskNumberParseError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printTaskNumberNotFound();
            }
        } else {
            Ui.printInvalidCommand();
        }
        return false;
    }
}
