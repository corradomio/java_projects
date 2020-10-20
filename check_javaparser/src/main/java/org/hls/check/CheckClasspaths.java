package org.hls.check;

import javassist.NotFoundException;

import java.io.File;

public class CheckClasspaths {

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

    }
}
