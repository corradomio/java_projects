package test.p4;

import java.util.HashMap;
import java.util.function.Function;

public class TestBasic {
    HashMap<String, String> statsStorageForSession2;
    public void test() {
        HashMap<String, String> statsStorageForSession = new HashMap<>();
        Function<String, String> statsStorageProvider = statsStorageForSession::get;
        statsStorageProvider = this.statsStorageForSession2::get;
    }
}
