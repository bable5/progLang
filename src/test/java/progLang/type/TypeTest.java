package progLang.type;

import org.junit.Test;
import org.mockito.Mock;
import progLang.ast.*;
import progLang.testUtils.AbstractProgLangTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TypeTest extends AbstractProgLangTest {
    private Type typeChecker;

    @Mock
    private TypeUnifier unifier;

    @Override
    protected void postSetup() {
        context.put(TypeUnifier.class, unifier);
        when(unifier.unify(any(TypeEnv.class), any(Symbol.class), any(Symbol.class))).thenThrow(new UnificationExpection());

        this.typeChecker = new Type(context);
    }

    @Test
    public void typeLiteral() {
        Literal expr = new Literal(1);

        TypeEnv env = givenEmptyStore();

        TypeCheckResult result = expr.accept(typeChecker, env);
        assertThat(result.expr, is(expr));
        assertThat(result.expr.type, is(integerType()));
        assertThat(result.env, is(env));
    }

    @Test
    public void typeBinaryExpr() {
        Literal expr1 = new Literal(1);
        Literal expr2 = new Literal(2);
        BinaryExpr expr = new BinaryExpr(Operator.ARITH_ADD, expr1, expr2);

        TypeEnv env = givenEmptyStore();
        givenExprsUnifyTo(env, integerType(), integerType(), integerType());

        TypeCheckResult result = expr.accept(typeChecker, env);
        assertThat(result.expr, is(expr));
        assertThat(result.expr.type, is(integerType()));
        assertThat(result.env, is(env));
    }

    private void givenExprsUnifyTo(TypeEnv env, Symbol s1, Symbol s2, Symbol unifiedType) {
        doReturn(unifiedType).when(unifier).unify(env, s1, s2);
    }

    private TypeEnv givenEmptyStore() {
        return new TypeEnv();
    }

    private Symbol integerType() {
        return new Symbol("progLang.Integer");
    }


}
