package operator;
import expression.*;

public abstract class Operator {
    private boolean isLeftAssociative;
    private String operator;
    private int importance;
    private int operandsCount;

    public Operator(String operator, int operandsCount, boolean isLeftAssociative,
                    int importance) {
        this.isLeftAssociative = isLeftAssociative;
        this.operator = operator;
        this.importance = importance;
        this.operandsCount = operandsCount;
    }

    public abstract Expression eval(Expression... args);

    public boolean isLeftAssociative() {
        return isLeftAssociative;
    }

    public int getImportance() {
        return importance;
    }

    public String getOperator() {
        return operator;
    }

    public int getOperandsCount() {
        return operandsCount;
    }
}
