package default_package;

public class Class_main {
    public static void main(String[] args){
        System.out.println("hello world!");
        System.out.print("1+1的结果是：");
        System.out.print(1+1+"\n");

        //使用DecimalFormat对浮点数进行格式化处理
        System.out.println("\n开始使用DecimalFormat对浮点数进行格式化处理");
        String string_pattern_1="###,###.###";
        String string_pattern_2="00000000.###kg";
        String string_pattern_3="000000.000";
        String string_pattern_4="#,###%";
        String string_pattern_5="###.###";
        String string_pattern_6="0.00000\u2030";
        Class_DecimalFormat_lian_xi.simple_format(string_pattern_1,1.224);
        Class_DecimalFormat_lian_xi.use_applypattern_format(string_pattern_2,2.33333333);
        Class_DecimalFormat_lian_xi.use_applypattern_format(string_pattern_6,0.0234588);
        Class_DecimalFormat_lian_xi.she_zhi_shu_zi_fen_zu_format(2,111111.4324242);
        System.out.println("结束使用DecimalFormat对浮点数进行格式化处理\n");

        //进行数学运算类练习
        System.out.println("\n开始进行数学运算类的练习！");


        System.out.println("结束进行数学运算类的练习！\n");


        //第十章开始代码练习
        Class_test1 class_test1_example=new Class_test1();
        Class_test2 calss_test3_example=new Class_test2();

        //类型转换练习
        //向上类型转换
        QuardRange q=new QuardRange();
        Parallelogram p=new Parallelogram();
        q.draw(p);//继承自父类，因此IDE推荐的参数类型仍然是父类的QuardRange，但是由于可以向上转型，子类可以当作父类使用，多态的体现
        //向下类型转换
        if(q instanceof Parallelogram){  //实例类型判断，是这个类的一个实例的话才能进行 显式的类型转换；不判断是否为实例就直接转型会导致编译不通过
            p.new_draw((Parallelogram) q);//向下转型必须显式的，且只有在instanceof判断为真时才能执行
        }
        else{
             System.out.println("无法进行向下转型！");
        }

        //函数重载代码练习
        Class_overload example_Class_overload=new Class_overload();
        System.out.println(example_Class_overload.add(1,2));//
        System.out.println(example_Class_overload.add(1,2,3,4,5,6));

        //接口练习
        //将接口和多态技术相结合
        draw_interface[] draw_interfaces_array={new QuardRange_interface(),new Parallelograme_interface()};//生成两个带接口的实例，并进行向上转型
        for(int i=0;i<2;i++){
            draw_interfaces_array[i].draw();
        }

        //import练习
        Class_import_lian_xi intance_Class_import_lian_xi=new Class_import_lian_xi();
        intance_Class_import_lian_xi.method_1();


        //内部类练习
        outer_class instance_outer_class=new outer_class();//实例化外部类
        outer_class.inner_class instance_inner_class=instance_outer_class.new inner_class();//通过外部类实例才访问 内部类 的构造函数
        //第二种方式实例化内部类并传递到外部
        outer_class2 instance_outer_class2=new outer_class2();
        outer_class2.inner_class instance_inner_class2= instance_outer_class2.get_reference_of_inner_class();
        //使用
        outer_class_3 instance_outer_class_3=new outer_class_3();
        //outer_class_3.inner_class_3=instance_outer_class_3.get_inner_class_3();   本以为可以这样，就不用返回接口了，但是！！！由于是private
        //修饰的，导致无法定一个引用来接收这个返回的应用，所以，还是必须利用向上转型返回一个接口，才能使用到在内部类中实现的功能
        Do_sth instance_Do_sth=instance_outer_class_3.get_interface_in_inner_class();
        instance_Do_sth.do_sth();
















    }
}
