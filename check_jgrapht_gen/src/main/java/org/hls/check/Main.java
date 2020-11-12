package org.hls.check;

import jext.csv.CSVLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static java.lang.Math.sqrt;

public class Main {

    private static double sq(double x) {
        return x*x;
    }

    private static double[] mCoeff(List<Object[]> data) {
        int nRows = data.size();
        int nCols = data.get(0).length;
        double[] min = new double[nCols];
        double[] max = new double[nCols];

        for (int i=0; i<nCols; ++i) {
            min[i] = Double.MAX_VALUE;
            max[i] = Double.MIN_VALUE;
        }

        for (int r = 0; r < nRows; ++r) {
            Object[] rec = data.get(r);
            for (int c = 0; c < nCols; ++c) {
                Object obj = rec[c];
                double value;
                if (obj instanceof Number) {
                    value = ((Number)obj).doubleValue();
                    if (value < min[c]) min[c] = value;
                    if (value > max[c]) max[c] = value;
                }
            }
        }

        for (int i=0; i<nCols; ++i) {
            if (min[i] == Double.MAX_VALUE) {
                min[i] = 0;
                max[i] = 1;
            }
        }

        double[] m = new double[nCols];

        for (int i=0; i<nCols; ++i)
            m[i] = max[i] - min[i];

        return m;
    }

    private static double weightOf(Object[] rec1, Object[] rec2, double[] m) {
        int nCols = rec1.length;
        double v, w = 0.;

        for (int i=0; i<nCols; ++i) {
            if (rec1[i] instanceof Number && rec2[i] instanceof Number) {

                double v1 = ((Number) rec1[i]).doubleValue();
                double v2 = ((Number) rec2[i]).doubleValue();

                v = (v1-v2)/m[i];
            }
            else if (rec1[i] == null || rec2[i] == null) {
                continue;
            }
            else {
                v = rec1[i].equals(rec2[i]) ? 0. : 1.;
            }
            w += sq(v);
        }

        return sqrt(w);
    }

    static void genGraph(String name, List<Object[]> data, List<Object> target) {
        int n = data.size();
        double[] m = mCoeff(data);

        String filename = String.format("%s.csv", name);

        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            for (int i=0; i<n; ++i)
            for (int j=i+1; j<n; ++j) {
                double weight = weightOf(data.get(i), data.get(j), m);

                if (weight != 0)
                    w.write(String.format("%d,%d,%f\n", i, j, weight));
                else
                    continue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = String.format("%s-target.csv", name);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            for (int i=0; i<n; ++i) {
                w.write(String.format("%d,%s\n", i, target.get(i).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void mushroom() throws IOException {
        File dataFile = new File("data/mushroom/agaricus-lepiota.data");
        List<Object[]> data = new CSVLoader()
                .withTarget(0)
                .load(dataFile)
                .getData();

        // genGraph("mushroom", data);
    }

    static void zoo() {
        File dataFile = new File("data/zoo/zoo.data");
        CSVLoader data = new CSVLoader()
                .withColumnTypes("skip", 1)
                .withColumnTypes("int", 17)
                .withTarget(17)
                .load(dataFile);

        genGraph("zoo", data.getData(), data.getTarget());
    }

    static void wine() {
        File dataFile = new File("data/wine/wine.data");
        CSVLoader data = new CSVLoader()
                .withColumnTypes("int", 1)
                .withColumnTypes("double", 13)
                .withTarget(0)
                .load(dataFile);

        genGraph("wine", data.getData(), data.getTarget());
    }

    static void auto() {
        File dataFile = new File("data/auto/imports-85.data");
        CSVLoader data = new CSVLoader()
                .withMissing("?")
                .withColumnTypes("int", 2)
                .withColumnTypes("str", 7)
                .withColumnTypes("double", 5)
                .withColumnTypes("str", 2)
                .withColumnTypes("double", 1)
                .withColumnTypes("str", 1)
                .withColumnTypes("double", 8)
                .withTarget(0)
                .load(dataFile);

        genGraph("auto", data.getData(), data.getTarget());
    }

    static void disease() {
        File dataFile = new File("data/hearth-disease/heart.csv");
        CSVLoader data = new CSVLoader()
                .withHeader(true)
                .withColumnTypes("double", 13)
                .withColumnTypes("int", 1)
                .withTarget(13)
                .load(dataFile);

        genGraph("disease", data.getData(), data.getTarget());
    }

    public static void main(String[] args) throws IOException {
        // mushroom();
        zoo();
        wine();
        auto();
        disease();
    }
}
