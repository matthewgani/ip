public class Ui {


    public void printFileScannerIOError() {
        printDividerLine();
        System.out.println("Unable to find dukeMemory file!");
        printDividerLine();
    }

    public void printReadError() {
        printDividerLine();
        System.out.println("Problems with reading file!");
        printDividerLine();
    }

    public void printInvalidCommand() {
        printDividerLine();
        System.out.println("Sorry, I could not understand your command!");
        printDividerLine();
    }

    public void printTaskNumberParseError() {
        printDividerLine();
        System.out.println("Unable to obtain task number!");
        printDividerLine();
    }

    public void printTaskNumberNotFound() {
        printDividerLine();
        System.out.println("No task number detected!");
        printDividerLine();
    }

    public void printTaskNumberMessage() {
        System.out.println("Now you have " + Task.getNumberOfTasks() + " task(s) in your list!");
    }

    public void printExceptionMessage(String exceptionMessage) {
        printDividerLine();
        System.out.println(exceptionMessage);
        printDividerLine();
    }

    public void printDividerLine() {
        System.out.println("___________________________________ \n");
    }

    public void printWelcome() {
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

    public void printGoodbye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printDividerLine();
    }

    public void printKeywordMissingError() {
        printDividerLine();
        System.out.println("There was no keyword input after the find command!");
        printDividerLine();
    }
}
