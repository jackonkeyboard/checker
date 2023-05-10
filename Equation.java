import expression.*;

public abstract class Equation {
    private Expression leftHandSide;
    private Expression rightHandSide;

    public Equation(String eq) {
        String[] eqSplitted = eq.split("=");
        leftHandSide = (new ExpressionConstructor(eqSplitted[0])).evaluate();
        rightHandSide = (new ExpressionConstructor(eqSplitted[1])).evaluate();
    }

    protected Expression getLeftHandSide(){
        return leftHandSide;
    }

    protected Expression getRightHandSide(){
        return rightHandSide;
    }

    public abstract void displaySolution();
}