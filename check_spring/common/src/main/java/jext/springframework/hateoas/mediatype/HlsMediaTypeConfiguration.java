package jext.springframework.hateoas.mediatype;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.config.HypermediaMappingInformation;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.mediatype.hal.HalLinkDiscoverer;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.http.MediaType;

import java.util.List;

@Configuration(proxyBeanMethods = false)
public class HlsMediaTypeConfiguration implements HypermediaMappingInformation {

    private final LinkRelationProvider relProvider;
    private final ObjectProvider<CurieProvider> curieProvider;
    private final ObjectProvider<HalConfiguration> halConfiguration;
    private final @Qualifier("messageResolver") MessageResolver resolver;
    private final AutowireCapableBeanFactory beanFactory;

    public HlsMediaTypeConfiguration(LinkRelationProvider relProvider, ObjectProvider<CurieProvider> curieProvider,
                                     ObjectProvider<HalConfiguration> halConfiguration, MessageResolver resolver,
                                     AutowireCapableBeanFactory beanFactory) {
        this.relProvider = relProvider;
        this.curieProvider = curieProvider;
        this.halConfiguration = halConfiguration;
        this.resolver = resolver;
        this.beanFactory = beanFactory;
    }

    @Bean
    LinkDiscoverer halLinkDisocoverer() {
        return new HalLinkDiscoverer();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.hateoas.config.HypermediaMappingInformation#getMediaTypes()
     */
    @Override
    public List<MediaType> getMediaTypes() {
        return HypermediaType.HAL.getMediaTypes();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.hateoas.config.HypermediaMappingInformation#configureObjectMapper(com.fasterxml.jackson.databind.ObjectMapper)
     */
    @Override
    public ObjectMapper configureObjectMapper(ObjectMapper mapper) {

        HalConfiguration halConfiguration = this.halConfiguration.getIfAvailable(HalConfiguration::new);

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new Jackson2HalModule());
        mapper.setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(relProvider,
                curieProvider.getIfAvailable(() -> CurieProvider.NONE), resolver, halConfiguration, beanFactory));

        halConfiguration.customize(mapper);

        return mapper;
    }
}
