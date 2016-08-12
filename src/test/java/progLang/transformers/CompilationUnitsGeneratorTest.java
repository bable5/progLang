package progLang.transformers;

import org.junit.Test;
import progLang.ast.CompilationUnit;
import progLang.ast.Literal;
import progLang.ast.Stmt;
import progLang.progLangParser;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CompilationUnitsGeneratorTest extends AbstractProgLangTest {
    private CompilationUnitsGenerator visitor;

    @Override
    public void postSetup() {
        visitor = new CompilationUnitsGenerator(context);
    }

    @Test
    public void extractOneStmt() {
        CompilationUnit compilationUnit = givenCompilationUnit("1;");

        assertThat(compilationUnit.stmts, hasSize(1));
        assertThat(compilationUnit.stmts.get(0), is(
                new Stmt(new Literal(1))
        ));
    }

    @Test
    public void extractSeveralStmts() {
        CompilationUnit compilationUnit = givenCompilationUnit(
                "1;2;3;"
        );

        assertThat(compilationUnit.stmts, hasSize(3));
        assertThat(compilationUnit.stmts.get(0), is(
                new Stmt(new Literal(1))
        ));
        assertThat(compilationUnit.stmts.get(1), is(
                new Stmt(new Literal(2))
        ));
        assertThat(compilationUnit.stmts.get(2), is(
                new Stmt(new Literal(3))
        ));
    }

    private CompilationUnit givenCompilationUnit(String program) {
        progLangParser.CompilationUnitContext compilationUnitContext = givenParser(program).compilationUnit();
        return visitor.visitCompilationUnit(compilationUnitContext);
    }
}
