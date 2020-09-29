package duke.exception;

public class MissingInformationException extends DukeException {

    private String exceptionMessage;


    public MissingInformationException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }


    @Override
    public String toString() {
        return "Oh no! " + exceptionMessage;
    }

}
