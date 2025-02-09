package jext.lang;

/**
 * Used to force method ``clone'' ``public''.
 * @param <T>
 */
public interface Cloneable<T> {
    T clone();
}
