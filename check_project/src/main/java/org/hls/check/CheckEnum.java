package org.hls.check;

enum TypeUse {
    NONE,       // no type use defined
    ALL,        // EXTENDS or IMPLEMENTS or DEPENDS_ON
    HIERARCHY,  // EXTENDS or IMPLEMENTS
    EXTENDS,
    IMPLEMENTS,
    DEPENDS_ON
}

public class CheckEnum {

    public static void main(String[] args) {
        System.out.println(TypeUse.NONE.name());
    }
}
