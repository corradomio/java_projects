package jext.sourcecode.project;

import jext.name.Name;

import java.util.List;

public interface TypeParam {

    Name getName();

    List<DeclType> getBounds();

    String getSignature();

    // String getDigest();
}
