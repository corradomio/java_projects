package ae.ebtic.spl.web.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPLWebApp {

    @RequestMapping("/")
    String homePage() {
        return "redirect:/index.html";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
