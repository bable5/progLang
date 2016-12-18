package progLang.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static progLang.prettyPrinting.Literals.EOL;

public class CompilationUnit {
    public final Symbol name;
    public final List<Stmt> stmts;

    public CompilationUnit(List<Stmt> stmts) {
        this(new Symbol(""), stmts);
    }

    public CompilationUnit(Symbol name, List<Stmt> stmts) {
        this.name = name;
        this.stmts = Collections.unmodifiableList(stmts);
    }


    public CompilationUnit copy(ArrayList<Stmt> typedStmts) {
        return new CompilationUnit(name, typedStmts);
    }

    @Override
    public String toString() {
        String printableStmts = stmts.stream()
                .map(s -> s.toString())
                .collect(joining(EOL));

        return "CompilationUnit{" +
                "name=" + name +
                ", stmts=" + printableStmts +
                '}';
    }
}
