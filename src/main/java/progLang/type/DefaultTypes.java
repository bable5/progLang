package progLang.type;

import progLang.ast.Symbol;

public class DefaultTypes {
    public static final Symbol PROG_LANG_INTEGER = new Symbol(namespaced("Integer"));

    private static String namespaced(String symbol) {
        return "progLang." + symbol;
    }
}
