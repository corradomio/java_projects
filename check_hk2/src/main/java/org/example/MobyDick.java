package org.example;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;

@Service
@Named
public class MobyDick implements Book {

    @Override
    public String getName() {
        return getClass().getName();
    }
}
