package token;
import exceptions.*;

import java.util.*;

public final class TokenBuilder {
    private char[] expression;
    private int index = 0;
    private Token prevToken;

    public TokenBuilder(String exp) {
        this.expression = exp.trim().toCharArray();
    }

    public boolean hasNext() {
        return this.expression.length > index;
    }

    public Token nextToken() throws UnknownTokenException {
        char ch = expression[index];
        while (Character.isWhitespace(ch)) {
            index++;
            ch = expression[index];
        }
        if (Character.isDigit(ch) || ch == '.') {
            if (isImplicitMultNeeded()) {
                prevToken = new OperatorToken('*');
                return prevToken;
            }
            return parseConstantToken();
        } else if (OperatorToken.isAllowedOperatorChar(ch)) {
            return parseOperatorToken(ch);
        } else if (ch == '(' || ch == ')') {
            if (isImplicitMultNeeded()) {
                prevToken = new OperatorToken('*');
                return prevToken;
            }
            return parseParenthesesToken();
        } else if (Character.isLetter(ch)) {
            if (isImplicitMultNeeded()) {
                prevToken = new OperatorToken('*');
                return prevToken;
            }
            return parseVariableToken(ch);

        }
        throw new UnknownTokenException("Char '" + ch + "' is not possible to parse");
    }

    private boolean isImplicitMultNeeded() {
        boolean isPrevTokenValid = prevToken != null &&
                (!(prevToken instanceof OperatorToken) &&
                        !(prevToken instanceof ParenthesesOpenToken));
        boolean isCurrentokenValid = expression[index] != ')';
        return isCurrentokenValid && isPrevTokenValid;
    }

    private Token parseConstantToken() {
        int startIndex = index;
        char ch = expression[index];
        while ((Character.isDigit(ch) || ch == '.')) {
            index++;
            if (!hasNext())
                break;
            ch = expression[index];
        }
        String constantS = new String(expression, startIndex, index - startIndex);
        double constant = 0.0;
        try {
            constant = Float.parseFloat(constantS);
        } catch (NumberFormatException e) {
            throw new Error("Could not retrieve contant");
        }

        this.prevToken = new ConstantToken(constant);
        return this.prevToken;
    }

    private Token parseParenthesesToken() {
        char ch = expression[index];
        if (ch == '(') {
            this.prevToken = new ParenthesesOpenToken();
        } else {
            this.prevToken = new ParenthesesClosedToken();
        }
        this.index++;
        return this.prevToken;
    }

    private Token parseOperatorToken(char ch) {
        this.prevToken = new OperatorToken(ch);
        this.index++;
        return prevToken;
    }

    private Token parseVariableToken(char ch) {
        this.prevToken = new VariableToken(ch);
        this.index++;
        return prevToken;
    }

    public ArrayList<Token> getTokens() throws UnknownTokenException {
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (hasNext()) {
            tokens.add(nextToken());
        }

        return tokens;
    }
}
