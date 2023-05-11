package exceptions;

public class ParenthesesCountError extends Exception{
    public ParenthesesCountError() {
        super("Parantheses count error");
    }
    
    public ParenthesesCountError(String message) {
        super(message);
    }
}
