package org.hls.check;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hls.check.guice.MyModule;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello IoC world");

        Injector injector = Guice.createInjector(new MyModule());
        Project project = injector.getInstance(Project.class);

        System.out.println(project.getModule().getName());
    }

}
