package token;

import expression.*;

public final class VariableToken extends Token {
    private char var;

    public FirstOrderExpression getValue() {
        //init with default coefficients
        return new FirstOrderExpression(0, 1 , var);
    }

    public VariableToken(char var) {
        super(TokenType.VARIABLE);
        this.var = var;
    }

    @Override
    public String toString() {
        return var + " ";
    }
}