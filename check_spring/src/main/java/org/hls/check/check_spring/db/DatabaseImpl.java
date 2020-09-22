package org.hls.check.check_spring.db;

import org.hls.check.check_spring.Database;

public class DatabaseImpl implements Database {

    private static int gid = 0;

    private int id;

    public DatabaseImpl() {
        id = ++gid;
    }

    @Override
    public String getName() {
        return DatabaseImpl.class.getName() + id;
    }
}
