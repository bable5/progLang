package progLang.jvm.tree;

import progLang.ast.Symbol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static progLang.type.DefaultTypes.PROG_LANG_INTEGER;

public class TypeMap {

    private static final Map<Symbol, Type> TYPE_MAP;

    static {
        HashMap<Symbol, Type> typeMap = new HashMap<>();

        typeMap.put(PROG_LANG_INTEGER, Type.INT_TYPE);

        TYPE_MAP = Collections.unmodifiableMap(typeMap);
    }

    public static Type type(Symbol symbol) {
        Type type = TYPE_MAP.get(symbol);

        if (type != null) {
            return type;
        }

        return new Type.NamedType(symbol.name);
    }
}
