package progLang.ast;

import java.util.HashMap;
import java.util.Map;

public class ObjectLiteral extends Expr {
    public final Map<Identifier, Expr> members = new HashMap<>();

    public ObjectLiteral(Symbol type) {
        super(type);
    }
}
