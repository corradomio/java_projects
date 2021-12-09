package org.hls.cdecl;

public class OuterClass {

    public static class InnerStaticClass {}
    public class InnerClass {
        public class Inner2Class {
            public class Inner3Class {

            }
        }
    }
    public /*static*/ interface InnerInterface {}

    // Anonymous class based on Interface
    public InnerInterface iifield = new InnerInterface() {

    };

    // Anonymous class based on Interface
    public InnerClass icfield = new InnerClass() {

    };

}
