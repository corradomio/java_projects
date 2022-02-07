package org.hls.template;

import org.hls.cdecl.ACTemplate;
import org.hls.cdecl.ACTemplate2;
import org.hls.cdecl.AClassAInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UseTemplate extends ArrayList<String> {

    ACTemplate<Integer> tfield;
    ACTemplate2<String, AClassAInterface> tfield2;

    List<Map<String, List<String>>> tfield3;

    UseTemplate() {

    }

}
