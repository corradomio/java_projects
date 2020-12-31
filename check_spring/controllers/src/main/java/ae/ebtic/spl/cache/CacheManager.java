package ae.ebtic.spl.cache;

import jext.springframework.cache.guava.GuavaCacheManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class CacheManager extends GuavaCacheManager {

    @Value("${cache.config}")
    private String configurationPath;

    private Logger logger = Logger.getLogger(CacheManager.class);

    public CacheManager() {
        logger.info("New");
    }

    @PostConstruct
    public void initialize() {
        if (configurationPath == null) {
            jext.cache.CacheManager.configure();
        }
        else {
            File configurationFile = new File(configurationPath);
            jext.cache.CacheManager.configure(configurationFile);
        }
    }
}
