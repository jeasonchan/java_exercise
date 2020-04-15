package default_package.按字典顺序输字符串排列组合;


import java.util.*;

/*

输入一个字符串,按字典序打印出该字符串中字符的所有排列。
例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

https://blog.csdn.net/k346k346/article/details/51154786

 */
public class Solution {
    public static enum ImplType {
        递推, 动态规划;

    }


    public List<List<Character>> getSortedList(String input, ImplType implType) {
        //对字符串的字母排序
        char[] sortedChars = input.toCharArray();
        Arrays.sort(sortedChars);
        System.out.println("input chars:" + Arrays.toString(sortedChars));

        switch (implType) {
            case 递推:
                return getList_递推(sortedChars);
            case 动态规划:
                return null;
        }

        return null;
    }


    /**
     * 感觉开销较大，差点意思
     * @param input
     * @return
     */
    private List<List<Character>> getList_递推(char[] input) {
        int N = input.length;
        Map<Integer, List<List<Character>>> record = new HashMap<>();

        for (int index = 0; index < N; ++index) {
            if (0 == index) {
                List<List<Character>> result0 = new ArrayList<>();
                List<Character> list0 = new ArrayList<>();
                list0.add(input[index]);
                result0.add(list0);
                record.put(index, result0);

            } else {
                List<List<Character>> previousResult = record.get(index - 1);
                List<List<Character>> currentResult = new ArrayList<>();
                char newChar = input[index];

                for (List<Character> each : previousResult) {
                    for (int i = 0; i <= index; ++i) {
                        List<Character> temp = new ArrayList<>(each);
                        temp.add(i, newChar);
                        currentResult.add(temp);
                    }

                }


                record.put(index, currentResult);
            }

        }

        return record.get(N - 1);

    }


}
