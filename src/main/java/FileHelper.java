import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {

    public static ArrayList<Task> retrieveData() {
        ArrayList<String> fileData = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Path directoryPath = Paths.get("data");
            Files.createDirectory(directoryPath);
            System.out.println("data directory was created!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("data Directory exists and is being loaded!");
        } catch (IOException e) {
            System.out.println("IO exception while creating directory!");
        }

        try {
            Path dukeMemoryPath = Paths.get("data/dukeMemory.txt");
            Files.createFile(dukeMemoryPath);
            System.out.println("dukeMemory file was created!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("dukeMemory file exists and is being loaded!");
        } catch (IOException e) {
            System.out.println("IO exception when creating file!");
        }

        try {
            File f = new File("data/dukeMemory.txt"); // create a File for the given file path
            Scanner fileScanner = new Scanner(f); // create a Scanner using the File as the source
            while (fileScanner.hasNext()) {
                fileData.add(fileScanner.nextLine());
            }
            taskList = convertFileToTaskList(fileData);

        } catch (IOException e) {
            Duke.printDividerLine();
            System.out.println("Unable to find dukeMemory file!");
            Duke.printDividerLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Duke.printDividerLine();
            System.out.println("Corrupted file");
            Duke.printDividerLine();
        } catch (DukeException e) {
            TaskHelper.printExceptionMessage(e.toString());
        }

        return taskList;

    }

    public static void convertTaskListToFile(String filePath, ArrayList<Task> taskList) {
        try {
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


    public static ArrayList<Task> convertFileToTaskList(ArrayList<String> fileData) throws ArrayIndexOutOfBoundsException, CorruptedFileException {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String taskData : fileData) {
            Boolean taskDoneStatus;
            String[] taskDetails = new String[5];
            String[] splitTaskData = taskData.split("\\|");
            String taskType = splitTaskData[0].trim();
            taskDoneStatus = Integer.parseInt(splitTaskData[1].trim()) == 1;

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
                System.out.println("Corrupted file");
                throw new CorruptedFileException("Unable to load the file as it was corrupted!");
            }

            try {
                TaskHelper.addTaskToList(taskDetails, taskList, true);
                if (taskDoneStatus) {
                    // set the latest task added as done
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            } catch (DukeException e) {
                TaskHelper.printExceptionMessage(e.toString());
            }

        }




        return taskList;
    }


}
