package default_package.括号生成_DFS_路径记录;

import default_package.BFS和DFS.BFS;

public class Main {
    public static void main(String[] args) {
        System.out.println("BFS:");
        new Solution().generateParenthesis(3, Solution.RouteGenerateType.BFS);


        System.out.println("DFS:");
        new Solution().generateParenthesis(3, Solution.RouteGenerateType.DFS);
    }
}
