import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        runDuke();
    }

    public static void runDuke(){
        Scanner userResponseScanner = new Scanner(System.in);
        String command;
        boolean isQuittingLoop = false;
        TaskList taskList = new TaskList();
        Storage.loadDukeMemory();
        Ui.printWelcome();

        // move this to 'parser.java'
        while (!isQuittingLoop) {
            command = userResponseScanner.nextLine().trim();

            if (command.equals("bye")) {
                isQuittingLoop = true;
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
        }

        Storage.saveDukeMemory("data/dukeMemory.txt");
        Ui.printGoodbye();

    }

}
