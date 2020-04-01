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


    //从小到大快速排序
    public int[] 快速排序(int[] nums, int leftIndex, int rightIndex) {
        /*
        快速排序主要使用了：分而治之、双指针、快速交换  递归 等思想
         */

        //递归退出条件
        if (leftIndex >= rightIndex || nums.length <= 1) {
            return nums;
        }

        int bigNumIndex = leftIndex;
        int smallNumIndex = rightIndex;
        int middle = (leftIndex + rightIndex) / 2;//基数坐标
        int pivot = nums[middle];//基数

        while (bigNumIndex <= smallNumIndex) {
            //找到左起及其下一个的 >=基数 的索引
            //千万不能用 nums[bigNumIndex] <= pivot ，
            // 否则会一直寻找比pivot严格大的数字，如果不凑巧，pivot恰好就是最大的，会造成越界
            while (nums[bigNumIndex] < pivot) {
                bigNumIndex++;
            }

            //找到右起及其下一个的 <=基数的 索引
            while (nums[smallNumIndex] > pivot) {
                smallNumIndex--;
            }

            //根据索引的相对大小，分情况进行处理
            if (bigNumIndex < smallNumIndex) {
                int temp = nums[bigNumIndex];
                nums[bigNumIndex] = nums[smallNumIndex];
                nums[smallNumIndex] = nums[bigNumIndex];
                bigNumIndex++;
                smallNumIndex--;
            }
            if (bigNumIndex == smallNumIndex) {
                bigNumIndex++;
            }


        }

        //将被分成三部分的数组的在再次进行快速排序：左边的部分的   基数    右边的部分
        //进行一下两步时，必须保证  smallNumIndex 已经严格小于 bigNumIndex
        快速排序(nums, leftIndex, smallNumIndex);
        快速排序(nums, bigNumIndex, rightIndex);

        return nums;
    }


    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 1, 2, 0, 0, 3, 4, 534, 5, 35};
        System.out.println(Arrays.toString(new Solution().sortArray(array)));
        System.out.println(Arrays.toString(new Solution().冒泡排序(array)));
        System.out.println(Arrays.toString(new Solution().快速排序(array, 0, array.length - 1)));


    }
}
