package org.example.service.impl;

import org.example.service.AnotherService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Modified;

import org.example.service.FirstService;

@Component(
    service = FirstService.class,
    immediate = true
)
public class FirstServiceImpl implements FirstService {
    // Inject another OSGi service dependency
    @Reference
    private AnotherService anotherService;

    @Activate
    protected void activate() {
        // Called when the component is initialized
    }

    @Modified
    protected void modified() {
        // Called when component configuration changes
    }

    @Deactivate
    protected void deactivate() {
        // Called when the component is stopped or destroyed
    }

    @Override
    public void performAction() {
        anotherService.execute();
    }
}
