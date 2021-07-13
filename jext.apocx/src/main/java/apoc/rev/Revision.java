package apoc.rev;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.Collection;
import java.util.List;

public class Revision {
    @UserFunction
    @Description("apoc.rev.test(inRevision, r|[]|[r]|[r1,r2]) | check if the node is available in the selected revision(s)")
    public Object test(@Name("inRevision")List<Boolean> inRevision, @Name("rev")Object rev) {
        if (inRevision == null || rev == null)
            return true;
        if (inRevision.isEmpty())
            return false;

        if (!(rev instanceof Collection)) {
            int r = ((Long)rev).intValue();
            return testRev(inRevision, r);
        }
        List<Long> c = ((List<Long>) rev);
        if (c.isEmpty())
            return testRev(inRevision, -1);
        if (c.size() == 1)
            return testRev(inRevision, c.get(0).intValue());
        else
            return testRev(inRevision, c.get(0).intValue())
                || testRev(inRevision, c.get(1).intValue());
    }

    private boolean testRev(List<Boolean> inRevision, int rev) {
        if (rev == -2) return true;
        if (rev == -1) return inRevision.get(inRevision.size()-1);
        return inRevision.get(rev);
    }
}
