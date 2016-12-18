package progLang.jvm.tree;

import java.util.Objects;

public class JVMIntExpr implements JVMExpr {
    public final int value;

    public JVMIntExpr(int value) {
        this.value = value;
    }

    public String toString() {
        return "i" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JVMIntExpr that = (JVMIntExpr) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
