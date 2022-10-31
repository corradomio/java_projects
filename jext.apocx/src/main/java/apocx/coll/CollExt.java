package apocx.coll;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CollExt {

    @UserFunction
    @Description("apocx.coll.arrayIncr(coll, index, value) | increment coll[index] value")
    public Object arrayIncr(@Name("coll") List<Object> coll, @Name("index") long index, @Name("incr") long incr) {
        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);

        while (coll.size() <= index)
            coll.add(0L);

        long value = ((Number)coll.get((int)index)).longValue();
        coll.set( (int) index, Long.valueOf(value + incr));
        return coll;
    }

    @UserFunction
    @Description("apocx.coll.arrayGet(coll, index) | get coll[index] value, supporting indices < 0")
    public Object arrayGet(@Name("coll") List<Object> coll, @Name("index") long index) {
        if (coll == null)
            return null;

        if (index >= 0 && index < coll.size())
            return coll.get((int)index);
        else
            return null;
    }

    @UserFunction
    @Description("apocx.coll.arraySet(coll, index, value) | set coll[index] to value, extend coll if necessary")
    public List<Object> arraySet(@Name("coll") List<Object> coll, @Name("index") long index, @Name("value") Object value) {
        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);

        int n = coll.size();
        if (index >= n) {
            Object lastValue;
            if (n > 0)
                lastValue = coll.get(n-1);
            else
                lastValue = defaultValue(value);

            while (coll.size() <= index)
                coll.add(lastValue);
        }

        coll.set( (int) index, value);
        return coll;
    }

    private static Object defaultValue(Object value) {
        Object lastValue;
        if (value instanceof Boolean)
            lastValue = Boolean.FALSE;
        else if (value instanceof Integer)
            lastValue = 0;
        else if (value instanceof Long)
            lastValue = 0L;
        else if (value instanceof Float)
            lastValue = 0.f;
        else if (value instanceof Double)
            lastValue = 0.;
        else if (value instanceof String)
            lastValue = "";
        else
            throw new IllegalArgumentException(value.toString());
        return lastValue;
    }

    @UserFunction
    @Description("apocx.coll.append(coll, value) | append value to collection")
    public List<Object> append(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null)
            return coll;

        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);
        coll.add(value);
        return coll;
    }

    @UserFunction
    @Description("apocx.coll.appendDistinct(coll, value) | append value to collection without duplicates")
    public List<Object> appendDistinct(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null)
            return coll;

        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);
        if (!coll.contains(value))
            coll.add(value);
        return coll;
    }

    @UserFunction
    @Description("apocx.coll.resize(coll, length) | resize the collection")
    public List<Object> resize(@Name("coll") List<Object> coll, @Name("length") long length) {
        if (coll == null || coll.isEmpty())
            return coll;

        int size = coll.size();

        if (length <= size) {
            coll = coll.subList(0, (int)length);
        }
        else {
            Object lastObject = coll.get(size-1);
            coll = new ArrayList<>(coll);
            while (coll.size() < length)
                coll.add(lastObject);
        }
        return coll;
    }

    //
    // support for 2-dimensional arrays
    //

    @UserFunction
    @Description("apocx.coll.array2Set(coll, index1, index2, value) | set coll[index1,index2] to value, extend coll if necessary")
    public List<Object> array2Set(@Name("coll") List<Object> coll, @Name("index1") long index1, @Name("index2") long index2, @Name("value") Object value) {
        if (value == null)
            return null;

        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);
        while (coll.size() <= index1)
            coll.add("");
        String eltList = (String) coll.get((int)index1);
        List<String> elts = split(eltList);
        String lastValue;
        if (elts.size() > 0)
            lastValue = elts.get(elts.size()-1);
        else
            lastValue = defaultValue(value).toString();
        while (elts.size() <= index2)
            elts.add(lastValue);
        elts.set( (int) index2, value.toString());

        StringBuilder sb = new StringBuilder(elts.get(0));
        for(int i=1; i<elts.size(); ++i)
            sb.append(",").append(elts.get(i));
        coll.set((int)index1, sb.toString());

        return coll;
    }

    @Deprecated
    @UserFunction
    @Description("apocx.coll.setOrExtend2(coll, index1, index2, value) | set coll[index1,index2] to value, extend coll if necessary")
    public List<Object> setOrExtend2(@Name("coll") List<Object> coll, @Name("index1") long index1, @Name("index2") long index2, @Name("value") Object value) {
        return array2Set(coll, index1, index2, value);
    }

    private static List<String> split(String s) {
        List<String> list = new ArrayList<>();
        if (s.isEmpty()) return list;
        String[] elist = s.split(",");
        list.addAll(Arrays.asList(elist));
        return list;
    }


    @UserFunction
    @Description("apocx.coll.append2(coll, index, value) | append value to collection")
    public List<Object> append2(@Name("coll") List<Object> coll,  @Name("value") long index, @Name("value") Object value) {
        if (value == null)
            return null;

        if (coll == null)
            coll = new ArrayList<>();
        else
            coll = new ArrayList<>(coll);
        while (coll.size() <= index)
            coll.add("");

        String seq = (String) coll.get((int)index);
        if (seq.isEmpty())
            seq = value.toString();
        else
            seq = seq + "," + value;

        coll.set((int)index, seq);
        return coll;
    }

    //
    // support for 2-dimensional lists
    //

    @UserFunction
    @Description("apocx.coll.appendDistinct2(coll, index, value) | append value to collection")
    public List<Object> appendDistinct2(@Name("coll") List<Object> coll,  @Name("value") long index, @Name("value") Object value) {
        if (value == null)
            return null;
        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        while (list.size() <= index)
            list.add("");
        String svalue = value.toString();
        String seq = list.get((int)index).toString();
        String[] elts = seq.split(",");
        for (String elt : elts)
            if (elt.equals(svalue))
                return list;
        if (seq.isEmpty())
            seq = value.toString();
        else
            seq = seq + "," + value;
        list.set((int)index, seq);
        return list;
    }

    @UserFunction
    @Description("apocx.coll.asArray2(coll, index, value) | Convert coll in an array of array of type")
    public List<List<Object>> asArray2(@Name("coll") List<Object> coll, @Name("type") String type) {
        if (coll == null)
            return null;
        if (coll.isEmpty())
            return Collections.emptyList();

        Function<String,Object> cvt;
        if (type.startsWith("int") || type.startsWith("long"))
            cvt = Long::valueOf;
        else if (type.startsWith("float") || type.startsWith("double"))
            cvt = Double::valueOf;
        else if (type.startsWith("bool"))
            cvt = Boolean::valueOf;
        else
            cvt = (s) -> s;

        List<List<Object>> list = new ArrayList<>();

        for (Object obj : coll) {
            String eltList = (String) obj;
            if (eltList.isEmpty()) {
                list.add(Collections.emptyList());
                continue;
            }

            List<Object> values = new ArrayList<>();
            Object value;
            String[] elts = eltList.split(",");
            for (String elt : elts) {
                value = cvt.apply(elt);
                values.add(value);
            }

            list.add(values);
        }

        return list;
    }
}
