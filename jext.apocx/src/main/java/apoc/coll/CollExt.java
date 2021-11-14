package apoc.coll;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CollExt {

    @UserFunction
    @Description("apoc.coll.arrayGet(coll, index) | get coll[index] value, supporting indices < 0")
    public Object arrayGet(@Name("coll") List<Object> coll, @Name("index") long index) {
        if (coll == null)
            return null;

        long n = coll.size();
        if (index < 0)
            index = n+index;

        if (index >= 0 && index < n)
            return coll.get((int)index);
        else
            return null;
    }

    @UserFunction
    @Description("apoc.coll.arraySet(coll, index, value) | set coll[index] to value, extend coll if necessary")
    public List<Object> arraySet(@Name("coll") List<Object> coll, @Name("index") long index, @Name("value") Object value) {
        // if (coll == null) return null;
        // if (index < 0 || value == null || index >= coll.size()) return coll;
        //
        // List<Object> list = new ArrayList<>(coll);
        // list.set( (int) index, value );
        // return list;
        if (index < 0)
            return null;

        value = makeArray(value);

        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);

        // remove the element coll[index]
        if (value == null) {
            if (index < list.size())
                list.remove((int) index);
            return list;
        }

        Object lastValue;
        int n = list.size();
        if (n > 0)
            lastValue = list.get(n-1);
        // else if (value instanceof Boolean)
        //     lastValue = false;
        // else if (value instanceof Integer)
        //     lastValue = 0;
        // else if (value instanceof Long)
        //     lastValue = 0L;
        // else if (value instanceof Float)
        //     lastValue = 0.f;
        // else if (value instanceof Double)
        //     lastValue = 0.;
        // else if (value instanceof String)
        //     lastValue = "";
        // else
        //     throw new IllegalArgumentException(value.toString());
        else
            lastValue = defaultValue(value);

        while (list.size() <= index)
            list.add(lastValue);

        list.set( (int) index, value);
        return list;
    }

    @Deprecated
    @UserFunction
    @Description("apoc.coll.setOrExtend(coll, index, value) | set coll[index] to value, extend coll if necessary")
    public List<Object> setOrExtend(@Name("coll") List<Object> coll, @Name("index") long index, @Name("value") Object value) {
        return arraySet(coll, index, value);
    }

    private static Object makeArray(Object value) {
        if (!(value instanceof Collection))
            return value;
        Collection c = (Collection) value;
        if (c.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();
        for (Object e : c) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(e.toString());
        }
        return sb.toString();
    }

    private static Object defaultValue(Object value) {
        Object lastValue;
        if (value instanceof Boolean)
            lastValue = false;
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
    @Description("apoc.coll.append(coll, value) | append value to collection")
    public List<Object> append(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null)
            return coll;

        value = makeArray(value);

        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        list.add(value);
        return list;
    }

    @UserFunction
    @Description("apoc.coll.appendDistinct(coll, value) | append value to collection without duplicates")
    public List<Object> appendDistinct(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null)
            return coll;

        value = makeArray(value);

        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        if (!list.contains(value))
            list.add(value);
        return list;
    }

    @UserFunction
    @Description("apoc.coll.resize(coll, length) | resize the collection")
    public List<Object> resize(@Name("coll") List<Object> coll, @Name("length") long length) {
        if (coll == null || coll.isEmpty())
            return coll;

        int size = coll.size();
        List<Object> list;

        if (length <= size) {
            list = coll.subList(0, (int)length);
        }
        else {
            Object lastObject = coll.get(size-1);
            list = new ArrayList<>(coll);
            while (list.size() < length)
                list.add(lastObject);
        }
        return list;
    }

    //
    // support for 2-dimensional arrays
    //

    @UserFunction
    @Description("apoc.coll.array2Set(coll, index1, index2, value) | set coll[index1,index2] to value, extend coll if necessary")
    public List<Object> array2Set(@Name("coll") List<Object> coll, @Name("index1") long index1, @Name("index2") long index2, @Name("value") Object value) {
        if (value == null)
            return null;

        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        while (list.size() <= index1)
            list.add("");
        String eltList = (String) list.get((int)index1);
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
        list.set((int)index1, sb.toString());

        return list;
    }

    @Deprecated
    @UserFunction
    @Description("apoc.coll.setOrExtend2(coll, index1, index2, value) | set coll[index1,index2] to value, extend coll if necessary")
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
    @Description("apoc.coll.append2(coll, index, value) | append value to collection")
    public List<Object> append2(@Name("coll") List<Object> coll,  @Name("value") long index, @Name("value") Object value) {
        if (value == null)
            return null;
        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        while (list.size() <= index)
            list.add("");
        String seq = (String) list.get((int)index);
        if (seq.isEmpty())
            seq = value.toString();
        else
            seq = seq + "," + value;
        list.set((int)index, seq);
        return list;
    }

    //
    // support for 2-dimensional lists
    //

    @UserFunction
    @Description("apoc.coll.appendDistinct2(coll, index, value) | append value to collection")
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
    @Description("apoc.coll.asArray2(coll, index, value) | Convert coll in an array of array of type")
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
