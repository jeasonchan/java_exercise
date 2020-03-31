package default_package.圆圈中最后剩下的数字;

/*
面试题62. 圆圈中最后剩下的数字

0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。



示例 1：

输入: n = 5, m = 3
输出: 3

示例 2：

输入: n = 10, m = 17
输出: 2



限制：

    1 <= n <= 10^5
    1 <= m <= 10^6





 */


import java.util.ArrayList;

public class Solution {
    public int lastRemaining(int n, int m) {
        final int REMOVED = -1;
        int[] array = new int[n];//初始化后。默认都是0
        int remainedNumber = n;
        int index = 0;

        cycle_1:
        while (remainedNumber >= 0) {
            //用for循环进行m次移动
            for (int i = 1; i <= m; ++i) {

                //找到未移除的第一个索引
                while (REMOVED == array[index]) {
                    int tempIndex = index + 1;
                    index = tempIndex >= n ? tempIndex - n : tempIndex;

                }

                System.out.println("不为-1的索引：" + index);

                //前m-1次都只是移动索引，第m次才进行删除操作
                if (i == m) {
                    //对找到的index进行处理
                    --remainedNumber;
                    System.out.println("remove:" + index);
                    array[index] = REMOVED;

                    if (remainedNumber == 1) {
                        break cycle_1;
                    }
                }

                int tempIndex = index + 1;
                index = tempIndex >= n ? tempIndex - n : tempIndex;


            }

        }


        for (int i = 0; i < n; ++i) {
            if (REMOVED != array[i]) {
                return i;
            }
        }

        return 0;
    }

    public int lastRemaining2(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int idx = 0; //跟踪器
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }

        return list.get(0);

    }


    public static void main(String[] args) {
//        System.out.println(new Solution().lastRemaining(5, 3));
        System.out.println(new Solution().lastRemaining(10, 17));
    }
}
