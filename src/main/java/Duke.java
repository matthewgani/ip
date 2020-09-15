import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        runDuke();
    }

    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printDividerLine();
        System.out.println("Hello! I'm Duke! \n " + logo);
        System.out.println("What can I do for you?");
        printDividerLine();

    }

    public static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }

    public static void printGoodbye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printDividerLine();
    }

    public static ArrayList<Task> loadDukeMemory() {
        printDividerLine();
        ArrayList<Task> taskList = FileHelper.retrieveData();
        TaskHelper.printTaskNumberMessage();
        return taskList;
    }

    public static void runDuke(){
        Scanner userResponseScanner = new Scanner(System.in);
        String command;
        boolean quitResponseLoop = false;
        ArrayList<Task> taskList = loadDukeMemory();
        printWelcome();

        while (!quitResponseLoop) {
            command = userResponseScanner.nextLine().trim();

            if (command.equals("bye")) {
                quitResponseLoop = true;
            } else if (command.equals("list")) {
                TaskHelper.printTaskList(taskList);
            } else if (command.startsWith("done")) {
                try {
                    int taskNumber = TaskHelper.getTaskNumberFromCommand(command);
                    TaskHelper.setTaskAsDone(taskNumber, taskList);
                } catch (NumberFormatException e) {
                    TaskHelper.printTaskNumberParseError();
                } catch (IndexOutOfBoundsException e) {
                    TaskHelper.printTaskNumberNotFound();
                }
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                try {
                    String[] taskCommand = TaskHelper.splitTaskCommand(command);
                    TaskHelper.addTaskToList(taskCommand, taskList, false);
                } catch (DukeException e) {
                    TaskHelper.printExceptionMessage(e.toString());
                }
            } else if (command.startsWith("delete")) {
                try {
                    int taskNumber = TaskHelper.getTaskNumberFromCommand(command);
                    TaskHelper.deleteTask(taskNumber, taskList);
                } catch (NumberFormatException e) {
                    TaskHelper.printTaskNumberParseError();
                } catch (IndexOutOfBoundsException e) {
                    TaskHelper.printTaskNumberNotFound();
                }
            } else {
                TaskHelper.printInvalidCommand();
            }
        }

        FileHelper.convertTaskListToFile("data/dukeMemory.txt", taskList);
        printGoodbye();

    }

}
