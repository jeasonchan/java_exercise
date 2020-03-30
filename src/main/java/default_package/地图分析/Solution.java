package default_package.地图分析;

/*
你现在手里有一份大小为 N x N 的「地图」（网格） grid，上面的每个「区域」（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，

请你找出一个海洋区域，这个海洋区域到离它最近的陆地区域的距离是最大的。

我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。

如果我们的地图上只有陆地或者海洋，请返回 -1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

分析：




 */


import java.util.HashMap;
import java.util.Map;

public class Solution {
    final int WATER = 0;
    final int LAND = 1;

    public int maxDistance(int[][] grid) {
        int N = grid[0].length;



        Map<Node, Integer> cord2DistanceMap = new HashMap<>();

        for (int width = 0; width < N; ++width) {
            for (int height = 0; height < N; ++height) {

                //遍历求每个海洋区域的离陆地的最短距离
                if (WATER == grid[height][width]) {
                    cord2DistanceMap.put(new Node(width, height), getMinDistance(grid, width, height));
                }


            }
        }

        int result = -1;

        for (int each : cord2DistanceMap.values()) {
            result = Math.max(result, each);
        }


        return result;
    }


    public int getMinDistance(int[][] grid, int width, int height) {



        return 0;
    }

}


class Node {
    int width;
    int height;

    public Node(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (width != node.width) return false;
        return height == node.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }
}
