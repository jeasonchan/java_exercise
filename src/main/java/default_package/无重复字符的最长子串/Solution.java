package default_package.无重复字符的最长子串;


import java.util.HashMap;
import java.util.Map;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


分析：
dp(n)是包含s[n]的最长的不重复的子串

dp(n+1) 是包含s[n+1]的最长的不重复的子串

dp(n+1)和dp(n)关系：

如果dp(n)中含有s[n+1],
dp(n+1)=dp[n]中的s[n+1]的后一位到当前的s[n+1]

否则：dp[n+1]=dp[n]+s[n+1]

 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int Length = s.length();
        Map<Integer, String> record = new HashMap<>();


        for (int index = 0; index < Length; ++index) {
            if (0 == index) {
                record.put(0, s.substring(0, 1));

            } else {

                String previousString = record.get(index - 1);
                String currentSingleString = s.substring(index, index + 1);

                if (previousString.contains(currentSingleString)) {
                    int previousIndex = previousString.indexOf(currentSingleString);
                    record.put(index, previousString.substring(previousIndex + 1) + currentSingleString);
                } else {
                    record.put(index, previousString + currentSingleString);

                }


            }

        }

        System.out.println(record);

        int maxLength = 0;
        for (String string : record.values()) {
            maxLength = Math.max(string.length(), maxLength);
        }
        return maxLength;
    }
}
