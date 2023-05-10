package arithmeticParsing;

import token.*;
import token.Token.TokenType;

import java.util.*;

//Implementation of Shunting Yard Algorithm of Dijkstra
public final class Parser {
    public static Token[] convert(String expression) {
        Stack<Token> stack = new Stack<>();
        List<Token> output = new ArrayList<>();

        TokenBuilder tokenBuilder = new TokenBuilder(expression);
        while (tokenBuilder.hasNext()) {
            Token token = tokenBuilder.nextToken();
            switch (token.getType()) {
                case CONSTANT:
                case VARIABLE:
                    output.add(token);
                    break;
                case PARENTHESES_OPEN:
                    stack.push(token);
                    break;
                case PARENTHESES_CLOSE:
                    while (stack.peek().getType() != TokenType.PARENTHESES_OPEN) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    break;
                case OPERATOR:
                    while (stack.peek().getType() == TokenType.OPERATOR && !stack.empty()) {
                        OperatorToken o1 = (OperatorToken) token;
                        OperatorToken o2 = (OperatorToken) stack.peek();
                        int o1Importance = o1.getOperator().getImportance();
                        int o2Importance = o2.getOperator().getImportance();
                        if (o1.getOperator().getOperandsCount() == 1 && o2.getOperator().getOperandsCount() == 2) {
                            break;
                        } else if ((o1.getOperator().isLeftAssociative() && o1Importance <= o2Importance)
                                || (o1Importance < o2Importance)) {
                            output.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(token);
                    break;
                default:
                    throw new IllegalArgumentException("Token is not known.");
            }
        }
        while (!stack.empty()) {
            Token token = stack.pop();
            if (token.getType() == TokenType.PARENTHESES_CLOSE || token.getType() == TokenType.PARENTHESES_OPEN) {
                throw new IllegalArgumentException("Parantheses count error");
            } else {
                output.add(token);
            }
        }
        return output.toArray(new Token[0]);
    }
}
