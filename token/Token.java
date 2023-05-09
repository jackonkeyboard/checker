package token;

public abstract class Token {
    public enum TokenType {CONSTANT, OPERATOR, PARENTHESES_OPEN, PARENTHESES_CLOSE, VARIABLE}
    private TokenType type;

    Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }
}
