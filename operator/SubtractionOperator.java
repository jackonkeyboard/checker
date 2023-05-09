package operator;
import expression.*;

public final class SubtractionOperator extends Operator {
    private static final int operandsCount = 2;
    private static final int importance = 1;
    private static final String symbol = "-";
    private static final boolean isLeftAssociative = true;
    
    public SubtractionOperator() {
        super(symbol, operandsCount, isLeftAssociative, importance);
    }

    @Override
    public Expression eval(Expression... args) {
        return args[0].subtract(args[1]);
    }
}