package token;

public final class ParenthesesClosedToken extends Token {
    ParenthesesClosedToken() {
        super(TokenType.PARENTHESES_CLOSE);
    }

    @Override
    public String toString() {
        return ") ";
    }
}
