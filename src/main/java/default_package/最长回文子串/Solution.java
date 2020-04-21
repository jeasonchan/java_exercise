package default_package.最长回文子串;


import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：

输入: "cbbd"
输出: "bb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



dp(index) 为 以 string  在index处的字符  结尾的 最长回文字符串

dp(index)和dp(index-1)的状态转移：

如果 string[index] 等于 dp(index)  的前一个：
    dp(index)=string[index]+dp(index-1)+string[index]
    (不用考虑再往前几个相等的情况，反正法即可)
否则：
    从dp(index-1)中从左开始查找字符string[index]，并检查   string[index]+部分dp(index-1)+string[index]  是否为回文字符串：
        是，则 dp(index)=string[index]+部分dp(index-1)+string[index]
        否，继续寻找下一个





 */
class Solution {
    public String longestPalindrome(String s) {
        Map<Integer, String> record = new HashMap<>();
        int Length = s.length();

        for (int index = 0; index < Length; ++index) {
            if (0 == index) {
                record.put(index, s.substring(index, index + 1));
            } else {
                String singleString = s.substring(index, index + 1);
                String previousResult = record.get(index - 1);
                int previousResultLength = previousResult.length();

                if (previousResultLength < index && s.substring(index - previousResultLength - 1, index - previousResultLength).equals(singleString)) {
                    record.put(index, singleString + previousResult + singleString);

                } else {

                    for (int subIndex = 0; subIndex < previousResultLength; ++subIndex) {
                        if (previousResult.substring(subIndex, subIndex + 1).equals(singleString)) {
                            String checkedString = previousResult.substring(subIndex) + singleString;

                            if (checkIsPalindrome(checkedString)) {
                                record.put(index, checkedString);
                                break;
                            }

                        }
                    }

                    record.putIfAbsent(index, singleString);


                }


            }


        }

        System.out.println(record);

        String result = "";
        for (String each : record.values()) {
            result = each.length() > result.length() ? each : result;
        }

        return result;
    }


    //检查是否为回文字符串
    private boolean checkIsPalindrome(String string) {
        return string.equals(new StringBuilder(string).reverse().toString());
    }
}
