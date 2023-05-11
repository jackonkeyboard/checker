import expression.*;

public class LinearEquation extends Equation {
    public LinearEquation(String eq) {
        super(eq);
    }

    public Solution solve() {
        Expression left = getLeftHandSide();
        Expression rigt = getRightHandSide();
        Expression simpleForm = left.subtract(rigt);

        if (simpleForm instanceof FirstOrderExpression) {
            double[] coefficients = simpleForm.getValue();
            double coefficient1 = coefficients[0];
            double coefficient2 = coefficients[1];

            // almost zero
            if (Math.abs(coefficient2) < 2 * Double.MIN_VALUE && Math.abs(coefficient1) < 2 * Double.MIN_VALUE) {
                return new Solution(Solution.Type.INFINITELY_SOLUTIONS, 0);
            } else if (Math.abs(coefficient2) < 2 * Double.MIN_VALUE && Math.abs(coefficient1) > 2 * Double.MIN_VALUE) {
                return new Solution(Solution.Type.NO_SOLUTION, 0);
            } else if (Math.abs(coefficient2) > 2 * Double.MIN_VALUE && Math.abs(coefficient1) < 2 * Double.MIN_VALUE) {
                return new Solution(Solution.Type.ONE_SOLUTION, 0);
            } else {
                return new Solution(Solution.Type.ONE_SOLUTION, -(coefficient1 / coefficient2));
            }
        } else { // case when simpleForm is an instanceof ConstantExpression
            double value = simpleForm.getValue()[0];
            if (Math.abs(value) > 2 * Double.MIN_VALUE) {
                return new Solution(Solution.Type.NOT_HOLDING, 1);
            } else {
                return new Solution(Solution.Type.HOLDING, 1);
            }
        }
    }

    public static void main(String[] args) {
        LinearEquation a = new LinearEquation(args[0]);
        Solution s = a.solve();
        s.print();
    }
}
