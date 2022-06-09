package jext.sourcecode.project.java;

import jext.util.MapUtils;

import java.util.Map;

public interface AndroidConstants {

    String ANDROID = "android";

    Map<String,String> ANDROID_VERSIONS = MapUtils.asMap(
        "cupcacke", "1.5.0",
        "donut", "1.6.0",
        "enclair", "2.0.0",
        "froyo", "2.2.0",
        "gingerbread", "2.3.0",

        "honeycomb", "3.0.0",
        "icecream", "4.0.0",
        "jellybean", "4.1.0",
        "kitkat", "4.4.0",
        "lollipop", "5.0.0",
        "marshmallow", "6.0.0",
        "nougat", "7.0.0",
        "oreo", "8.0.0",
        "pie", "9.0.0",
        "q", "10.0.0",
        "r", "11.0.0",
        "s", "12.0.0"
    );

}
