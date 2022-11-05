package jext.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class KCoreAnalyzer {

    public static List<java.util.Map<String,Object>> computeKCore (List<java.util.Map<String,Object>> nodes){
        for(java.util.Map<String,Object> node: nodes){
            node.put("core",0);
            node.put("active",true);
            int degree = new ArrayList<>((Collection<Integer>)node.get("neighbors")).size();
            node.put("activeNbrs",degree);
            node.put("justDeleted",false);
        }
        int KCore = 0;
        boolean activeNodes = true;
        boolean nodesJustDeleted = false;

        while(activeNodes){
            do{
                activeNodes = false;
                nodesJustDeleted = false;
                for(java.util.Map<String,Object> node: nodes){
                    if(Boolean.parseBoolean(node.get("justDeleted").toString())){
                        ArrayList<Integer> neighbors =(ArrayList<Integer>) ((Collection<Long>)node.get("neighbors")).stream().mapToInt(r -> r.intValue()).boxed().collect(Collectors.toList());
                        for(int x: neighbors){
                            java.util.Map<String,Object> nbr = getNode(nodes, x);
                            int nbrs = Integer.parseInt(nbr.get("activeNbrs").toString()) - 1;
                            nbr.put("activeNbrs",nbrs);
                        }
                    }
                }
                for(java.util.Map<String,Object> node: nodes){
                    if(Boolean.parseBoolean(node.get("active").toString())){
                        if(Boolean.parseBoolean(node.get("justDeleted").toString())) {
                            node.put("justDeleted",false);
                            node.put("active",false);
                        }
                        else if(Integer.parseInt(node.get("activeNbrs").toString()) < KCore) {
                            node.put("justDeleted",true);
                            node.put("core",KCore-1);
                            activeNodes = true;
                            nodesJustDeleted = true;
                        }
                        else {
                            activeNodes = true;
                        }
                    }
                }
            }while(nodesJustDeleted);
            KCore++;
        }
        KCore -= 2;

        for(java.util.Map<String,Object> node: nodes){
            Double k = Integer.parseInt(node.get("core").toString()) * 1.0;
            //System.out.println(node.get("name").toString()+" "+k);
            k = k/(KCore * 1.0);

            node.put("core", k);
        }
        return nodes;
    }

    public static java.util.Map<String,Object> getNode(List<java.util.Map<String,Object>> nodes, int id){

        for(java.util.Map<String,Object> node: nodes){
            if(Integer.parseInt(node.get("id").toString()) > id)
                break;
            if(Integer.parseInt(node.get("id").toString()) == id)
                return node;
        }
        return null;
    }
}
