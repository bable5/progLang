package progLang.jvm.lower;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Test;
import progLang.ast.*;
import progLang.jvm.tree.*;
import progLang.testUtils.AbstractProgLangTest;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static progLang.jvm.tree.Flag.FLAG_PUBLIC;
import static progLang.jvm.tree.Type.INT_TYPE;
import static progLang.type.DefaultTypes.PROG_LANG_INTEGER;

public class LowerTest extends AbstractProgLangTest {

    private Lower lower;

    @Override
    protected void postSetup() {
        lower = Lower.instance(context);
        super.postSetup();
    }

    @Test
    public void generateSimpleJVMClass() throws Exception {
        List<Stmt> cuStmts = unmodifiableList(Arrays.asList(
                new Stmt(
                        new BinaryExpr(Operator.ARITH_ADD, intExpr(3), intExpr(5), intType())
                )
        ));
        String expectedClassName = "ANY_CLASS_NAME";
        CompilationUnit cu = new CompilationUnit(new Symbol(expectedClassName), cuStmts);

        JVMClass result = (JVMClass) lower.apply(cu);

        assertThat(result.packageName, is(""));
        assertThat(result.compilationUnitName, is(expectedClassName));
        assertThat(result.fields, empty());
        assertThat(result.methods, containsDefaultMethod(
                Arrays.asList(
                        new JVMReturnStmt(
                                new JVMBinaryExpr(JVMOperator.ARITH_ADD, jvmIntExpr(3), jvmIntExpr(5), Type.INT_TYPE)
                        )
                )
        ));
    }

    private CustomTypeSafeMatcher<List<JVMMethod>> containsDefaultMethod(List<JVMStmt> expectedBody) {
        return new CustomTypeSafeMatcher<List<JVMMethod>>("Default method matcher") {
            @Override
            protected boolean matchesSafely(List<JVMMethod> jvmMethods) {
                assertThat(jvmMethods, hasSize(1));

                JVMMethod defaultMethod = jvmMethods.get(0);

                assertThat(defaultMethod.flags, hasItems(FLAG_PUBLIC));
                assertThat(defaultMethod.type, is(INT_TYPE));
                assertThat(defaultMethod.formalArguments, hasSize(0));

                for (int i = 0; i < expectedBody.size(); i++) {
                    assertThat(defaultMethod.body.get(i), is(expectedBody.get(i)));
                }

                return true;
            }
        };
    }

    private Symbol intType() {
        return PROG_LANG_INTEGER;
    }

    private Expr intExpr(int i) {
        return new Literal(i, PROG_LANG_INTEGER);
    }


    private JVMExpr jvmIntExpr(int i) {
        return new JVMIntExpr(i);
    }
}
