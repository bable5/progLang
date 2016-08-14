package progLang.ast;

public abstract class Expr implements Visitable {
    public final Symbol type;

    public Expr(Symbol type) {
        this.type = type;
    }

    @Override
    public <R, P> R accept(AstVisitor<R, P> vistor, P p) {
        throw new UnsupportedOperationException("Base expr is not visitable");
    }
}
