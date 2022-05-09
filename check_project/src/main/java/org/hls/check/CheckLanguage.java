package org.hls.check;

import jext.sourcecode.project.GuessProjectLanguage;
import jext.util.PropertiesUtils;

import java.io.File;

public class CheckLanguage {

    public static void main(String[] args) {

        String plang = GuessProjectLanguage.guessProjectLanguage(new File(
            // "D:\\Projects\\Java\\node2vec-java-master"
            "D:\\Projects\\Python\\django-main"
        ), PropertiesUtils.empty());

        System.out.println(plang);
    }
}
