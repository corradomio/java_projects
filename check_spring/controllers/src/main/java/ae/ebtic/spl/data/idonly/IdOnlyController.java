package ae.ebtic.spl.data.idonly;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/r")
public class IdOnlyController {

    @Autowired NodeRepository repository;
    // @Autowired Neo4jRepository<FeatureModelEntity, Long> extRepo;

    @GetMapping("")
    public List<?> getModel() {
        return Streams.stream(repository.findRelationsUsingList(Arrays.asList(1053L, 2050L, 2078L))).collect(Collectors.toList());
        //return modelRepo.findByFullname("example_repo/spl26");
        // return modelRepo.queryForObject(FeatureModelEntity.class,"MATCH (f:feature {role:'PROJECT'}) WHERE f.fullname=$fullname RETURN f",
        //         MapUtils.asMap("fullname", "example_repo/spl26"
        // ));
    }
}
