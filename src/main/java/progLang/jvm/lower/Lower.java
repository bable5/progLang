package progLang.jvm.lower;

import progLang.ast.CompilationUnit;
import progLang.ast.Stmt;
import progLang.jvm.AsJVMVisitor;
import progLang.jvm.tree.*;
import progLang.util.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class Lower {

    public static Lower instance(Context context) {
        Lower type = context.get(Lower.class);
        if (type == null) {
            type = new Lower();
            context.put(Lower.class, type);
        }
        return type;
    }

    private final AsJVMVisitor asJvmVisitor;

    private Lower() {
        asJvmVisitor = new AsJVMVisitor();
    }

    public JVMCompilationUnit apply(CompilationUnit compilationUnit) {
        return new JVMClass(
                "",
                compilationUnit.name.name,
                new ArrayList<>(),
                lowerStmts(compilationUnit.stmts)
        );
    }

    private List<JVMMethod> lowerStmts(List<Stmt> stmts) {
        return Arrays.asList(
                createDefaultMethod(stmts)
        );
    }

    protected JVMMethod createDefaultMethod(List<Stmt> body) {
        List<Flag> flags = Arrays.asList(Flag.FLAG_PUBLIC);
        Type type = Type.INT_TYPE;

        return new JVMMethod(flags, type, "generated$default", emptyList(), toJvmStmts(body));
    }

    protected List<JVMStmt> toJvmStmts(List<Stmt> body) {
        Stmt lastStmt = body.get(body.size() - 1);
        JVMExpr accept = lastStmt.expr.accept(asJvmVisitor, null);
        return Arrays.asList(new JVMReturnStmt(accept));
    }
}
