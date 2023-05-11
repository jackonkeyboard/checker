package expression;

public abstract class Expression {
    public abstract Expression mult(Expression exp);
    public abstract Expression divide(Expression exp);
    public abstract Expression add(Expression exp);
    public abstract Expression subtract(Expression exp);
    public abstract Expression negate();

    public abstract double[] getValue();
}
