package token;

import expression.*;

public final class ConstantToken extends Token{
    private double value;

    public ConstantToken(double value) {
        super(TokenType.CONSTANT);
        this.value = value;
    }

    public ConstantExpression getValue() {
        return new ConstantExpression(value);
    }

    @Override
    public String toString() {
        return value + " ";
    }
}
