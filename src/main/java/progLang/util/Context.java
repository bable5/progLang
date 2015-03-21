package progLang.util;

import java.util.HashMap;

public class Context {

    private HashMap<Class<?>, Object> contextSet = new HashMap<>();
    
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz) {
        return (T)contextSet.get(clazz);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T put(Class<T> clazz, T instance) {
        return (T)contextSet.put(clazz, instance);
    }
}
