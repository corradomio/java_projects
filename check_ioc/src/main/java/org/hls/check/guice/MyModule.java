package org.hls.check.guice;

import com.google.inject.AbstractModule;
import org.hls.check.Module;
import org.hls.check.Project;
import org.hls.check.internal.ModuleImpl;
import org.hls.check.internal.ProjectImpl;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Module.class).to(ModuleImpl.class);
        bind(Project.class).to(ProjectImpl.class);
    }
}
