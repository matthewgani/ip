public class Ui {
    public static void printFileScannerIOError() {
        printDividerLine();
        System.out.println("Unable to find dukeMemory file!");
        printDividerLine();
    }

    public static void printReadError() {
        printDividerLine();
        System.out.println("Problems with reading file!");
        printDividerLine();
    }

    public static void printInvalidCommand() {
        printDividerLine();
        System.out.println("Sorry, I could not understand your command!");
        printDividerLine();
    }

    public static void printTaskNumberParseError() {
        printDividerLine();
        System.out.println("Unable to obtain task number!");
        printDividerLine();
    }

    public static void printTaskNumberNotFound() {
        printDividerLine();
        System.out.println("No task number detected!");
        printDividerLine();
    }

    public static void printTaskNumberMessage() {
        System.out.println("Now you have " + Task.getNumberOfTasks() + " task(s) in your list!");
    }

    public static void printExceptionMessage(String exceptionMessage) {
        printDividerLine();
        System.out.println(exceptionMessage);
        printDividerLine();
    }

    public static void printDividerLine() {
        System.out.println("___________________________________ \n");
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

    public static void printGoodbye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printDividerLine();
    }
}
