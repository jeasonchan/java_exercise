//熟悉使用import方法

package default_package;

import static java.lang.System.out;//导入静态成员变量，out是变量？？？这个需要查询一下。
import static java.lang.Math.max;//导入静态成员方法


public class Class_import_lian_xi {
    void method_1(){
        out.println("hhhhh");
        out.println("1和3的较大值是"+max(1,3));
    }

}
