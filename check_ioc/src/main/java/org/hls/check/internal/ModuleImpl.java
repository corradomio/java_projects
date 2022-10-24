package org.hls.check.internal;

import org.hls.check.Module;

public class ModuleImpl implements Module {

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
