package progLang.ast;

public interface Visitable {
    <R, P> R accept(AstVisitor<R, P> visitor, P p);
}
