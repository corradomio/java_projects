package apocx.rev;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Revision {
    @UserFunction
    @Description("apocx.rev.test(inRevision, null|r|[]|[r]|[r1,r2]) | check if the node is available in the selected revision(s)")
    public Object test(@Name("inRevision") List<Boolean> inRevision, @Name("rev")Object rev) {
        if (inRevision == null)
            return true;
        if (inRevision.isEmpty())
            return true;

        List<Long> revs;

        // null -> [-1]
        if (rev == null)
            rev = Arrays.asList(-1L);
        // int -> [int]
        if (!(rev instanceof Collection))
            rev = Arrays.asList(rev);
        // [] -> [-1]
        if (((Collection)rev).isEmpty())
            rev = Arrays.asList(-1L);
        if (!(rev instanceof List))
            rev = new ArrayList<>((Collection)rev);

        revs = (List<Long>) rev;

        if (revs.size() == 1)
            return testRev(inRevision, revs.get(0).intValue());
        else
            return testRev(inRevision, revs.get(0).intValue())
                || testRev(inRevision, revs.get(1).intValue());
    }

    private boolean testRev(List<Boolean> inRevision, int rev) {
        if (rev == -2)
            return true;
        int n = inRevision.size();
        if (rev == -1)
            return inRevision.get(n-1);
        else
            return inRevision.get(rev);
    }
}
