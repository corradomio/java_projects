package org.hls.template;

import java.util.ArrayList;
import java.util.List;

public class OtherClass {

    String   s1, s2[];
    String[] s3, s4[];

    List<String>        ls   = new ArrayList<>();
    List<String>[]      lsa  = new List[10];
    List<String[]>      las  = new ArrayList<>();
    List<String[]>[]    lsas = new List[10];

    List<List<String>>      lls;
    List<List<String>[]>    llsa;
    List<List<String[]>>    llas;
    List<List<String[]>[]>  llsas;

    String[] m1()   { return null; }
    String   m2()[] { return null;}

    <T> List<T> m() { return null; }
}
