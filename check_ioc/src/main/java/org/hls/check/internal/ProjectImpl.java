package org.hls.check.internal;

import org.hls.check.Project;
import org.hls.check.Module;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectImpl implements Project {

    @Inject private Module module;

    public ProjectImpl() {

    }

    @Inject
    public ProjectImpl(Module module) {
        this.module = module;
    }

    @Override
    public Module getModule() {
        return module;
    }
}
