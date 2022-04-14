package org.hls.vars;

import org.hls.cdecl.AEnum;
import org.hls.deps.ClassFive;
import org.hls.deps.ClassFour;
import org.hls.deps.ClassOne;
import org.hls.deps.ClassThree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class LocalVars
 */
public class LocalVars {

    List<List<String>> one;

    /**
     * Method m
     * @param p1
     * @param p2
     */
    void m(Map<Integer, String> p1, Set<AEnum> p2) {
        Map<List<String>,List<Integer>> three = new HashMap<>();

        {
            List<Map<ClassOne, ClassThree>> four = new ArrayList<>();
        }

        {
            Set<Map<ClassThree, ClassOne>> four = new HashSet<>();
        }

        for(int i=0; i<10; ++i) {
            Set<Map<ClassFour, ClassFive>> five = new TreeSet<>();
        }
    }
}
