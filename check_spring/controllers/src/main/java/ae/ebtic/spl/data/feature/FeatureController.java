package ae.ebtic.spl.data.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/f")
public class FeatureController {

    @Autowired FeatureModelRepository modelRepo;

    @GetMapping("")
    public FeatureModelEntity getModel() {
        return modelRepo.findByRefId("abe112c1");
    }
}
