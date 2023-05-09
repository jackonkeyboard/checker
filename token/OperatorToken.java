package token;

public final class OperatorToken extends Token {
    private static char[] ALLOWED_OPERATORS = {'+', '-', '*', '/', '=', '^'};
    private char operator;

    public OperatorToken(char op) {
        super(TokenType.OPERATOR);
        if (!isAllowedOperatorChar(op)) {
            throw new IllegalArgumentException("Not known Operator found.");
        }
        this.operator = op;
    }

    public char getOperator() {
        return operator;
    }

    public static boolean isAllowedOperatorChar(char ch) {
        for (char allowed : ALLOWED_OPERATORS) {
            if (ch == allowed) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return operator + " ";
    }
}
