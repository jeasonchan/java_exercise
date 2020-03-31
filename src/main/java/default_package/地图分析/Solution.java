package default_package.地图分析;

/*
你现在手里有一份大小为 N x N 的「地图」（网格） grid，上面的每个「区域」（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，

请你找出一个海洋区域，这个海洋区域到离它最近的陆地区域的距离是最大的。

我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。

如果我们的地图上只有陆地或者海洋，请返回 -1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

=======================分析========================

解读题目中的「最远」和「最近」

题目问的是「距离陆地区域最远的海洋区域」，其实就是从陆地（1）开始，要扩散多少次，才能把所有的海洋给覆盖掉。「最远」应该从这个角度来理解。而「该海洋区域到离它最近的陆地区域的距离」，「最近」是因为一定是距离这个最后才扩散到的海洋的最近的陆地才能扩散到它。

二维表格上的问题，常用的算法是深度优先遍历、广度优先遍历和并查集，由于这里计算的结果和距离相关，显然使用广度优先遍历；
但是题目问的是「距离陆地区域最远的海洋区域」，这和我们的经验稍微有点出入。一般而言，「广度优先遍历」求的是最短路径，但仔细一想，发现其实广度优先遍历也是适用的：
1. 求最短路径的时候，只要找到目标值，返回即可；
2. 求最长路径的时候，所有目标值都看完以后，才返回。

这道题「广度优先遍历」的起点有多个，毕竟每一片水域都要求一下其到最近陆地的距离，但完全不影响算法的正确性，可以假想一个虚拟的起点，初始的起点就是由虚拟起点扩散开来的。

这里题目中介绍的「曼哈顿距离」，其实就是对广度优先遍历（BFS）逐层向外扩散的精准数学解释，每扩散一次，曼哈顿距离就加 1。

编写广度优先遍历算法的注意事项：
1. 如果题目要求返回的结果和距离相关，需要在 while 循环内部一下子把当前列表的所有元素都依次取出来，这种「一下子」操作的次数就是我们需要的距离；
2. 如果一个单元格被添加到队列以后，需要马上将它标记为已经访问（根据情况，可以直接在原始输入数组上修改，也可以使用一个布尔数组 visited 进行标记），如果不这么做，很可能会出现死循环，这一点如果一开始没有注意到，也可以通过调试代码观察出。

使用「广度优先遍历」算法的问题还有：

    「力扣」第 279 题：完全平方数
    「力扣」第 322 题：零钱兑换

这些问题虽然看起来和最短路径无关，但只要在纸上写写画画，不难发现依然是求：在树或者图上的最短路径。可以通过这一类问题熟悉 BFS 的写法。

二维表格上的问题还有：

    「力扣」第 200 题：岛屿数量
    「力扣」第 994 题：腐烂的橘子
    「力扣」第 130 题：被围绕的区域
    「力扣」第 79 题：单词搜索
    「剑指 Offer 」第 13 题：机器人的运动范围

大家可以通过做这些问题，熟悉在二维表格上编码代码的常用技巧：
1. 设置方向数组，使得向「四面八方」扩散的代码更加紧凑；
2. 设置是否越界的判断函数 inArea()；
3. 根据情况，使用二维坐标和一维坐标相互转换的操作，因为二维坐标传入队列的时候，需要封装成数组，创建和销毁数组有一定性能消耗，有些问题如果需要判重，还可能有一点点工作量。二位数组的

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/yan-du-you-xian-bian-li-java-by-liweiwei1419/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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


    /**
     * 传进来时，已经保证是海洋的坐标，无需再次判断
     *
     * @param grid
     * @param width
     * @param height
     * @return
     */
    public int getMinDistance(int[][] grid, int width, int height) {
        int N = grid[0].length;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(width, height));
        Map<Node, Integer> record = new HashMap<>();
        record.put(new Node(width, height), 0);
        int[] array_height = new int[]{0, 0, -1, 1};
        int[] array_width = new int[]{1, -1, 0, 0};


        while (!queue.isEmpty()) {
            Node topNode = queue.poll();//只有没被记录的才会放进队列
            int topHeight = topNode.height;
            int topWidth = topNode.width;
            int topDistance = record.get(topNode);
            if (LAND == grid[topHeight][topWidth]) return topDistance;


            //查看四个方向的水域
            for (int direction = 0; direction < 4; ++direction) {
                int newHeight = topHeight + array_height[direction];
                int newWidth = topWidth + array_width[direction];

                if (newHeight >= 0 && newHeight < N &&
                        newWidth >= 0 && newWidth < N) {

                    Node newNode = new Node(newWidth, newHeight);
                    if (!record.containsKey(newNode)) {//没经过的，放进queue，放进record
                        queue.add(newNode);
                        record.put(newNode, topDistance + 1);
                    }

                }
            }


        }


        return -1;//扩散的过程中没碰到任何LAND，直接返回-1
    }


    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println(new Solution().maxDistance(grid));
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
