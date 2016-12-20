package progLang.jvm.tree;

public interface Visitable {
    <R, P> R accept(JVMVisitor<R, P> visitor, P p);
}
