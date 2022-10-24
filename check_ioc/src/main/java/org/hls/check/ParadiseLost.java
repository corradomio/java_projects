package org.hls.check;

import javax.inject.Named;

@Named
public class ParadiseLost implements Book {
    @Override
    public String getName() {
        return ParadiseLost.class.getName();
    }
}
