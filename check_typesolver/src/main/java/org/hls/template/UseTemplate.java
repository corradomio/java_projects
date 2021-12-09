package org.hls.template;

import org.hls.cdecl.ACTemplate;
import org.hls.cdecl.ACTemplate2;
import org.hls.cdecl.AClassAInterface;

import java.util.ArrayList;

public class UseTemplate extends ArrayList<String> {

    ACTemplate<Integer> tfield;
    ACTemplate2<String, AClassAInterface> tfield2;
}
