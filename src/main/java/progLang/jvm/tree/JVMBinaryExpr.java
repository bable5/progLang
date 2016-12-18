package progLang.jvm.tree;

import java.util.Objects;

public class JVMBinaryExpr implements JVMExpr {
    public final JVMOperator operator;
    public final JVMExpr lhs;
    public final JVMExpr rhs;
    public final Type type;

    public JVMBinaryExpr(JVMOperator operator, JVMExpr lhs, JVMExpr rhs, Type type) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
        this.type = type;
    }

    @Override
    public String toString() {
        return "JVMBinaryExpr{" +
                "operator=" + operator +
                ", lhs=" + lhs +
                ", rhs=" + rhs +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JVMBinaryExpr that = (JVMBinaryExpr) o;
        return operator == that.operator &&
                Objects.equals(lhs, that.lhs) &&
                Objects.equals(rhs, that.rhs) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, lhs, rhs, type);
    }
}
