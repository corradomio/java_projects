package apoc.coll;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.ArrayList;
import java.util.List;

public class CollExt {

    @UserFunction
    @Description("apoc.coll.setOrExtend(coll, index, value) | set index to value, extend the list if necessary")
    public List<Object> setOrExtend(@Name("coll") List<Object> coll, @Name("index") long index, @Name("value") Object value) {
        // if (coll == null) return null;
        // if (index < 0 || value == null || index >= coll.size()) return coll;
        //
        // List<Object> list = new ArrayList<>(coll);
        // list.set( (int) index, value );
        // return list;
        if (value == null) return null;

        List<Object> list;
        Object lastValue = null;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        int n = list.size();
        if (n > 0)
            lastValue = list.get(n-1);
        else if (value instanceof Boolean)
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

        while (list.size() <= index)
            list.add(lastValue);
        list.set( (int) index, value);
        return list;
    }

    @UserFunction
    @Description("apoc.coll.append(coll, value) | append the value to the collection")
    public List<Object> append(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null) return null;
        List<Object> list;
        if (coll == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(coll);
        list.add(value);
        return list;
    }

    @UserFunction
    @Description("apoc.coll.appendDistinct(coll, value) | append the value to the collection without duplicates")
    public List<Object> appendDistinct(@Name("coll") List<Object> coll, @Name("value") Object value) {
        if (value == null) return null;
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

        List<Object> list = new ArrayList<>();

        if (length < coll.size()) {
            for (Object elt : coll)
                if (list.size() == length)
                    break;
                else
                    list.add(elt);
        }
        else {
            Object lastObject = coll.get(coll.size()-1);
            list.addAll(coll);
            while (list.size() < length)
                list.add(lastObject);
        }
        return list;
    }

}
