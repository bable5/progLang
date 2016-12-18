package progLang.jvm.tree;

import java.util.Objects;

public class JVMReturnStmt implements JVMStmt {
    public final JVMExpr expr;

    public JVMReturnStmt(JVMExpr expr) {
        this.expr = expr;
    }

    public String toString() {
        return "return " + expr + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JVMReturnStmt that = (JVMReturnStmt) o;
        return Objects.equals(expr, that.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }
}
