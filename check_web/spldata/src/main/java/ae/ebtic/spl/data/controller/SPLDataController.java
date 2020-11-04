package ae.ebtic.spl.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPLDataController {

    private Logger logger = LoggerFactory.getLogger(SPLDataController.class);

    public SPLDataController() {
        logger.info("SPLDataController::new");
    }

    @GetMapping("/spl/data/test")
    public String test() {
        return "/spl/data/test";
    }

}
