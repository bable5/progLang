package progLang.jvm.tree;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class JVMMethod {
    public final List<Flag> flags;
    public final Type type;
    public final List<?> formalArguments;
    public final List<JVMStmt> body;
    public final String name;

    public JVMMethod(List<Flag> flags, Type type, String name, List<?> formalArguments, List<JVMStmt> body) {
        this.flags = unmodifiableList(flags);
        this.type = type;
        this.name = name;
        this.formalArguments = unmodifiableList(formalArguments);
        this.body = unmodifiableList(body);
    }
}
