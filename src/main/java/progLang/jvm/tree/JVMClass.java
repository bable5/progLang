package progLang.jvm.tree;

import java.util.Collections;
import java.util.List;

public class JVMClass extends JVMCompilationUnit {
    public final List<JVMField> fields;
    public final List<JVMMethod> methods;

    public JVMClass(String packageName, String className, List<JVMField> fields, List<JVMMethod> methods) {
        super(packageName, className);
        this.fields = Collections.unmodifiableList(fields);
        this.methods = Collections.unmodifiableList(methods);
    }
}
