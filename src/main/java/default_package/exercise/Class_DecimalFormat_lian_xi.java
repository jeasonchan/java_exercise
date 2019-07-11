package default_package.exercise;

import java.text.DecimalFormat;
public class Class_DecimalFormat_lian_xi {
    /*主要用来进行数字格式化练习，写好方法后，再通过main方法进行调用执行
    * */

    //通过实例化对象的方式进行十进制数字格式化
    public static void simple_format(String pattern,double value){
        //将某字符串作为格式化的模板
        DecimalFormat my_pattern=new DecimalFormat(pattern);
        //使用非静态方法对值进行格式化
        String output_string=my_pattern.format(value);
        //将格式化并转化为String的字符串输出
        System.out.println("以"+pattern+"为模板，对"+value+"进行格式化，结果为："+output_string);
    }

    //通过ApplayPattern（）类方法进行数字格式化
    public static  void use_applypattern_format(String pattern,double value){
        DecimalFormat my_pattern=new DecimalFormat();
        my_pattern.applyPattern(pattern);
        String output_string=my_pattern.format(value);
        System.out.println("以"+pattern+"为模板，对"+value+"进行格式化，结果为："+output_string);

    }

    //设置数字分组，将浮点数个位数及以上部分以逗号分隔
    public static void she_zhi_shu_zi_fen_zu_format(int set_number,double value){
        DecimalFormat my_pattern=new DecimalFormat();
        my_pattern.setGroupingSize(set_number);
        my_pattern.setGroupingUsed(true);
        String output_string=my_pattern.format(value);
        System.out.println("以"+set_number+"为分组数，对"+value+"进行格式化，结果为："+output_string);
    }


}
