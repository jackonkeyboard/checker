import expression.*;

public class LinearEquation extends Equation {
    public LinearEquation(String eq) {
        super(eq);
    }

    @Override
    public void displaySolution() {
        Expression left = getLeftHandSide();
        Expression rigt = getRightHandSide();
        Expression simpleForm = left.subtract(rigt);

        if(simpleForm instanceof FirstOrderExpression){
            double[] coefficients = simpleForm.getValue();
            double coefficient1 = coefficients[0];
            double coefficient2 = coefficients[1];
            //almost zero
            if(Math.abs(coefficient2) < 2 * Double.MIN_VALUE && Math.abs(coefficient1) < 2 * Double.MIN_VALUE){
                System.out.println("The equation has infinitely many solutions");
            }else if(Math.abs(coefficient2) < 2 * Double.MIN_VALUE && Math.abs(coefficient1) > 2 * Double.MIN_VALUE){
                System.out.println("The equation has no solutions");
            }else if(Math.abs(coefficient2) > 2 * Double.MIN_VALUE && Math.abs(coefficient1) < 2 * Double.MIN_VALUE){
                System.out.println("Solution is: 0");
            }else{
                System.out.print("Solution is: ");
                System.out.println(-(coefficient1/coefficient2));
            }

        }else if(simpleForm instanceof ConstantExpression){
            double value = simpleForm.getValue()[0];
            if(Math.abs(value) > 2 * Double.MIN_VALUE){
                System.out.println("The equation does not hold");
            }else{
                System.out.println("The equation holds");
            }
        }
    }

    public static void main(String[] args) {
        LinearEquation a = new LinearEquation(args[0]);
        a.displaySolution();
    }
}
