package org.hls.annot;

import lombok.Data;
import lombok.NonNull;

/** */
@MyAnnotation
@Data
public class AnnotatedClass {

    String name;
    Long id;

    /** */
    @NonNull
    void method() {

    }
}
