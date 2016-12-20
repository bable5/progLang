package progLang.jvm.bytecode;


import org.apache.bcel.generic.*;
import progLang.jvm.tree.JVMBinaryExpr;
import progLang.jvm.tree.JVMIntExpr;
import progLang.jvm.tree.JVMReturnStmt;
import progLang.jvm.tree.JVMVisitor;

public class ByteCodeVisitor implements JVMVisitor<Void, InstructionList> {
    @Override
    public Void visitReturnStmt(JVMReturnStmt returnStmt, InstructionList il) {
        returnStmt.expr.accept(this, il);
        il.append(InstructionConst.IRETURN);
        return null;
    }

    @Override
    public Void visitIntExpr(JVMIntExpr jvmIntExpr, InstructionList il) {
        il.append(new BIPUSH((byte)jvmIntExpr.value));
        return null;
    }

    @Override
    public Void visitBinaryExpr(JVMBinaryExpr jvmBinaryExpr, InstructionList il) {
        jvmBinaryExpr.lhs.accept(this, il);
        jvmBinaryExpr.rhs.accept(this, il);

        switch (jvmBinaryExpr.operator) {
            case ARITH_ADD:
                il.append(InstructionConst.IADD);
                break;
            case ARITH_MULT:
                il.append(InstructionConst.IMUL);
                break;
            default:
                throw new UnsupportedOperationException("Unknown operator " + jvmBinaryExpr.operator);
        }

        return null;
    }
}
