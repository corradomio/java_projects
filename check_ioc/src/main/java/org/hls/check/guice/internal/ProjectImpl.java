package org.hls.check.guice.internal;

import org.hls.check.guice.Project;
import org.hls.check.guice.Module;

import javax.inject.Inject;

public class ProjectImpl implements Project {

    @Inject private Module module;

    @Override
    public Module getModule() {
        return module;
    }
}
