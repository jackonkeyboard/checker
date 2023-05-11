package expression;

public class FirstOrderExpression extends Expression {
    private double coefficient1;
    private double coefficient2;
    private char symbol;

    public FirstOrderExpression(double value1, double value2, char symbol) {
        this.coefficient1 = value1;
        this.coefficient2 = value2;
        this.symbol = symbol;
    }

    public FirstOrderExpression(double value1, double value2) {
        this.coefficient1 = value1;
        this.coefficient2 = value2;
        symbol = 'x';
    }

    @Override
    public String toString() {
        String firstPart = coefficient1 == 0.0 ? "" : coefficient1+"";
        String secondPart = coefficient2 == 0.0 ? "" : coefficient2 == 1 ? " + " + symbol :  " + " + coefficient2 + "" + symbol;
        return firstPart + secondPart;
    }

    @Override
    public double[] getValue() {
        var value = new double[2];
        value[0] = this.coefficient1;
        value[1] = this.coefficient2;
        return value;
    }


    public Expression add(Expression term) {
        if (term instanceof ConstantExpression) {
            return new FirstOrderExpression(coefficient1 + ((ConstantExpression) term).getValue()[0], coefficient2);
        } else if (term instanceof FirstOrderExpression) {
            double[] coefficients = ((FirstOrderExpression) term).getValue();
            return new FirstOrderExpression(coefficient1 + coefficients[0], coefficient2 + coefficients[1]);
        }
        return null;
    }

    public Expression mult(Expression term) {
        if (term instanceof ConstantExpression) {
            double val = ((ConstantExpression) term).getValue()[0];
            return new FirstOrderExpression(coefficient1 * val, coefficient2 * val);
        }
        return null;
    }

    public Expression divide(Expression term) {
        if (term instanceof ConstantExpression) {
            double divisor = ((ConstantExpression) term).getValue()[0];
            if (divisor == 0.0)
                throw new ArithmeticException("Division of 0");
            return new FirstOrderExpression(coefficient1 / divisor, coefficient2 / divisor);
        }   
        return null;
    }

    public Expression subtract(Expression term) {
        if (term instanceof ConstantExpression) {
            double val = ((ConstantExpression) term).getValue()[0];
            return new FirstOrderExpression(coefficient1-val, coefficient2);
        }   else if (term instanceof FirstOrderExpression) {
            double[] coefficients = ((FirstOrderExpression) term).getValue();
            return new FirstOrderExpression(coefficient1 - coefficients[0],coefficient2 - coefficients[1]);
        }
        return null;
    }

    public Expression negate(){
        return new FirstOrderExpression(-coefficient1, -coefficient2);
    }
}