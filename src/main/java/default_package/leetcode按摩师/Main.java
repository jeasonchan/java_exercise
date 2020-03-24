package default_package.leetcode按摩师;

/*
一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

注意：本题相对原题稍作改动



示例 1：

输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。

示例 2：

输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。

示例 3：

输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/the-masseuse-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main {
    public int massage(int[] nums) {

        int length = nums.length;

        if (0 == length) {
            return 0;
        }

        int[] result = new int[length];//存储前面的记录用数据结构

        //采用递推的方式从底部开始填充
        for (int i = 0; i < length; i++) {
            if (0 == i) {
                result[i] = nums[i];
                continue;
            }

            if (1 == i) {
                result[i] = Math.max(nums[0], nums[1]);
                continue;
            }

            //动态规划的核心！
            //此处采用一维方式抵消后效性
            result[i] = Math.max(result[i - 1], result[i - 2] + nums[i]);

        }

        return result[length - 1];
    }
}
