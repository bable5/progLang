package progLang.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogTest {

    private Context context;
    private Log log;
    private ByteArrayOutputStream err;
    private ByteArrayOutputStream out;

    @Before
    public void setup() {
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        context = new Context();
        log = Log.instance(context);
        log.setOutput(new PrintStream(out));
        log.setError(new PrintStream(err));
    }

    @After
    public void tearDown() {
        out = null;
        err = null;
        context = null;
        log = null;
    }

    @Test
    public void compileErrorMessage() {
        log.error("compile.error", "some compile error");
        assertThat(err.toString(), equalTo("some compile error\n"));
        assertStdOutEmpty();
    }

    @Test
    public void fileNotFoundMessage() {
        log.error("file.not.found", "f");
        assertThat(err.toString(), equalTo("File f not found\n"));
        assertStdOutEmpty();
    }
    
    private void assertStdOutEmpty() {
        assertThat(out.toString(), equalTo(""));
    }
}
