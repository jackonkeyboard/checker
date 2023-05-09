package token;

import java.util.*;

public class Tokenizer {
    private char[] expression;
    private int index = 0;
    private Token lastToken;

    public Tokenizer(String exp) {
        this.expression = exp.trim().toCharArray();
    }

    public boolean hasNext() {
        return this.expression.length > index;
    }

    public Token nextToken() {
        char ch = expression[index];
        while (Character.isWhitespace(ch)) {
            ch = expression[++index];
        }
        if (Character.isDigit(ch) || ch == '.') {
            if (isImplicitMultNeeded()) {
                lastToken = new OperatorToken('*');
                return lastToken;
            }
            return parseConstantToken();
        } else if (ch == '(' || ch == ')') {
            if (isImplicitMultNeeded()) {
                lastToken = new OperatorToken('*');
                return lastToken;
            }
            return parseParenthesesToken(ch == '(');
        } else if (OperatorToken.isAllowedOperatorChar(ch)) {
            return parseOperatorToken(ch);
        } else if (Character.isLetter(ch)) {
            if (isImplicitMultNeeded()) {
                lastToken = new OperatorToken('*');
                return lastToken;
            }
            return parseVariableToken(ch);

        }
        throw new IllegalArgumentException("Unable to parse char '" + ch + "' (Code:" + (int) ch + ") at [" + index + "]");
    }

    private boolean isImplicitMultNeeded() {
        boolean isLastTokenValid = lastToken != null &&
                (!(lastToken instanceof OperatorToken) &&
                        !(lastToken instanceof ParenthesesOpenToken));
        boolean isCurrentokenValid = expression[index] != ')';
        return isCurrentokenValid && isLastTokenValid;
    }

    private Token parseConstantToken() {
        int startIndex = index;
        char ch = expression[index];
        while ((Character.isDigit(ch) || ch == '.')) {
            index++;
            if(!hasNext()) break;
            ch = expression[index];
        }
        String constantS = new String(expression, startIndex, index - startIndex);
        double constant = 0.0;
        try {
            constant = Float.parseFloat(constantS);
        } catch (NumberFormatException e) {
            throw new Error("Could not retrieve contant");
        }

        lastToken = new ConstantToken(constant);
        return lastToken;
    }

    private Token parseParenthesesToken(boolean isOpen) {
        if (isOpen) {
            this.lastToken = new ParenthesesOpenToken();
        } else {
            this.lastToken = new ParenthesesClosedToken();
        }
        this.index++;
        return lastToken;
    }

    private Token parseOperatorToken(char ch) {
        this.lastToken = new OperatorToken(ch);
        this.index++;
        return lastToken;
    }

    private Token parseVariableToken(char ch) {
        this.lastToken = new VariableToken(ch);
        this.index++;
        return lastToken;
    }

    public ArrayList<Token> getTokens(){
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (hasNext()) {
            tokens.add(nextToken());
        }

        return tokens;
    }
}
