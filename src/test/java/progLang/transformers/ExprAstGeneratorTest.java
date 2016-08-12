package progLang.transformers;


import org.junit.Before;
import progLang.util.Context;

public class ExprAstGeneratorTest {

    private ExprAstGenerator visitor;

    @Before
    public void setup() {
        Context context = new Context();

        visitor = new ExprAstGenerator(context);
    }
}
