package org.hls.scitools;

import java.util.List;
import java.util.Map;

class Template<T> { }

class TemplateExtents<T extends List<T>> { }

class ExtendsTemplate<T> extends Template<T> { }

abstract class ImplementsMap<K, T> implements Map<List<K>, Map<K, List<T>>> { }

abstract class AbstractMap<K, T> implements Map<List<K>, Map<K, List<T>>> { }

public class ExampleFields {
    int i;
    int[][][] ia3;
    String s;
    String[][][] sa3;
    List<String> ls;
    Map<String, String> mss;
    List<Map<String,String>> lmss;
    Map<List<String>,Map<String, List<String>>> mlsmsls;
    Map<String, String[][][]> mssa3;
    Map<List<String>,Map<String, List<String[][][]>>> mlsmslsa3;
    List<String>[][][] lsa3;
    List<String[][][]>[][][] lsa3a3;
    List<List<String[][][]>[][][]>[][][] llsa3a3a3;
}
