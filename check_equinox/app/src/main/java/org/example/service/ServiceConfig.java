package org.example.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Service Configuration")
public @interface ServiceConfig {
    @AttributeDefinition(name = "Enabled", description = "Turn service on or off")
    boolean enabled() default true;

    @AttributeDefinition(name = "Max Retries")
    int maxRetries() default 3;
}
