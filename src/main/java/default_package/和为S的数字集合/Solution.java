package default_package.和为S的数字集合;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
正整数, 找出和为s的数字的集合.
如 s=10,输出：
1,9
2,8
1,2,7
4,6
1,3,6
1,4,5
2,3,5
1,2,3,4
  ...



动态规划思路:
状态定义：
DP(n) 和为n的数字列表 的 集合，其中数字列表内部严格按照从小到大排序，比如：
1,9
1,2,7
1,2,3,4
1,3,6
1,4,5

2,8
2,3,5

3,7

4,6
...


则，DP(n)到DP(n+1)的操作是：
[1,DP(n+1-1)中以2开始的数组]
[2,DP(n+1-2)中以3开始的数组]
[3,DP(n+1-3)中以4开始的数组]
……
……
……
[n,DP(n+1-n)中以n+1开始的数组]
[n+1]

对以上求合集，即为最终所求。其中，某些情况可以直接省去，比如。不会存在以 (n+1+1)/2开头的数字集合


动态规划的实现，采用自底向上的递推

 */
public class Solution {

    public List<List<Integer>> getList(int n) {
        Map<Integer, List<List<Integer>>> record = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            DP(i, record);
        }

        return record.get(n);
    }


    private List<List<Integer>> DP(int n, Map<Integer, List<List<Integer>>> record) {
        List<List<Integer>> result = new ArrayList<>();


        //处理边界
        if (1 == n) {
            List<Integer> list1 = new ArrayList<>();
            list1.add(1);
            result.add(list1);
            record.put(1, result);
            return result;
        }

        //n>=2的情况，可优化边界条件，比如，start<=(n+1)/2
        for (int startNum = 1; startNum <= n - 1; startNum++) {
            int queriedN = n - startNum;//此值确保>=1，因此tartNum <= n - 1
            List<List<Integer>> queriedList = record.get(queriedN);

            for (List<Integer> each : queriedList) {
                if (startNum + 1 <= each.get(0)) {
                    List<Integer> oneResult = new ArrayList<>();
                    oneResult.add(startNum);
                    oneResult.addAll(each);

                    result.add(oneResult);
                }


            }


        }


        //以自身开头的数字
        result.add(List.of(n));


        //输出结果
        record.put(n, result);
        return result;
    }
}
