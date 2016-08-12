package progLang.transformers;

import progLang.ast.BinaryExpr;
import progLang.ast.Expr;
import progLang.ast.Literal;
import progLang.ast.Operator;
import progLang.progLangBaseVisitor;
import progLang.progLangParser;
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

    @Override
    public Expr visitNumberLiteral(progLangParser.NumberLiteralContext ctx) {
        return new Literal(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public Expr visitMultExpr(progLangParser.MultExprContext ctx) {
        Expr lhs = ctx.arithExpr(0).accept(this);
        Expr rhs = ctx.arithExpr(1).accept(this);

        Operator op = ctx.ARITH_MULT()!= null ? Operator.ARITH_MULT : Operator.ARITH_DIV;

        return new BinaryExpr(op, lhs, rhs);
    }

    @Override
    public Expr visitAddExpr(progLangParser.AddExprContext ctx) {
        Expr lhs = ctx.arithExpr(0).accept(this);
        Expr rhs = ctx.arithExpr(1).accept(this);

        Operator op = ctx.ARITH_ADD() != null ? Operator.ARITH_ADD : Operator.ARITH_MINUS;

        return new BinaryExpr(op, lhs, rhs);
    }

    @Override
    public Expr visitNestArithExpr(progLangParser.NestArithExprContext ctx) {
        return ctx.arithExpr().accept(this);
    }
}
