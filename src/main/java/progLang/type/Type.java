package progLang.type;

import progLang.ast.*;
import progLang.util.Context;

import java.util.ArrayList;

import static progLang.type.DefaultTypes.PROG_LANG_INTEGER;

public class Type extends AstVisitor<TypeCheckResult, TypeEnv> {

    public static Type instance(Context context) {
        Type type = context.get(Type.class);
        if (type == null) {
            type = new Type(context);
            context.put(Type.class, type);
        }
        return type;
    }

    private TypeUnifier unifier;

    private Type(Context context) {
        this.unifier = TypeUnifier.instance(context);
    }

    @Override
    public TypeCheckResult visitBinaryExpr(BinaryExpr binaryExpr, TypeEnv typeEnv) {
        System.out.println("Typing " + binaryExpr);
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

    public CompilationUnit check(CompilationUnit compilationUnit) {
        ArrayList<Stmt> typedStmts = new ArrayList<>(compilationUnit.stmts.size());
        TypeEnv env = new TypeEnv();

        for (Stmt s : compilationUnit.stmts) {
            TypeCheckResult result = s.expr.accept(this, env);

            env = result.env;
            Stmt typedStmt = new Stmt(result.expr);
            typedStmts.add(typedStmt);
        }


        return compilationUnit.copy(typedStmts);
    }
}
