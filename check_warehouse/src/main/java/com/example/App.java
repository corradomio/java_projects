package com.example;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import lombok;

class Location {

}

class Warehouse {

}

/**
 * Hello world!
 *
 */
public class App 
{

    private static List<Location> loadLocations(LinkedHashMap<String, Object> lmap) {
        List<Location> locations = new ArrayList<>();

        return locations;
    }

    private static List<Warehouse> locadWarehouses(LinkedHashMap<String, Object> wmap) {
        List<Warehouse> warehouses = new ArrayList<>();

        return warehouses;
    }


    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );

        LinkedHashMap<String, Object> hm = JSONUtils.load(new File("warehouses_locations.json"), LinkedHashMap.class);
        System.out.println();
    }
}
