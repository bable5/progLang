package progLang.transformers;


import org.junit.Test;
import progLang.ast.BinaryExpr;
import progLang.ast.Expr;
import progLang.ast.Literal;
import progLang.ast.Operator;
import progLang.progLangParser;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class ExprAstGeneratorTest extends AbstractProgLangTest {

    private ExprAstGenerator visitor;

    @Override
    public void postSetup() {
        visitor = new ExprAstGenerator(context);
    }

    @Test
    public void extractInt() {
        Expr expr = givenExpr("1");

        assertThat(expr, instanceOf(Literal.class));
        Literal literal = (Literal) expr;
        assertThat(literal.value, is(1));
    }

    @Test
    public void extractAddExpr() {
        assertThat(givenExpr("1 + 2"), is(
                new BinaryExpr(Operator.ARITH_ADD, new Literal(1), new Literal(2))
        ));
    }

    @Test
    public void extractSubtractExpr() {
        assertThat(givenExpr("1 - 2"), is(
                new BinaryExpr(Operator.ARITH_MINUS, new Literal(1), new Literal(2))
        ));
    }

    @Test
    public void extractMulExpr() {
        assertThat(givenExpr("1 * 2"), is(
                new BinaryExpr(Operator.ARITH_MULT, new Literal(1), new Literal(2))
        ));
    }

    @Test
    public void extractDivExpr() {
        assertThat(givenExpr("1 / 2"), is(
                new BinaryExpr(Operator.ARITH_DIV, new Literal(1), new Literal(2))
        ));
    }

    @Test
    public void testNestedArithExpr() {
        assertThat(givenExpr("(1 / 2) + (3 * 4)"), is(
                new BinaryExpr(Operator.ARITH_ADD,
                        new BinaryExpr(Operator.ARITH_DIV, new Literal(1), new Literal(2)),
                        new BinaryExpr(Operator.ARITH_MULT, new Literal(3), new Literal(4))
                )
        ));
    }

    private Expr givenExpr(String exprText) {
        progLangParser.ExprContext exprContext = givenParser(exprText).expr();
        return visitor.visitExpr(exprContext);
    }
}
