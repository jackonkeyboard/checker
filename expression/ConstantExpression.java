package expression;

public class ConstantExpression extends Expression {
    private double value;

    public ConstantExpression(double value) {
        this.value = value;
    }

    public double[] getValue() {
        var value = new double[1];
        value[0] = this.value;
        return value;
    }

    public String toString() {
        return value + "";
    }

    public Expression add(Expression term) {
        if (term instanceof ConstantExpression) {
            return new ConstantExpression(value + ((ConstantExpression) term).getValue()[0]);
        } else if (term instanceof FirstOrderExpression) {
            double[] coefficients = ((FirstOrderExpression) term).getValue();
            return new FirstOrderExpression(value + coefficients[0], coefficients[1]);
        }
        return null;
    }

    public Expression mult(Expression term) {
        if (term instanceof ConstantExpression) {
            return new ConstantExpression(value * ((ConstantExpression) term).getValue()[0]);
        } else if (term instanceof FirstOrderExpression) {
            double[] coefficients = ((FirstOrderExpression) term).getValue();
            return new FirstOrderExpression(value * coefficients[0], value * coefficients[1]);
        }
        return null;
    }

    public Expression divide(Expression term) {
        if (term instanceof ConstantExpression) {
            double divisor = ((ConstantExpression) term).getValue()[0];
            if (divisor == 0.0)
                throw new ArithmeticException("Division of 0");
            return new ConstantExpression(value / divisor);
        }   
        return null;
    }

    public Expression subtract(Expression term) {
        if (term instanceof ConstantExpression) {
            return new ConstantExpression(value - ((ConstantExpression) term).getValue()[0]);
        }   else if (term instanceof FirstOrderExpression) {
            double[] coefficients = ((FirstOrderExpression) term).getValue();
            return new FirstOrderExpression(value - coefficients[0], -coefficients[1]);
        }
        return null;
    }

    public Expression negate(){
        return new ConstantExpression(-value);
    }
}