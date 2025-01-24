package examples.set4;

import jext.text.Tabulate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        Random rnd = new Random();
        Tabulate t = new Tabulate()
            .header("ciccio", "pasticcio")
            .realFormat("%.3f");

        for (int i=0; i<10; ++i) {
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < 2; ++j) {
                data.add(rnd.nextInt(1000000));
            }
            t.addRecord(data);
        }
        t.addRecord("false", "true");
        t.addRecord("precipitevolissimevolmente", Math.PI);

        t.print();
    }
}
