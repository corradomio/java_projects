package types;

import java.util.List;
import java.util.Map;

class Template<T> { }

class ExtendsTemplate<T> extends Template<T> { }

abstract class ImplementsMap<K, T> implements Map<List<K>, Map<K, List<T>>> { }

abstract class AbstractMap<K, T> implements Map<List<K>, Map<K, List<T>>> { }

public class ExampleFields {

    int i;
    int[][][] iaaa;

    String s;
    String[][][] saaa;
    List<String> ls;
    Map<String, String> mss;
    List<Map<String,String>> lmss;
    Map<List<String>,Map<String, List<String>>> mlsmsls;
    Map<String, String[][][]> mssaaa;
    Map<List<String>,Map<String, List<String[][][]>>> mlsmslsaaa;
}
