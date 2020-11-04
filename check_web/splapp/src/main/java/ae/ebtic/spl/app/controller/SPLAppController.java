package ae.ebtic.spl.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPLAppController {

    private Logger logger = LoggerFactory.getLogger(SPLAppController.class);

    public SPLAppController() {
        logger.info("SPLAppController::new");
    }

    @GetMapping("/spl/app/test")
    public String test() {
        return "/spl/app/test";
    }
}
