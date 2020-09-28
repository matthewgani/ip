public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        new Duke("data/dukeMemory.txt").run();
    }

    public Duke(String filepath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser(tasks);
        storage.loadDukeMemory(filepath, tasks);
    }

    public void run(){
        boolean isQuittingLoop = false;
        ui.printWelcome();
        while (!isQuittingLoop) {
            isQuittingLoop = parser.getUserCommand();
        }
        storage.saveDukeMemory("data/dukeMemory.txt", tasks);
        ui.printGoodbye();
    }

}
