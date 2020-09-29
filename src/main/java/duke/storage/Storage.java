package duke.storage;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.exception.CorruptedFileException;
import duke.exception.DukeException;
import duke.ui.Ui;

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

    /**
     * Configures the ArrayList task to set and clear isLoadingFromFile.
     * Calls retrieveData.
     *
     * @param pathname String that has the pathname for the data to be retrieved.
     * @param tasks data.TaskList class used when changing isLoadingFromFile.
     */
    public void loadDukeMemory(String pathname, TaskList tasks) {
        ui.printDividerLine();
        tasks.setIsLoadingFromFile(true);
        retrieveData(pathname, tasks);
        ui.printTaskNumberMessage();
        tasks.setIsLoadingFromFile(false);
    }

    /**
     * Retrieves the data from the text file and updates the task list in TaskList.
     *
     * @param pathname String that has the pathname for the data to be retrieved.
     * @param tasks data.TaskList class that is used to populate the task list in TaskList.
     */
    public void retrieveData(String pathname, TaskList tasks) {
        ArrayList<String> fileData = new ArrayList<>();

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
            tasks.resetTaskList();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printReadError();
            tasks.resetTaskList();
        } catch (DukeException e) {
            ui.printExceptionMessage(e.toString());
            tasks.resetTaskList();
        }

    }

    /**
     * Creates the data directory if it doesn't exist.
     * It prints a loading message if the data directory already exists.
     */
    public void manageDataDirectory() {
        try {
            Path directoryPath = Paths.get("data");
            Files.createDirectory(directoryPath);
            System.out.println("data directory was created!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("data directory exists and is being loaded!");
        } catch (IOException e) {
            System.out.println("IO exception while creating directory!");
        }
    }

    /**
     * Creates the dukeMemory text file if it doesn't exist.
     * It prints a loading message if it exists.
     */
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

    /**
     * Overwrites the dukeMemory text file with the current task list.
     *
     * @param filePath String that has the file path to the text file to write to.
     * @param tasks data.TaskList class used to access the current task list.
     */
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

    /**
     * Formats the 3 different types of tasks into another format while writing to the file.
     *
     * @param currentTask Task class that is being formatted for the dukeMemory text file.
     * @return The String that the task has been converted into.
     */
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

    /**
     * Converts the text file's contents and adds the tasks into the task list.
     *
     * @param fileData Arrays of Strings of each task to be added into the list.
     * @param tasks TaskList class used to add tasks into the task list.
     * @throws ArrayIndexOutOfBoundsException If some tasks has corrupted fields.
     * @throws CorruptedFileException If the task's done status is neither true or false.
     */
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
