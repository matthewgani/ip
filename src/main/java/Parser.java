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
}
