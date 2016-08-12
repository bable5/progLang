package progLang.transformers;

import progLang.ast.Stmt;
import progLang.progLangBaseVisitor;
import progLang.progLangParser;
import progLang.util.Context;

public class StmtAstGenerator extends progLangBaseVisitor<Stmt> {
    public static StmtAstGenerator instance(Context context) {
        StmtAstGenerator visitor = context.get(StmtAstGenerator.class);
        if (visitor == null) {
            visitor = new StmtAstGenerator(context);
            context.put(StmtAstGenerator.class, visitor);
        }
        return visitor;
    }

    private final ExprAstGenerator exprAstGenerator;

    public StmtAstGenerator(Context context) {
        exprAstGenerator = ExprAstGenerator.instance(context);
    }

    @Override
    public Stmt visitStmt(progLangParser.StmtContext ctx) {
        return new Stmt(exprAstGenerator.visitExpr(ctx.expr()));
    }
}
