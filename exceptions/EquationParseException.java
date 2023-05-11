package exceptions;

public class EquationParseException extends Exception {
    public EquationParseException() {
        super("Error while parsing the equation");
    }
    
    public EquationParseException(String message) {
        super(message);
    }
}
