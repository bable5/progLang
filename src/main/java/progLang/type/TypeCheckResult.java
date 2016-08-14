package progLang.type;

import progLang.ast.Expr;

public class TypeCheckResult {
    public final Expr expr;
    public final TypeEnv env;

    public TypeCheckResult(Expr expr, TypeEnv env) {
        this.expr = expr;
        this.env = env;
    }
}
