package ae.ebtic.spl.data.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/f")
public class FeatureController {

    @Autowired FeatureModelRepository modelRepo;
    // @Autowired Neo4jRepository<FeatureModelEntity, Long> extRepo;

    @GetMapping("")
    public FeatureModelEntity getModel() {
        return modelRepo.findByRefId("abe112c1");
        //return modelRepo.findByFullname("example_repo/spl26");
        // return modelRepo.queryForObject(FeatureModelEntity.class,"MATCH (f:feature {role:'PROJECT'}) WHERE f.fullname=$fullname RETURN f",
        //         MapUtils.asMap("fullname", "example_repo/spl26"
        // ));
    }
}
