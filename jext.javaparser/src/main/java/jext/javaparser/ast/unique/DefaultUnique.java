package jext.javaparser.ast.unique;

import com.github.javaparser.ast.Node;
import jext.javaparser.ast.Symbols;
import jext.javaparser.ast.UniqueSymbols;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class DefaultUnique extends NoneUnique {

    public DefaultUnique(UniqueSymbols us) {
        super(us);
    }

    @Override
    public void analyze(Object o) {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : fields)
            analyze(field, o);
        for (Field field : declaredFields)
            analyze(field, o);
    }

    protected void analyze(Field field, Object o) {
        String fname = field.getName();
        if (uppercase(fname)) return;

        if (field.getType().equals(String.class)) {
            makeUnique(field, o);
            return;
        }

        field.setAccessible(true);
        try {
            Object fo = field.get(o);
            us.analyze(fo);
        } catch (IllegalAccessException e) {

        }

    }

    private void makeUnique(Field field, Object o) {
        field.setAccessible(true);
        try {
            String value = (String) field.get(o);
            field.set(o, us.unique(value));
        } catch (IllegalAccessException e) {

        }
    }

    private static final Pattern UPPERCASE = Pattern.compile("[A-Z_]+");

    private boolean uppercase(String fname) {
        return UPPERCASE.matcher(fname).matches();
    }
}
