package org.hls.deps;

public class ClassOne {

    public ClassOne one() {
        return new ClassOne();
    }

    // ClassTwo f2;
    //
    // public ClassTwo two() {
    //     return new ClassTwo();
    // }

    ClassThree f3;

    public ClassThree three() {
        return new ClassThree();
    }

}
