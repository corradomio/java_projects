package org.hls.check;

import org.hls.classes.AClass;
import org.hls.classes.AEnum;
import org.hls.classes.AInterface;
import org.hls.cdecl.OuterClass;
import org.hls.missing.Missing;

public class App {

    private static Missing useMissing(Missing p) {
        return new Missing();
    }

    static void print(Class<?> clazz) {
        System.out.printf("         name: '%s'\n", clazz.getName());
        System.out.printf("   simpleName: '%s'\n", clazz.getSimpleName());
        System.out.printf("canonicalName: '%s'\n", clazz.getCanonicalName());
        System.out.println("---");
    }

    public static void main(String[] args) {

        print(AClass.class);
        print(AEnum.class);
        print(AInterface.class);
        print(OuterClass.class);
        print(OuterClass.InnerClass.class);
        print(OuterClass.InnerClass.Inner2Class.class);
        print(OuterClass.InnerClass.Inner2Class.Inner3Class.class);
        print(OuterClass.InnerStaticClass.class);
        print(OuterClass.InnerInterface.class);

        OuterClass oc = new OuterClass();
        print(oc.icfield.getClass());
        print(oc.iifield.getClass());

    }
}
