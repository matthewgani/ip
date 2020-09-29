package duke.exception;

public class CorruptedFileException extends DukeException {


    private String exceptionMessage;

    public CorruptedFileException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "Oh no!" + exceptionMessage;
    }


}
