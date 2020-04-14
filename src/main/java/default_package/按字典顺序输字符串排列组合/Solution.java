package default_package.按字典顺序输字符串排列组合;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

输入一个字符串,按字典序打印出该字符串中字符的所有排列。
例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。







 */
public class Solution {
    public static enum ImplType {
        递归, 动态规划;

    }


    public List<String> getSortedList(String input, ImplType implType) {
        //对字符串的字母排序
        char[] sortedChars = input.toCharArray();
        Arrays.sort(sortedChars);
        System.out.println("input chars:" + Arrays.toString(sortedChars));

        switch (implType) {
            case 递归:
                return getList_递归(sortedChars);
            case 动态规划:
                return null;
        }

        return null;
    }


    private List<String> getList_递归(char[] input) {


        return null;
    }


}
