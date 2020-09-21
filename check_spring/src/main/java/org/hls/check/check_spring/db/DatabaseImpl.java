package org.hls.check.check_spring.db;

import org.hls.check.check_spring.Database;

public class DatabaseImpl implements Database {

    private static int id = 0;

    @Override
    public String getName() {
        return DatabaseImpl.class.getName() + Integer.toString(++id);
    }
}
