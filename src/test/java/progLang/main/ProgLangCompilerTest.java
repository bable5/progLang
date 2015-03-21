package progLang.main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import progLang.util.Context;
import progLang.util.Log;

@RunWith(MockitoJUnitRunner.class)
public class ProgLangCompilerTest {

    @Mock
    private Parser parser;
    
    private ProgLangCompiler compiler;
    
    private ByteArrayOutputStream err;
    
    private Log log;

    @Before
    public void setup() {
        Context context = new Context();
        err = new ByteArrayOutputStream();
        log = Log.instance(context);
        log.setError(new PrintStream(err));
        
        context.put(Parser.class, parser);
        
        compiler = ProgLangCompiler.instance(context);
    }
    
    @After
    public void tearDown() {
        compiler = null;
        err = null;
    }
    
    @Test
    public void parseErrorsTrapsException() throws IOException {
        doThrow(new FileNotFoundException("TEST EXCEPTION")).when(parser).parse(anyString());
        Options options = new Options();
        options.addFilename("src/main/examples/ObjectLiteral.progLang");
        
        compiler.compile(options);
        
        assertThat(err.toString(), equalTo("TEST EXCEPTION\n"));
    }
}
