package default_package.排序数组;

import java.util.Arrays;

class Solution {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }


    public int[] 冒泡排序(int[] nums) {
        //从小到大冒泡排序
        for (int i = 0; i < nums.length; i++) {

            //每次选出第i小的元素放到i位置上
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }


        return nums;
    }

    public int[] 快速排序(int[] nums) {


        return nums;
    }


    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 1, 2, 0, 0, 3, 4, 534, 5, 35};
        System.out.println(Arrays.toString(new Solution().sortArray(array)));
        System.out.println(Arrays.toString(new Solution().冒泡排序(array)));


    }
}
