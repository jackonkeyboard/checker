package token;

public final class ConstantToken extends Token{
    private final double value;

    public ConstantToken(double value) {
        super(TokenType.CONSTANT);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " ";
    }
}
