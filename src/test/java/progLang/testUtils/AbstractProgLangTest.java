package progLang.testUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
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
    public void teardown() {
        preTearDown();
        context = null;
    }

    protected void postSetup() {

    }

    protected void preTearDown() {

    }
}
