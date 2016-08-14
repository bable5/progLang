package progLang.testUtils;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import progLang.progLangLexer;
import progLang.progLangParser;
import progLang.util.Context;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractProgLangTest {

    protected Context context;

    @Before
    public final void setup() {
        context = new Context();
        postSetup();
    }
    @After
    public final void teardown() {
        preTearDown();
        context = null;
    }

    protected void postSetup() {

    }

    protected void preTearDown() {

    }

    protected progLangParser givenParser(String programText) {
        progLangLexer lexer = new progLangLexer(new ANTLRInputStream(programText));
        progLangParser parser = new progLangParser(new CommonTokenStream(lexer));

        return parser;
    }
}
