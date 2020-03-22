package default_package.BFS和DFS;

import java.util.Arrays;
import java.util.Map;

public class DFS {
    /*
    DFS  主要用来寻找，以某个点为起点时，所有的完整的、有终点的路径情况
     */

    public static void listAllRoutesFromStartNode(Map<String, String[]> graph, Map<String, Boolean> record, String startNode) {
        if (!record.containsKey(startNode)) {
            record.put(startNode, true);

            System.out.println("node:" + startNode);


            String[] neighbourNodes = graph.get(startNode);

            //所连接的节点都已经被访问过一遍了，则说明要返回父级节点重新访问
            //也就说明，当前节点是一个末端节点
            if (record.keySet().containsAll(Arrays.asList(neighbourNodes))) {
                System.out.println("return the previous node");
            }

            for (String each : neighbourNodes) {
                listAllRoutesFromStartNode(graph, record, each);
            }


        }
    }
}
