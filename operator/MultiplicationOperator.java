package operator;
import expression.*;

public final class MultiplicationOperator extends Operator {
    private static final int operandsCount = 2;
    private static final int importance = 2;
    private static final String symbol = "*";
    private static final boolean isLeftAssociative = true;
    
    public MultiplicationOperator() {
        super(symbol, operandsCount, isLeftAssociative, importance);
    }

    @Override
    public Expression eval(Expression ...args) {
        return args[0].mult(args[1]);
    }
}
