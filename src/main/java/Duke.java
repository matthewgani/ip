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
        String userResponse;
        ArrayList<Task> taskList = new ArrayList<>();

        printWelcome();

        while (!quitResponseLoop) {
            userResponse = userResponseScanner.nextLine().trim();
            String[] splitUserResponse = userResponse.split(" ", 2);
            String command = splitUserResponse[0];

            if (command.equals("bye")) {
                printGoodbye();
                quitResponseLoop = true;

            } else if (command.equals("list")) {
                TaskHelper.printTaskList(taskList);

            } else if (command.equals("done")) {
                int taskNumber = Integer.parseInt(splitUserResponse[1]);
                // Need to check for error when parseInt

                TaskHelper.setTaskAsDone(taskNumber, taskList);
            } else {
                String taskDetails = splitUserResponse[1];
                TaskHelper.addTaskToList(command, taskDetails, taskList);

            }

        }

    }
}
