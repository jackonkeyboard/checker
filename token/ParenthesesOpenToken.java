package token;

class ParenthesesOpenToken extends Token {
    ParenthesesOpenToken() {
        super(TokenType.PARENTHESES_OPEN);
    }

    @Override
    public String toString() {
        return "( ";
    }
}