package progLang.jvm.tree;

public abstract class JVMCompilationUnit {
    public final String packageName;
    public final String compilationUnitName;

    protected JVMCompilationUnit(String packageName, String compilationUnitName) {
        this.packageName = packageName;
        this.compilationUnitName = compilationUnitName;
    }
}
