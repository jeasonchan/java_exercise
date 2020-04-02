package default_package.list的remove验证;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int index = 2; index < 12; index++) {
            list.add(index);
        }

        System.out.println(list);//[2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

        //list.remove(Integer.valueOf(2));//[3, 4, 5, 6, 7, 8, 9, 10, 11]
//        list.remove(2);//[2, 3, 5, 6, 7, 8, 9, 10, 11]
        list.remove(new Integer(2));//[3, 4, 5, 6, 7, 8, 9, 10, 11]

        /**
         * ！！！！！！！！！！！！！！！！！
         * 手动装箱的方式有两种！
         * 注意 remove 两种重载，一种是的int入参的，按索引删除；
         * 一种是传入对象，调用的对象的equals的方法作比较，然后删除
         */

        System.out.println(list);


    }
}
