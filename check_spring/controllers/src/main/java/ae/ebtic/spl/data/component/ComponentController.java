package ae.ebtic.spl.data.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/c")
public class ComponentController {

    @Autowired ComponentModelRepository repository;

    @GetMapping("")
    public ComponentModelEntity getModel() {
        // return modelRepo.findByRefId("abe112c1");
        return repository.findByFullname("example_repo/spl26");
    }
}
