import java.util.*;

public class LinearEquation extends Equation {
    private double a;
    private double b;

    public LinearEquation(String eq) {
        super(eq);
    }

    @Override
    protected Stack<String> parseImp(String equation) {
        // Simplified parsing logic, assuming a properly formatted equation
        Stack<String> eqStack = new Stack<>();
        String[] parts = equation.split(" ");
        Collections.addAll(eqStack, parts);
        a = Double.parseDouble(parts[0].split("")[0]);
        b = Double.parseDouble(parts[2]);
        return eqStack;
    }

    @Override
    public String display() {
        return a + "x + " + b + " = 0";
    }

    @Override
    public boolean isSolvable() {
        return a != 0;
    }

    @Override
    public ArrayList<Double> solve() {
        ArrayList<Double> solutions = new ArrayList<>();
        if (isSolvable()) {
            solutions.add(-b / a);
        }
        return solutions;
    }

    public static void main(String[] args) {
        LinearEquation a = new LinearEquation("7x + 9 = 0");
        System.out.println(a.display());
        System.out.println(a.isSolvable());
        System.out.println(a.solve().get(0));
    }
}
