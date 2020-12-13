package jext.springframework.http.converter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.hateoas.server.core.DefaultLinkRelationProvider;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;

public class HalHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public HalHttpMessageConverter() {
        //super(new ObjectMapper(), new MediaType("application", "hal+json", StandardCharsets.UTF_8));
        super(new ObjectMapper(), new MediaType("application", "json", StandardCharsets.UTF_8));
        objectMapper.registerModule(new Jackson2HalModule());
        objectMapper.setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(
                new DefaultLinkRelationProvider(),
                CurieProvider.NONE,
                MessageResolver.DEFAULTS_ONLY
        ));
        // customize your mapper if needed
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true; //return ResourceSupport.class.isAssignableFrom(clazz);
    }

}