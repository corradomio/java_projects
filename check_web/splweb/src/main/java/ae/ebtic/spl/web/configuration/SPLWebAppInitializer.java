package ae.ebtic.spl.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class SPLWebAppInitializer implements WebApplicationInitializer {

    // @Autowired
    private String activeProfile;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // servletContext.setInitParameter("spring.profiles.active", "dev");
        activeProfile = servletContext.getInitParameter("spring.profiles.active");
    }
}
