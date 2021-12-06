package jext.android;

import jext.util.MapUtils;

import java.util.Map;

public interface AndroidConstants {

    Map<String,String> ANDROID_VERSIONS = MapUtils.asMap(
        "honeycomb", "3.0.0",
        "icecream", "4.0.0",
        "jellybean", "4.1.0",
        "kitkat", "4.4.0",
        "lollipop", "5.0.0",
        "marshmallow", "6.0.0",
        "nougat", "7.0.0",
        "oreo", "8.0.0",
        "pie", "9.0.0"
    );

}
