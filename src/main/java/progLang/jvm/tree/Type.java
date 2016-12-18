package progLang.jvm.tree;

public class Type<T> {
    public static final PrimitiveType INT_TYPE = new PrimitiveType(Primitive.INT);

    public final T type;

    private Type(T type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type=" + type +
                '}';
    }

    public static class PrimitiveType extends Type<Primitive> {
        public PrimitiveType(Primitive type) {
            super(type);
        }
    }

    public enum Primitive {
        INT
    }

    public static class NamedType extends Type<String> {
        public NamedType(String name) {
            super(name);
        }
    }
}
