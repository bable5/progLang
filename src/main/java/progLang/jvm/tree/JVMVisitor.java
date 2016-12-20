package progLang.jvm.tree;

public interface JVMVisitor<R, P> {
    R visitReturnStmt(JVMReturnStmt returnStmt, P p);

    R visitIntExpr(JVMIntExpr jvmIntExpr, P p);

    R visitBinaryExpr(JVMBinaryExpr jvmBinaryExpr, P p);
}
