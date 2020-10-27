package jext.springframework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class SpringBeans {
    @Autowired
    private ApplicationContext applicationContext;

    public SpringBeans() { }

    public SpringBeans(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void dumpBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
            System.out.println(beanName );
    }
}
