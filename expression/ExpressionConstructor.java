package expression;
import arithmeticParsing.*;
import token.*;
import operator.*;
import token.Token.TokenType;

import java.util.*;

public final class ExpressionConstructor {
    private Token[] tokens;

    public ExpressionConstructor(String expression) {
        Token[] tokens = Parser.convert(expression);
        this.tokens = tokens;
    }

    public Expression evaluate() {
        Stack<Expression> output = new Stack<Expression>();
        for (Token token : tokens) {
            if (token.getType() == TokenType.CONSTANT) {
                output.push(((ConstantToken) token).getValue());
            } else if (token.getType() == TokenType.VARIABLE) {
                output.push(((VariableToken) token).getValue());
            } else if (token.getType() == TokenType.OPERATOR) {
                OperatorToken opToken = (OperatorToken) token;
                Operator operator = opToken.getOperator();
                if (output.size() < operator.getOperandsCount()) {
                    throw new IllegalArgumentException("Not enough operands are provided for the '" + operator.getOperator() + "' operator.");
                }
                //ideally if we supported operators requiring more than 2 operands
                //we would want to pop last n items from the output stack instead of this hardcoded logic
                if (operator.getOperandsCount() == 1) {
                    Expression arg = output.pop();
                    output.push(operator.eval(arg));
                } else if (operator.getOperandsCount() == 2) {
                    Expression rightArg = output.pop();
                    Expression leftArg = output.pop();
                    output.push(operator.eval(leftArg, rightArg));
                }
            } 
        }
        if (output.size() > 1) {
            throw new IllegalArgumentException("Mismatch of operators count and operands");
        }
        return output.pop();
    }
}
