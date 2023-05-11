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
        for (Token t : tokens) {
            if (t.getType() == TokenType.CONSTANT) {
                output.push(((ConstantToken) t).getValue());
            } else if (t.getType() == TokenType.VARIABLE) {
                output.push(((VariableToken) t).getValue());
            } else if (t.getType() == TokenType.OPERATOR) {
                OperatorToken opToken = (OperatorToken) t;
                Operator operator = opToken.getOperator();
                if (output.size() < operator.getOperandsCount()) {
                    throw new IllegalArgumentException("Not enough operands are provided for the '" + operator.getOperator() + "' operator.");
                }
                if (operator.getOperandsCount() == 2) {
                    Expression rightArg = output.pop();
                    Expression leftArg = output.pop();
                    output.push(operator.eval(leftArg, rightArg));
                } else if (operator.getOperandsCount() == 1) {
                    Expression arg = output.pop();
                    output.push(operator.eval(arg));
                }
            } 
        }
        if (output.size() > 1) {
            throw new IllegalArgumentException("Mismatch of operators count and operands");
        }
        return output.pop();
    }
}
