package emenda.staticm;

import scala.Tuple2;

public class ThisClass {

    public static void main(String[] args) {
        KeyValueGroupedDataset<String, Tuple2<String, Integer>> grouped = null;
        Dataset<Tuple2<String, Double>> aggregated = grouped.agg(
                emenda.inner.typed.avg(value -> value._2() * 2.0));
    }
}
