package progLang.main;

import org.junit.Test;
import org.mockito.Mock;
import progLang.ast.CompilationUnit;
import progLang.progLangParser;
import progLang.testUtils.AbstractProgLangTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


public class ASTExtractorTest extends AbstractProgLangTest{

    private ASTExtractor extractor;

    @Mock
    private progLangParser.ProgLangContext parserContext;

    protected void postSetup() {


        extractor = ASTExtractor.instance(context);
    }

    @Test
    public void extractCompilationUnit() {
        final List<CompilationUnit> expectedUnits = new ArrayList<>();
//        when(compilationUnitsExtractor).thenReturn(exptectedUnits);

        CompilationUnit units = extractor.extract(parserContext);

        assertThat(units, is(expectedUnits));
    }
}
