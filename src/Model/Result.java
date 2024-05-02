package Model;

public class Result {
    private final boolean successful;
    private final String message;

    public Result(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }
}
