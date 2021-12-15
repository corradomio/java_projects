package jext.sourcecode.project;

import java.util.List;

public interface Resources extends List<Resource> {

    Resource getResource(String nameOrId);
}
