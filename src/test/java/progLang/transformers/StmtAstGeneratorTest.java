package progLang.transformers;

import org.junit.Test;
import progLang.ast.Literal;
import progLang.ast.Stmt;
import progLang.progLangParser;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StmtAstGeneratorTest extends AbstractProgLangTest {

    private StmtAstGenerator visitor;

    @Override
    public void postSetup() {
        visitor = new StmtAstGenerator(context);
    }

    @Test
    public void parseStmtExpr() {
        Stmt stmt = givenStmt("1;");

        assertThat(stmt, is(new Stmt(
            new Literal(1)
        )));
    }

    private Stmt givenStmt(String s) {
        progLangParser.StmtContext stmt = givenParser(s).stmt();
        return visitor.visitStmt(stmt);
    }
}
