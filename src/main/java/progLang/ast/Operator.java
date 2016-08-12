package progLang.ast;

public enum Operator {
    ARITH_ADD("+"),
    ARITH_MINUS("-"),
    ARITH_MULT("*"),
    ARITH_DIV("/");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
