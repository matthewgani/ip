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
    private Ui ui;

    public Storage() {
        ui = new Ui();
    }

    public void loadDukeMemory(String pathname, TaskList tasks) {
        ui.printDividerLine();
        tasks.setIsLoadingFromFile(true);
        retrieveData(pathname, tasks);
        ui.printTaskNumberMessage();
        tasks.setIsLoadingFromFile(false);
    }

    public void retrieveData(String pathname, TaskList tasks) {
        ArrayList<String> fileData = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        manageDataDirectory();
        manageDukeMemory();

        try {
            File f = new File(pathname);
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                fileData.add(fileScanner.nextLine());
            }
            convertFileToTaskList(fileData, tasks);

        } catch (IOException e) {
            ui.printFileScannerIOError();
            tasks.resetTaskList(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printReadError();
            tasks.resetTaskList(taskList);
        } catch (DukeException e) {
            ui.printExceptionMessage(e.toString());
            tasks.resetTaskList(taskList);
        }

    }

    public void manageDataDirectory() {
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

    public void manageDukeMemory() {
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

    public void saveDukeMemory(String filePath, TaskList tasks) {
        try {
            ArrayList<Task> taskList = tasks.getTaskList();
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(formatTaskForFileWrite(task));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception when creating file from taskList");
        } catch(IllegalStateException e) {
            System.out.println("Error while writing to file!");
        }

    }

    public String formatTaskForFileWrite(Task currentTask) {
        String fileWriteFormat;
        Boolean taskDoneStatus = currentTask.getDoneStatus();

        switch (currentTask.getTaskType()) {
        case "todo" :
            fileWriteFormat = "todo" + " | " + taskDoneStatus + " | " + currentTask.getDescription();
            break;
        case "deadline":
            Deadline currentDeadline = (Deadline) currentTask;
            fileWriteFormat = "deadline" + " | " + taskDoneStatus + " | " + currentTask.getDescription() + " | " + currentDeadline.getBy();
            break;
        case "event":
            Event currentEvent = (Event) currentTask;
            fileWriteFormat = "event" + " | " + taskDoneStatus + " | " + currentTask.getDescription() + " | " + currentEvent.getAt();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + currentTask.getTaskType());
        }

        return fileWriteFormat;

    }


    public void convertFileToTaskList(ArrayList<String> fileData, TaskList tasks) throws ArrayIndexOutOfBoundsException, CorruptedFileException {
        for (String taskData : fileData) {
            String[] taskDetails = new String[5];
            String[] splitTaskData = taskData.split("\\|");
            String taskType = splitTaskData[0].trim();

            if(!splitTaskData[1].trim().equals("true") && !splitTaskData[1].trim().equals("false")) {
                throw new CorruptedFileException("Unable to load the file as it was corrupted!");
            }

            boolean taskDoneStatus = Boolean.parseBoolean(splitTaskData[1].trim());

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
                tasks.addTaskToList(taskDetails);
                if (taskDoneStatus) {
                    tasks.getTaskList().get(Task.getNumberOfTasks() - 1).markAsDone();
                }
            } catch (DukeException e) {
                ui.printExceptionMessage(e.toString());
            }
        }
    }


}
