package ae.ebtic.spl.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPLWebController {

    private Logger logger = LoggerFactory.getLogger(SPLWebController.class);

    public SPLWebController() {
        logger.info("SPLWebController::new");
    }

    @GetMapping("/spl/web/test")
    public String test() {
        return "/spl/web/test";
    }
}
