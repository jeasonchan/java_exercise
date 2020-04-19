package default_package.两数之和;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
//        return twoSum_暴力法实现(nums, target);
        return twoSum_哈希表实现(nums, target);

    }


    //暴力法求解,for循环
    public int[] twoSum_暴力法实现(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        final int Length = nums.length;

        index1_cycle:
        for (int index1 = 0; index1 < Length; ++index1) {
            int firstNum = nums[index1];
            result[0] = index1;

            int expectedNum = target - firstNum;
            for (int index2 = index1 + 1; index2 < Length; ++index2) {
                if (expectedNum == nums[index2]) {
                    result[1] = index2;
                    break index1_cycle;
                }

            }

        }

        return result;
    }


    //使用hash实现常数级别查找，降低时间复杂度
    public int[] twoSum_哈希表实现(int[] nums, int target) {
        int Length = nums.length;
        Map<Integer, List<Integer>> num2Index = new HashMap<>();
        for (int index = 0; index < Length; ++index) {
            num2Index.putIfAbsent(nums[index], new ArrayList<>());
            num2Index.get(nums[index]).add(index);
        }


        int[] result = new int[]{-1, -1};
        for (int index = 0; index < Length; ++index) {
            result[0] = index;
            int firstNum = nums[index];

            //从hash中删除该键值对，防止重复利用
            num2Index.get(firstNum).remove(Integer.valueOf(index));

            int expectedNum = target - firstNum;
            if (num2Index.containsKey(expectedNum) && num2Index.get(expectedNum).size() >= 1) {
                result[1] = num2Index.get(expectedNum).get(0);
                break;
            }

            //恢复删除的值
            num2Index.get(firstNum).add(index);


        }

        return result;


    }

}
