package apocx.util;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

public class Compatibility {
    @UserFunction
    @Description("apocx.util.toInt(value) | convert the object in an integer")
    public Long toInt(@Name("value") Object value) {
        if (value == null)
            return 0L;
        if (value instanceof Number)
            return ((Number)value).longValue();
        if (value instanceof String)
            return Long.parseLong((String)value);
        else
            return Long.parseLong(value.toString());
    }
}
