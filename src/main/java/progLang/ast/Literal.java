package progLang.ast;

import java.util.Objects;

public class Literal extends Expr {
    public final int value;

    public Literal(int value) {
        super(null);
        this.value = value;
    }

    public Literal(int value, Symbol type) {
        super(type);
        this.value = value;
    }

    @Override
    public <R, P> R accept(AstVisitor<R, P> visitor, P p) {
        return visitor.visitLiteral(this, p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return value == literal.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
