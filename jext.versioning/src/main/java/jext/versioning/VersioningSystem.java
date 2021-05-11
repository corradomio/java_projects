package jext.versioning;

import java.util.List;

public interface VersioningSystem {

    List<Tag>    listTags();
    List<Branch> listBranches();

    void checkout();
    void commit();
}
