package progLang.ast;

import java.util.Objects;

public class BinaryExpr extends Expr {
    public final Operator operator;
    public final Expr lhs;
    public final Expr rhs;

    public BinaryExpr(Operator operator, Expr lhs, Expr rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryExpr that = (BinaryExpr) o;
        return operator == that.operator &&
                Objects.equals(lhs, that.lhs) &&
                Objects.equals(rhs, that.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, lhs, rhs);
    }

    @Override
    public String toString() {
        return "BinaryExpr{" +
                "operator=" + operator +
                ", lhs=" + lhs +
                ", rhs=" + rhs +
                '}';
    }
}
