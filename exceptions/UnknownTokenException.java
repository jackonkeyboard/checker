package exceptions;

public class UnknownTokenException extends Exception {
    public UnknownTokenException() {
        super("Unknown Token found");
    }
    
    public UnknownTokenException(String message) {
        super(message);
    }
}
