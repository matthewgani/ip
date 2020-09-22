import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void retrieveData() {
        ArrayList<String> fileData = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        manageDataDirectory();
        manageDukeMemory();

        try {
            File f = new File("data/dukeMemory.txt");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                fileData.add(fileScanner.nextLine());
            }
            convertFileToTaskList(fileData);

        } catch (IOException e) {
            Ui.printFileScannerIOError();
            TaskList.resetTaskList(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printReadError();
            TaskList.resetTaskList(taskList);
        } catch (DukeException e) {
            Ui.printExceptionMessage(e.toString());
            TaskList.resetTaskList(taskList);
        }

    }

    public static void manageDataDirectory() {
        try {
            Path directoryPath = Paths.get("data");
            Files.createDirectory(directoryPath);
            System.out.println("data directory was created!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("data Directory exists and is being loaded!");
        } catch (IOException e) {
            System.out.println("IO exception while creating directory!");
        }
    }

    public static void manageDukeMemory() {
        try {
            Path dukeMemoryPath = Paths.get("data/dukeMemory.txt");
            Files.createFile(dukeMemoryPath);
            System.out.println("dukeMemory file was created!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("dukeMemory file exists and is being loaded!");
        } catch (IOException e) {
            System.out.println("IO exception when creating file!");
        }
    }

    public static void saveDukeMemory(String filePath) {
        try {
            ArrayList<Task> taskList = TaskList.getTaskList();
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(formatTaskForFileWrite(task));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception when creating file from taskList");
        }

    }

    public static String formatTaskForFileWrite(Task currentTask) {
        String fileWriteFormat = new String();
        Boolean taskDoneStatus = currentTask.getDoneStatus();
        String doneStatusString;
        if (taskDoneStatus) {
            doneStatusString = "1";
        } else {
            doneStatusString = "0";
        }

        switch (currentTask.getTaskType()) {
        case "todo" :
            fileWriteFormat = "todo" + " | " + doneStatusString + " | " + currentTask.getDescription();
            break;
        case "deadline":
            Deadline currentDeadline = (Deadline) currentTask;
            fileWriteFormat = "deadline" + " | " + doneStatusString + " | " + currentTask.getDescription() + " | " + currentDeadline.getBy();
            break;
        case "event":
            Event currentEvent = (Event) currentTask;
            fileWriteFormat = "event" + " | " + doneStatusString + " | " + currentTask.getDescription() + " | " + currentEvent.getAt();
            break;
        }

        return fileWriteFormat;

    }


    public static void convertFileToTaskList(ArrayList<String> fileData) throws ArrayIndexOutOfBoundsException, CorruptedFileException {
        for (String taskData : fileData) {
            Boolean taskDoneStatus;
            int taskDoneInt;
            String[] taskDetails = new String[5];
            String[] splitTaskData = taskData.split("\\|");
            String taskType = splitTaskData[0].trim();
            taskDoneInt = Integer.parseInt(splitTaskData[1].trim());

            if (taskDoneInt > 1) {
                throw new CorruptedFileException("Unable to load the file as it was corrupted!");
            } else if (taskDoneInt == 1) {
                taskDoneStatus = true;
            } else {
                taskDoneStatus = false;
            }

            switch (taskType) {
            case "todo":
                taskDetails[0] = taskType;
                taskDetails[1] = splitTaskData[2].trim();
                break;
            case "deadline":
                taskDetails[0] = taskType;
                taskDetails[1] = splitTaskData[2].trim() + " /by " + splitTaskData[3].trim();
                break;
            case "event":
                taskDetails[0] = taskType;
                taskDetails[1] = splitTaskData[2].trim() + " /at " + splitTaskData[3].trim();
                break;
            default:
                throw new CorruptedFileException("Unable to load the file as it was corrupted!");
            }

            try {
                TaskList.addTaskToList(taskDetails);
                if (taskDoneStatus) {
                    TaskList.getTaskList().get(Task.getNumberOfTasks() - 1).markAsDone();
                }
            } catch (DukeException e) {
                Ui.printExceptionMessage(e.toString());
            }
        }
    }


    public static void loadDukeMemory() {
        Ui.printDividerLine();
        TaskList.setIsLoadingFromFile(true);
        Storage.retrieveData();
        Ui.printTaskNumberMessage();
        TaskList.setIsLoadingFromFile(false);
    }
}
