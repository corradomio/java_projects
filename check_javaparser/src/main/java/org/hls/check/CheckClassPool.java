package org.hls.check;

import javassist.NotFoundException;
import jext.javassist.ClasspathElements;
import jext.javassist.Classpaths;

import java.io.File;

public class CheckClassPool {

    public static void main(String[] args) throws NotFoundException {
        // ClassPool classPool = new ClassPool(false);
        // classPool.appendClassPath("D:\\Java\\MiniJdk\\Jdk8\\rt.jar");
        // classPool.appendClassPath("D:\\Java\\MiniJdk\\Jdk8\\alt-rt.jar");
        //
        // CtClass clazz = classPool.makeClass("java.util.Map");
        // System.out.println(clazz.getName());
        // System.out.println(clazz.getGenericSignature());

        // ClassCollection cc;
        //
        // cc = new ClassCollection(new File("D:\\Java\\MiniJdk\\Jdk14"));
        // cc.initialize();
        //
        // cc.classSet().forEach(classname -> {
        //     System.out.println(cc.get(classname).getName());
        // });

        Classpaths cp = new Classpaths();
        ClasspathElements cpe = cp.getClasspathElements(new File("D:\\Java\\MiniJdk\\Jdk14"));
        cpe.keySet().forEach(className -> {
            System.out.println(cpe.get(className).getName());
        });
    }
}
