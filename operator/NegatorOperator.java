package operator;

import expression.*;

//though we are not using Negator operator, 
//let's just implement it for the sake of implementing ))
public class NegatorOperator extends Operator{
    private static final int operandsCount = 1;
    private static final int importance = 5;
    private static final String symbol = "-";
    private static final boolean isLeftAssociative = false;

    public NegatorOperator() {
        super(symbol, operandsCount, isLeftAssociative, importance);
    }

    @Override
    public Expression eval(Expression... args) {
        if(args.length != 1){
            throw new ArithmeticException("Negation is unary operator.");
        }
        return args[0].negate();
    }
}
