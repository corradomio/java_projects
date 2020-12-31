package ae.ebtic.spl.data.idonly;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

@Data
@QueryResult
public class FromAndTo {
    private Long from;
    private Long to;
}
