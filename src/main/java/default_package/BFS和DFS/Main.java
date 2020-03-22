package default_package.BFS和DFS;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //构造一个的图
        /*
        图：
        a:b d e
        b:a c
        c:b f d
        d:c a e
        e:a d
        f:c g
        g:f

         */

        //构造图
        Map<String, String[]> node_Neighbour_map = new HashMap<>();
        node_Neighbour_map.put("a", new String[]{"b", "d", "e"});
        node_Neighbour_map.put("b", new String[]{"a", "c"});
        node_Neighbour_map.put("c", new String[]{"b", "f", "d"});
        node_Neighbour_map.put("d", new String[]{"c", "a", "e"});
        node_Neighbour_map.put("e", new String[]{"a", "d"});
        node_Neighbour_map.put("f", new String[]{"c", "g"});
        node_Neighbour_map.put("g", new String[]{"f"});

//        BFS.printEachNodeDistance(node_Neighbour_map, "a");


        Map<String, Boolean> record = new LinkedHashMap<>();
        DFS.listAllRoutesFromStartNode(node_Neighbour_map, record, "a");

        System.out.println(record);


    }
}
