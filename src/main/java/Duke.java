import java.util.Scanner;

public class Duke {


    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printDividerLine();
        System.out.println("Hello! I'm Duke \n " + logo);
        System.out.println("What can I do for you?");
        printDividerLine();


    }
    public static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }
    public static void printGoodbye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }
    public static void main(String[] args) {
        Scanner userResponseScanner = new Scanner(System.in);
        boolean quitResponseLoop = false;
        printWelcome();
        String userResponse;
        while(!quitResponseLoop) {
            userResponse = userResponseScanner.nextLine();
            if(!userResponse.equals("bye")) {
                printDividerLine();
                System.out.println(userResponse);
                printDividerLine();

            }
            else {
                printGoodbye();
                quitResponseLoop = true;
            }

        }






    }
}
