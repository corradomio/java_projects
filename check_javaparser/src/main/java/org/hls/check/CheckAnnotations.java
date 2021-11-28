package org.hls.check;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class CheckAnnotations {

    public static void main(String[] args) {
        ClassOrInterfaceDeclaration n = null;
        n.getAnnotations();
    }
}
