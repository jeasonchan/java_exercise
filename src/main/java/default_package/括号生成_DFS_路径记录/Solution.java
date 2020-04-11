package default_package.括号生成_DFS_路径记录;

import java.util.*;
import java.util.stream.Collectors;


/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例：

输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/generate-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


先强调几个事实：
尝试去生成一个有效的括号串时，如果：
1、剩下的做括号个数大于0，可以在前面括号串的基础上再加上一个左括号
2、如果，剩余的右括号<左括号，也就是 字符串中的  右括号>左括号   了，显然是不合理的，无论怎么加括号，该字符串都不会是有效括号串了
3、如果 剩余的 右括号>左括号 ，也就是 字符串中的  右括号<左括号  ，才有可能添加右括号

综上，如果，将添加括号的过程一个生成二叉树的过程，添加子节点生成条件，就有两种情况：
1、剩余左括号个数>0，添加左括号，生成左儿子
2、剩余的右括号>左括号，添加右括号，生成右儿子

如何避免生成重复的字符串，从二叉树的生成过程可以看出，如果是一样的字符串，那就是二叉树的路径会发生完全重合，显然不可能
因此，按照以上两个规则生成二叉树时，就自然完成了去重流程

解题步骤：
（1） 先按照规则生括号二叉树
（2）DFS搜索出末端节点，即为最终的结果
注：生成二叉树的过程，其实就可以看作是 DFS的过程，可以将2步合成1步


编码实现要点：
记录路径时，每从一层递归中退出，都要回退对路径记录的修改！！！！！

 */
class Solution {
    static enum RouteGenerateType {
        BFS("BFS"), DFS("DFS");

        RouteGenerateType(String value) {
            this.value = value;
        }

        private String value;
    }


    public List<String> generateParenthesis(int n, RouteGenerateType routeGenerateType) {
        Node root = new Node(Node.NodeType.LEFT);
        generateTree(n - 1, n, root);

        List<String> result = new ArrayList<>();

        switch (routeGenerateType) {
            case BFS:
                //使用BFS获取路径
                result = generateRoute_bfs(root, n);
                break;
            case DFS:
                //使用DFS获取路径
                generateRoute_dfs(root, new StringBuilder(), result);
                break;

        }

        return result;
    }

    /**
     * 通过深度优先搜素，得到末端节点，并输出记录下的遍历路径
     *
     * @param currentNode
     * @param route
     * @param result
     */
    private void generateRoute_dfs(Node currentNode, StringBuilder route, List<String> result) {
        StringBuilder newRoute = route.append(currentNode.value);

        if (null == currentNode.leftNode && null == currentNode.rightNode) {
            //说明当前是末端节点
            result.add(newRoute.toString());
            System.out.println(newRoute);
            return;
        }

        if (null != currentNode.leftNode) {
            generateRoute_dfs(currentNode.leftNode, newRoute, result);
            newRoute.delete(newRoute.length() - 1, newRoute.length());//回退到上一层时，同时回退对路径的修改
        }

        if (null != currentNode.rightNode) {
            generateRoute_dfs(currentNode.rightNode, newRoute, result);
            newRoute.delete(newRoute.length() - 1, newRoute.length());//回退到上一层时，同时回退对路径的修改
        }


    }

    /*
    通过广度优先遍历时，BFS本身的终止条件是Queue中没有节点需要遍历了，
    但是，对于放入队列的节点，我们可以进行有条件的筛选，
    对于本体，我们的目的是遍历得到末端节点，因此，可以不必限制条件，让其自然结束；
    也可以，只加入距离<=2n-1的节点，因为括号字符串的长度是确定的2n，末端节点距离root节点的距离必定是2n-1
     */
    private List<String> generateRoute_bfs(Node root, int n) {
        Map<Node, String> record = new HashMap<>();
        //此处使用的Node没有重写equals和hashcode，会直接使用内存地址，正好可以区分开不同的左右括号节点

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        record.put(root, root.value);


        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentRoute = record.get(currentNode);


            //一般情况下，都要先判断子节点有没有在record中出现过，但是，因为是二叉树，不是图，不会重复访问，略去record判断
            if (null != currentNode.leftNode) {
                queue.add(currentNode.leftNode);

                //务必注意！！！！！！！！！！！！！！！！！放进record的必须和前面的currentNode脱离关系，要不然会持续影响后面的
                //new StringBuilder()  并不会进行深拷贝，会直接影响前后对象
                record.put(currentNode.leftNode, currentRoute + currentNode.leftNode.value);
            }

            if (null != currentNode.rightNode) {
                queue.add(currentNode.rightNode);
                record.put(currentNode.rightNode, currentRoute + currentNode.rightNode.value);
            }


        }


        List<String> result = new ArrayList<>();
        record.values().stream().filter((each) -> each.length() == 2 * n).forEach((each) -> {
            System.out.println(each);
            result.add(each);
        });


        return result;
    }


    private void generateTree(int remainedLeft, int remainedRight, Node currentNode) {
        if (0 == remainedLeft && 0 == remainedRight) {
            return;
        }
        if (remainedLeft > 0) {
            //添加左儿子
            currentNode.leftNode = new Node(Node.NodeType.LEFT);
            generateTree(remainedLeft - 1, remainedRight, currentNode.leftNode);

        }
        if (remainedRight > remainedLeft) {
            //添加右儿子
            currentNode.rightNode = new Node(Node.NodeType.RIGHT);
            generateTree(remainedLeft, remainedRight - 1, currentNode.rightNode);
        }

    }


    //二叉树节点
    static class Node {
        public enum NodeType {
            LEFT("("),
            RIGHT(")");

            NodeType(String value) {
                this.value = value;
            }

            public String value;
        }


        Node(NodeType nodeType) {
            this.value = nodeType.value;
        }

        public String value;
        public Node leftNode = null;
        public Node rightNode = null;

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }
}
