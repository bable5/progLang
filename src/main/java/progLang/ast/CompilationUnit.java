package progLang.ast;

import java.util.Collections;
import java.util.List;

public class CompilationUnit {
    public final List<Stmt> stmts;

    public CompilationUnit(List<Stmt> stmts) {
        this.stmts = Collections.unmodifiableList(stmts);
    }
}
