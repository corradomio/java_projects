package org.example;

public class Coords {

    public static Coords of(float x, float y, float z) {
        return new Coords(x, y, z);
    }

    public final float x, y, z;

    private Coords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
