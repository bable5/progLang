package progLang.type;

import progLang.ast.AstVisitor;
import progLang.ast.BinaryExpr;
import progLang.ast.Literal;
import progLang.ast.Symbol;
import progLang.util.Context;

import static progLang.type.DefaultTypes.PROG_LANG_INTEGER;

public class Type extends AstVisitor<TypeCheckResult, TypeEnv> {

    private TypeUnifier unifier;

    public Type(Context context) {
        this.unifier = context.get(TypeUnifier.class);
    }

    @Override
    public TypeCheckResult visitBinaryExpr(BinaryExpr binaryExpr, TypeEnv typeEnv) {
        TypeCheckResult lhsResult = binaryExpr.lhs.accept(this, typeEnv);
        TypeCheckResult rhsResult = binaryExpr.rhs.accept(this, lhsResult.env);

        Symbol unifiedType = unify(rhsResult.env, lhsResult.expr.type, rhsResult.expr.type);

        return new TypeCheckResult(
                new BinaryExpr(
                        binaryExpr.operator,
                        lhsResult.expr,
                        rhsResult.expr,
                        unifiedType
                ), rhsResult.env);
    }

    protected Symbol unify(TypeEnv env, Symbol type1, Symbol type2) {
        return unifier.unify(env, type1, type2);
    }


    @Override
    public TypeCheckResult visitLiteral(Literal exprLiteral, TypeEnv p) {
        return new TypeCheckResult(new Literal(exprLiteral.value, PROG_LANG_INTEGER), p);
    }
}
