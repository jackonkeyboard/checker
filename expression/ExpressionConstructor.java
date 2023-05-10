package expression;
import arithmeticParsing.*;
import token.*;
import operator.*;
import token.Token.TokenType;

import java.util.*;

public final class ExpressionConstructor {
    private Token[] tokens;

    ExpressionConstructor(Token[] tokens) {
        this.tokens = tokens;
    }

    ExpressionConstructor(Token token) {
        this.tokens = new Token[1];
        this.tokens[0] = token;
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

    public static void main(String[] args) {
        Token[] tokens = Parser.convert("2(2x+1)+4x(5+1)+9x+(2+2)(x+2x)+9");
        Expression e = (new ExpressionConstructor(tokens)).evaluate();
        System.out.println(e.toString());
    }
}
