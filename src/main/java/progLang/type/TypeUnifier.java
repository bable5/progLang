package progLang.type;

import progLang.ast.Symbol;
import progLang.util.Context;

public class TypeUnifier {

    public static TypeUnifier instance(Context context) {
        TypeUnifier unifier = context.get(TypeUnifier.class);
        if (unifier == null) {
            unifier = new TypeUnifier();
            context.put(TypeUnifier.class, unifier);
        }
        return unifier;
    }

    public Symbol unify(TypeEnv env, Symbol type1, Symbol type2) {
        if (type1.equals(type2)) {
            return type1;
        }
        throw new UnificationException(type1, type2);
    }


}
