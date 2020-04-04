package default_package.BFS和DFS;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.*;

public class BFS {

    public static void printEachNodeDistance(Map<String, String[]> graph, String startNode) {
        Map<String, Integer> record = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        int distance = 0;


        record.put(startNode, distance);
        queue.add(startNode);


        while (!(queue.isEmpty())) {
            String topNode = queue.poll();
            int currentDistance = record.get(topNode);

            System.out.println(topNode + " to " + startNode + " distance is " + currentDistance);

            String[] linkedNodes = graph.get(topNode);
            for (String each : linkedNodes) {
                if (!record.containsKey(each)) {
                    record.put(each, currentDistance + 1);
                    queue.add(each);
                }


            }


        }

    }

}
