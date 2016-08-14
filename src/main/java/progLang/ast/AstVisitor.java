package progLang.ast;

public abstract class AstVisitor<R, P> {
    public abstract R visitLiteral(Literal exprLiteral, P p);

    public abstract R visitBinaryExpr(BinaryExpr binaryExpr, P p);
}
