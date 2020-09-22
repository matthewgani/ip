import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        runDuke();
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        /*try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }

    public static void runDuke(){
        boolean isQuittingLoop = false;
        TaskList taskList = new TaskList();
        Storage.loadDukeMemory();
        //ui = new Ui();
        Ui.printWelcome();

        while (!isQuittingLoop) {
            isQuittingLoop = Parser.getUserCommand();
        }

        Storage.saveDukeMemory("data/dukeMemory.txt");
        Ui.printGoodbye();

    }

}
