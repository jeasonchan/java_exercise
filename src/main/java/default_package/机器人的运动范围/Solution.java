package default_package.机器人的运动范围;

/*
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？



示例 1：

输入：m = 2, n = 3, k = 1
输出：3

示例 1：

输入：m = 3, n = 1, k = 0
输出：1

提示：

    1 <= n,m <= 100
    0 <= k <= 20

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int movingCount(int m, int n, int k) {
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> record = new HashMap<>();//本体可以不用Map，因为不需要求最短距离，直接使用List即可
        Node start = new Node(0, 0);
        queue.add(start);
        record.put(start, 0);

        final int[] heightDiv = new int[]{0, 0, 1, -1};
        final int[] widthDiv = new int[]{1, -1, 0, 0};

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            int distance = record.get(top);

            //向四个方向扩展
            for (int direction = 0; direction < 4; ++direction) {
                int newHeight = top.height + heightDiv[direction];
                int newWidth = top.width + widthDiv[direction];

                //不越界，且和<=k则有可能加入队列
                if (newHeight >= 0 && newHeight < m &&
                        newWidth >= 0 && newWidth < n &&
                        getNumSum(newHeight) + getNumSum(newWidth) <= k) {
                    Node nodeToVisit = new Node(newHeight, newWidth);

                    //之前没有访问过，最终判断可加入queue
                    if (!record.containsKey(nodeToVisit)) {

                        record.put(nodeToVisit, distance + 1);
                        queue.add(nodeToVisit);

                    }

                }


            }


        }


        return record.size();
    }

//    public static void main(String[] args) {
//        System.out.println(new Solution().movingCount(11, 8, 16));
//    }

    //根据提议 number>=0 && number<=100
    public int getNumSum(int number) {
        int ans = 0;
        while (number > 0) {
            int digit = number % 10;
            number /= 10;
            ans += digit;
        }
        return ans;

    }


    //由于每个节点是的包含两个数字的，要么使用对象，要么使用将两个数字转为唯一数值的公式 比如 (100+1)*height+width
    class Node {
        int height;
        int width;

        public Node(int height, int width) {
            this.height = height;
            this.width = width;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (height != node.height) return false;
            return width == node.width;
        }

        @Override
        public int hashCode() {
            int result = height;
            result = 31 * result + width;
            return result;
        }
    }
}
