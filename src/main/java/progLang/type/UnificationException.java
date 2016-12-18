package progLang.type;

import progLang.ast.Symbol;

public class UnificationException extends RuntimeException {

    public final Symbol symbol1;
    public final Symbol symbol2;

    public UnificationException() {
        this(new Symbol("Unknown"), new Symbol("Unknown"));
    }

    public UnificationException(Symbol type1, Symbol type2) {
        symbol1 = type1;
        symbol2 = type2;
    }
}
