package org.hls.check.guice.internal;

import org.hls.check.guice.Module;

public class ModuleImpl implements Module {

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
