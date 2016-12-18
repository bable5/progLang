package progLang.jvm.tree;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class JVMMethod {
    public final List<Flag> flags;
    public final Type type;
    public final List<?> formalArguments;
    public final List<JVMStmt> body;

    public JVMMethod(List<Flag> flags, Type type, List<?> formalArguments, List<JVMStmt> body) {
        this.flags = unmodifiableList(flags);
        this.type = type;
        this.formalArguments = unmodifiableList(formalArguments);
        this.body = unmodifiableList(body);
    }
}
