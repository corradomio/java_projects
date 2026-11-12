package org.hls.example;

import org.geotools.api.data.FileDataStoreFactorySpi;
import org.geotools.api.data.FileDataStoreFinder;

import java.util.Iterator;

public class DataStores {

    public static void main(String[] args) {
        for (Iterator<FileDataStoreFactorySpi> it = FileDataStoreFinder.getAvailableDataStores(); it.hasNext(); ) {
            FileDataStoreFactorySpi fdsfspi =  it.next();

            System.out.println(fdsfspi);

        }
    }
}
