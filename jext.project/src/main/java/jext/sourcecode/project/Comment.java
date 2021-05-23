package jext.sourcecode.project;

import java.util.List;

public interface Comment {

    List<String> getDocumentation();
    List<String> getAuthors();

    List<String> getComments();
    List<String> getAllComments();
}
