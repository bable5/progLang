package progLang.jvm.bytecode;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.*;
import org.apache.commons.lang3.StringUtils;
import progLang.jvm.tree.JVMClass;
import progLang.jvm.tree.JVMCompilationUnit;
import progLang.jvm.tree.JVMMethod;
import progLang.util.Context;

import static org.apache.bcel.Const.ACC_PUBLIC;
import static org.apache.bcel.Const.ACC_SUPER;

public class Generator {
    public static Generator instance(Context context) {
        Generator gen = context.get(Generator.class);
        if (gen == null) {
            gen = new Generator();
            context.put(Generator.class, gen);
        }
        return gen;
    }

    private final ByteCodeVisitor byteCodeVisitor;

    private Generator() {
        byteCodeVisitor = new ByteCodeVisitor();
    }


    public JavaClass generate(JVMCompilationUnit compilationUnit) {
        ClassGen cg = new ClassGen(fullyQualifiedName(compilationUnit), "java.lang.Object", compilationUnit.compilationUnitName + ".class", accessFlags(compilationUnit), null);

        if (compilationUnit instanceof JVMClass) {
            return generateClass(cg, (JVMClass) compilationUnit);
        } else {
            throw new UnsupportedOperationException("Unknown compilation unit type");
        }
    }

    private String fullyQualifiedName(JVMCompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isNoneEmpty(compilationUnit.packageName)) {
            sb.append(compilationUnit.packageName);
            sb.append(".");
        }

        sb.append(compilationUnit.compilationUnitName);
        return sb.toString();
    }

    private JavaClass generateClass(ClassGen cg, JVMClass clazz) {
        generateConstructor(cg, clazz);

        clazz.methods.forEach(m -> {
            generateMethod(m, cg);
        });

        return cg.getJavaClass();
    }

    private void generateConstructor(ClassGen cg, JVMClass clazz) {
        cg.addEmptyConstructor(ACC_PUBLIC);
    }

    private void generateMethod(JVMMethod method, ClassGen cg) {
        InstructionList il = new InstructionList();

        MethodGen mg = new MethodGen(ACC_PUBLIC,
                Type.INT, new Type[]{},
                new String[]{}, method.name, cg.getClassName(), il, cg.getConstantPool());

        il.append(InstructionConst.ALOAD_0);

        method.body.forEach(s -> {
            System.out.println(s);
            s.accept(byteCodeVisitor, il);
        });

        mg.setMaxLocals();
        mg.setMaxStack();

        cg.addMethod(mg.getMethod());

        il.dispose();
    }

    private int accessFlags(JVMCompilationUnit compilationUnit) {
        return ACC_PUBLIC | ACC_SUPER;
    }
}
