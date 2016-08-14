package progLang.ast;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpr extends Expr {
    public final List<Identifier> params = new ArrayList<>();
    public Expr body;

    public LambdaExpr() {
        super(null);
    }

    public LambdaExpr(Symbol type) {
        super(type);
    }
}
