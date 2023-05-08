import java.util.*;

public abstract class Equation {
    private Stack<String> representation;

    public Equation(String eq) {
        representation = parseImp(eq);
    }

    protected abstract Stack<String> parseImp(String equation);

    public abstract String display();
    public abstract boolean isSolvable();
    public abstract ArrayList<Double> solve();
}