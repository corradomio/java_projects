package org.hls.template;

import org.hls.cdecl.ACTemplate;
import org.hls.cdecl.ACTemplate1;

public class AnotherTemplate<T extends Integer> extends ACTemplate<T> {

    ACTemplate<T> tfield;

    AnotherTemplate(T init) {

    }

}
