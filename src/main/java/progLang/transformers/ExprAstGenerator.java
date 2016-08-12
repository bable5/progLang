package progLang.transformers;

import progLang.ast.Expr;
import progLang.progLangBaseVisitor;
import progLang.util.Context;

public class ExprAstGenerator extends progLangBaseVisitor<Expr> {
    public static ExprAstGenerator instance(Context context) {
        ExprAstGenerator visitor = context.get(ExprAstGenerator.class);
        if (visitor == null) {
            visitor = new ExprAstGenerator(context);
            context.put(ExprAstGenerator.class, visitor);
        }
        return visitor;
    }

    protected ExprAstGenerator(Context context) {
        super();
    }
}
