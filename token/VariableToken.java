package token;

public class VariableToken extends Token {
    private final char var;

    public char getName() {
        return var;
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