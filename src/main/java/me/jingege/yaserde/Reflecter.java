package me.jingege.yaserde;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Act as a yaserder's factory.A constructor with no arguments is necessary.
 * User: jingege
 * Datetime: 9/3/13 2:23 PM
 */
public class Reflecter {
    //Yaserder's constructor cache
    private static final Map<Class<?>, Constructor<?>> CONSTRUCTOR_CACHE =
            new ConcurrentHashMap<Class<?>, Constructor<?>>();

    public static final Class[] EMPTY_ARRAY = new Class[]{};

    /**
     * Create yaserder instances by the non-argument constructor
     * @param clazz yaserder's class
     * @param <T> instance to deflat data to
     * @return instance
     */
    public static <T extends Yaserder> T newInstance(Class<T> clazz) {
        T t = null;
        Constructor<T> constructor = (Constructor<T>) CONSTRUCTOR_CACHE.get(clazz);
        if(constructor == null){
            try {
                constructor = clazz.getDeclaredConstructor(EMPTY_ARRAY);
                constructor.setAccessible(true);
                CONSTRUCTOR_CACHE.put(clazz,constructor);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            t = constructor.newInstance(EMPTY_ARRAY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return t;
    }
}
