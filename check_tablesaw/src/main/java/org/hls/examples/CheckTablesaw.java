package org.hls.examples;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.time.format.DateTimeFormatter;

public class CheckTablesaw {

    public static void main(String[] args) {
        System.out.println("Check Tablesaw");

        CsvReadOptions.Builder builder =
            CsvReadOptions
                .builder("vw_food_import_kg_train_test_area_skill.csv")
                .missingValueIndicator("(null)")
                .dateFormat(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        CsvReadOptions options = builder.build();

        Table t = Table.read().usingOptions(options);
        System.out.println(t.print());
    }
}
