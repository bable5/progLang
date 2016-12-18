package progLang.jvm;

import progLang.ast.AstVisitor;
import progLang.ast.BinaryExpr;
import progLang.ast.Literal;
import progLang.ast.Operator;
import progLang.jvm.tree.*;

public class AsJVMVisitor extends AstVisitor<JVMExpr, Void> {
    @Override
    public JVMExpr visitLiteral(Literal exprLiteral, Void aVoid) {
        return new JVMIntExpr(exprLiteral.value);
    }

    @Override
    public JVMExpr visitBinaryExpr(BinaryExpr binaryExpr, Void aVoid) {
        JVMOperator operator = mapOperator(binaryExpr.operator);
        JVMExpr lhs = binaryExpr.lhs.accept(this, aVoid);
        JVMExpr rhs = binaryExpr.rhs.accept(this, aVoid);
        Type type = TypeMap.type(binaryExpr.type);

        return new JVMBinaryExpr(operator, lhs, rhs, type);
    }


    private JVMOperator mapOperator(Operator operator) {
        switch (operator) {
            case ARITH_ADD:
                return JVMOperator.ARITH_ADD;
            default:
                throw new UnsupportedOperationException(String.format("Unable to map a %s", operator));
        }
    }
}
