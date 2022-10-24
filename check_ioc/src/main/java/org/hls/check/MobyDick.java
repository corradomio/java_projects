package org.hls.check;

import javax.inject.Named;

@Named
public class MobyDick implements Book {
    @Override
    public String getName() {
        return MobyDick.class.getName();
    }
}