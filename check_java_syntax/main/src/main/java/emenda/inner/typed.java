package emenda.inner;

import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.execution.aggregate.TypedAverage;
import org.apache.spark.sql.execution.aggregate.TypedCount;
import org.apache.spark.sql.execution.aggregate.TypedSumDouble;
import org.apache.spark.sql.execution.aggregate.TypedSumLong;

public class typed {

    public static <T> TypedColumn<T, Long> count(MapFunction<T, Object> f) {
        return new TypedCount<T>(f).toColumnJava();
    }

    public static <T> TypedColumn<T, Double> avg(MapFunction<T, Double> f) {
        return new TypedAverage<T>(f).toColumnJava();
    }

}
