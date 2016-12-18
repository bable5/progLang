package progLang.ast;

import java.util.Objects;

public class Stmt {
    public final Expr expr;

    public Stmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stmt stmt = (Stmt) o;
        return Objects.equals(expr, stmt.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }

    @Override
    public String toString() {
        return "Stmt{" +
                "expr=" + expr +
                '}';
    }
}
