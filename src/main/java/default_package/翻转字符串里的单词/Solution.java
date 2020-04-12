package default_package.翻转字符串里的单词;

/*
给定一个字符串，逐个翻转字符串中的每个单词。



示例 1：

输入: "the sky is blue"
输出: "blue is sky the"

示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。

示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。



说明：

    无空格字符构成一个单词。
    输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


要点：
1、注意while循环查找的边界判断
//这种移动式查找务必先判断边界！！！！
while (endIndex < newS.length() && ' ' != newS.charAt(endIndex)) {

    endIndex++;

}

2、先正序逆序，自然然而想到栈，虽然普通的数组也可以解决


 */


import java.util.Stack;

class Solution {

    /*
    双指针法，找到每一个单词，压入栈，再按此取出进行拼接
     */
    public String reverseWords(String s) {
        int startIndex = 0, endIndex = 0;//左闭右开
        String newS = s.trim();
        Stack<String> wordStack = new Stack<>();

        //将单词放入栈中
        while (!newS.isEmpty()) {
            startIndex = 0;
            endIndex = 0;

            //这种移动式查找务必先判断边界！！！！
            while (endIndex < newS.length() && ' ' != newS.charAt(endIndex)) {

                endIndex++;

            }

            wordStack.add(newS.substring(startIndex, endIndex));
            newS = newS.substring(endIndex).trim();

        }

        //从栈中取出，拼凑输出
        StringBuilder result = new StringBuilder();
        while (!wordStack.isEmpty()) {
            result.append(wordStack.pop()).append(" ");

        }


        return result.toString().trim();
    }
}
