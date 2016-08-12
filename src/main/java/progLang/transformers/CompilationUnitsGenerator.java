package progLang.transformers;

import progLang.ast.CompilationUnit;
import progLang.ast.Stmt;
import progLang.progLangBaseVisitor;
import progLang.progLangParser;
import progLang.util.Context;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationUnitsGenerator extends progLangBaseVisitor<CompilationUnit> {

    public static CompilationUnitsGenerator instance(Context context) {
        CompilationUnitsGenerator visitor = context.get(CompilationUnitsGenerator.class);
        if (visitor == null) {
            visitor = new CompilationUnitsGenerator(context);
            context.put(CompilationUnitsGenerator.class, visitor);
        }
        return visitor;
    }

    private final StmtAstGenerator stmtAstGenerator;

    public CompilationUnitsGenerator(Context context) {
        stmtAstGenerator = StmtAstGenerator.instance(context);
    }

    @Override
    public CompilationUnit visitCompilationUnit(progLangParser.CompilationUnitContext ctx) {
        List<Stmt> stmts = ctx.stmt().stream()
                .map(stmt -> stmtAstGenerator.visitStmt(stmt))
                .collect(Collectors.toList());
        return new CompilationUnit(stmts);
    }
}
