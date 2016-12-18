package progLang.jvm;

import org.junit.Test;
import progLang.ast.BinaryExpr;
import progLang.ast.Expr;
import progLang.ast.Literal;
import progLang.ast.Operator;
import progLang.jvm.tree.JVMBinaryExpr;
import progLang.jvm.tree.JVMIntExpr;
import progLang.jvm.tree.JVMOperator;
import progLang.jvm.tree.Type;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static progLang.type.DefaultTypes.PROG_LANG_INTEGER;

public class AsJVMVisitorTest extends AbstractProgLangTest {

    private AsJVMVisitor visitor;

    @Override
    protected void postSetup() {
        super.postSetup();
        visitor = new AsJVMVisitor();
    }

    @Test
    public void visitLiteral() throws Exception {
        Expr expr = givenIntLiteral(1);

        JVMIntExpr visited = (JVMIntExpr) expr.accept(visitor, null);

        assertThat(visited.value, is(1));
    }


    @Test
    public void visitBinaryExpr() throws Exception {
        Expr expr = givenArithExpr(givenIntLiteral(1), givenIntLiteral(3));

        JVMBinaryExpr visited = (JVMBinaryExpr) (expr.accept(visitor, null));
        assertThat(visited.operator, is(JVMOperator.ARITH_ADD));
        assertThat(visited.type, is(Type.INT_TYPE));
        assertThat(visited.lhs, is(new JVMIntExpr(1)));
        assertThat(visited.rhs, is(new JVMIntExpr(3)));
    }

    private Expr givenArithExpr(Expr lhs, Expr rhs) {
        return new BinaryExpr(Operator.ARITH_ADD, lhs, rhs, PROG_LANG_INTEGER);
    }

    private Literal givenIntLiteral(int i) {
        return new Literal(i, PROG_LANG_INTEGER);
    }
}
