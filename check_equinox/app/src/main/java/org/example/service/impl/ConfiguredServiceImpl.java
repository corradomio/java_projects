package org.example.service.impl;

import org.example.service.ConfiguredService;
import org.example.service.ServiceConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@Component(service = ConfiguredService.class)
@Designate(ocd = ServiceConfig.class)
public class ConfiguredServiceImpl implements ConfiguredService {
    @Activate
    protected void activate(ServiceConfig config) {
        boolean isEnabled = config.enabled();
        int retries = config.maxRetries();
    }
}
