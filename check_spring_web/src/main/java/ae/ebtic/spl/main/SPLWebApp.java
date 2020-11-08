package ae.ebtic.spl.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPLWebApp {

    private Logger logger = LoggerFactory.getLogger(SPLWebApp.class);

    @Value("${ae.ebtic.spl.welcome}")
    private String welcome= "boh";

    public SPLWebApp() {
        logger.info("SPLWebApp::new");
    }

    // @RequestMapping("/")
    // String homePage() {
    //     return "redirect:/index.html";
    // }

    @GetMapping("/spl/main/test")
    @ResponseBody
    public String test() {
        return "SPLWebApp: " + welcome;
    }
}
