package progLang;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import progLang.main.Options;
import progLang.main.ProgLangCompiler;
import progLang.main.ProgLangCompiler.Result;
import progLang.testUtils.AbstractProgLangTest;
import progLang.util.Context;
import progLang.util.Log;

@RunWith(MockitoJUnitRunner.class)
public class MainTest extends AbstractProgLangTest{

    @Mock
    private ProgLangCompiler compiler;

    @Mock
    private Log log;

    private Main main;

    public void postSetup() {
        context.put(Log.class, log);
        context.put(ProgLangCompiler.class, compiler);
        compiler = ProgLangCompiler.instance(context);

        main = new Main(context);
    }

    @Test
    public void mainCompileTrapsErrors() {
        when(compiler.compile(any(Options.class))).thenThrow(new RuntimeException("TEST"));

        Result result = main.runCompiler(new String[] {});

        assertThat(result, equalTo(Result.ERROR));
        verify(log).error("compile.error", "TEST");
    }
}
