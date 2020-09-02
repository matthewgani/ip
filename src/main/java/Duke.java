import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


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


    public static void main(String[] args) {
        Scanner userResponseScanner = new Scanner(System.in);
        boolean quitResponseLoop = false;
        //String userResponse;
        String command;
        String commandDetails = null;
        ArrayList<Task> taskList = new ArrayList<>();

        printWelcome();

        while (!quitResponseLoop) {
            command = userResponseScanner.nextLine().trim();

            if(command.contains(" ")) {
                String[] splitUserResponse = command.split(" ", 2);
                command = splitUserResponse[0];
                commandDetails = splitUserResponse[1];
            }

            if (command.equals("bye")) {
                printGoodbye();
                quitResponseLoop = true;

            } else if (command.equals("list")) {
                TaskHelper.printTaskList(taskList);

            } else if (command.equals("done")) {
                if (commandDetails != null) {
                    int taskNumber = Integer.parseInt(commandDetails);
                    // Undone: Check for error when parseInt

                    TaskHelper.setTaskAsDone(taskNumber, taskList);
                } else {
                    TaskHelper.printDoneErrorMessage();
                }
            } else {
                String taskDetails = commandDetails;
                TaskHelper.addTaskToList(command, taskDetails, taskList);

            }

        }

    }
}
