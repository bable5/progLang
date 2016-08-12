package progLang.main;

import org.junit.Test;
import progLang.ast.CompilationUnit;
import progLang.ast.Literal;
import progLang.progLangParser;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;


public class ASTExtractorTest extends AbstractProgLangTest {

    private ASTExtractor extractor;


    protected void postSetup() {
        extractor = ASTExtractor.instance(context);
    }

    @Test
    public void extractCompilationUnit() {
        progLangParser.ProgLangContext progLangContext = givenCompilationUnit("1;");

        CompilationUnit unit = extractor.extract(progLangContext);

        assertThat(unit.stmts, hasSize(1));
        assertThat(unit.stmts.get(0).expr, is(new Literal(1)));
    }

    private progLangParser.ProgLangContext givenCompilationUnit(String programText) {
        return givenParser(programText).progLang();
    }
}
