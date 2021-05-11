package jext.vfs.git;

import jext.vfs.Branch;
import org.eclipse.jgit.lib.Ref;

public class GitBranch implements Branch {

    private Ref ref;

    GitBranch(Ref ref) {
        this.ref = ref;
    }

    @Override
    public String getName() {
        return ref.getName();
    }

    @Override
    public String toString() {
        return ref.getName();
    }
}
