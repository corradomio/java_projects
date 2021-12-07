package jext.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ClassClosure {

    public static Set<Class<?>> of(Object obj) {
        Set<Class<?>> closure = new HashSet<>();
        if (obj == null)
            return closure;

        Queue<Class<?>> waiting = new LinkedList<>();
        if (obj instanceof Class<?>)
            waiting.add((Class<?>) obj);
        else
            waiting.add(obj.getClass());
        while (!waiting.isEmpty()) {
            Class<?> clazz = waiting.remove();
            if (closure.contains(clazz))
                continue;

            closure.add(clazz);
            System.out.println(clazz.getName());

            add(waiting, clazz.getSuperclass());
            addAll(waiting, clazz.getInterfaces());
            addAll(waiting, clazz.getClasses());

            for (Field f : clazz.getDeclaredFields())
                add(waiting, f.getType());
        }

        return closure;
    }

    private static void add(Queue<Class<?>> waiting, Class<?> clazz) {
        if (clazz == null) return;
        while (clazz.isArray())
            clazz = clazz.getComponentType();
        waiting.add(clazz);
    }

    private static void addAll(Queue<Class<?>> waiting, Class<?>[] clazzes) {
        for (Class<?> clazz : clazzes)
            add(waiting, clazz);
    }
}
