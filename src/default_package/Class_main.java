package default_package;

import javax.swing.text.html.HTMLDocument;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.System.out;//导入静态变量

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

        //try catch 异常捕捉练习1
        try{
            String name="jeason_chan";
            System.out.println(name+"年龄是：");
            int age=Integer.parseInt("25L");//故意出错，是静态方法无法完成对对字符串的转变
            out.println("继续执行try代码块内容……");//上面的语句发生错误后，跳转到catch代码块执行，不再执行异常语句后面的代码
            out.println(age);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        finally {
            out.println("执行结束！");
        }

        //自定义异常类，并进行实现
        try {
            Tran.avg(-3,120);
        }catch (my_exceptions error){
            out.println(error);//可见java自带的exception类具有直接转为string类的默认方法
        }

        //在方法中抛出异常
        try {
            Shoot.pop();
        }catch (Exception e){ //可能抛出多种异常打算用异常类这个父类接收，毕竟可以向上转型，最后打印出来的时候执行各自子类自己的打印转换方法
            out.println(e);
        }

        //使用throw关键字抛出异常
        try{
            Captor.quotient(122,0);
        }catch (my_exceptions e){
            out.println(e);//exception类自带转化为string的方法
            out.println(e.getMessage());//标准的返回异常提示字符串方法
        }catch(ArithmeticException e){
            out.println(e.getMessage());
            out.println("除数不能为0");
        }catch(Exception e){
            out.println(e);//异常类这个大的父类放在最后，因为，防止前面的抓取异常类无法处理
        }


        //练习使用collection类
        java.util.Collection<String> list_collection=new ArrayList<>();//实例化集合对象，这里很像模板类……
        list_collection.add("a");
        list_collection.add("b");
        java.util.Iterator<String>it=list_collection.iterator();//用迭代器模板创建第一个迭代器
        while (it.hasNext()){//迭代器的 next()方法的返回值是 Object类的实例，相当于向上转型了
            String str=it.next();//接收向上转型得到的结果
            out.println(str);
        }

        //练习使用list集合类，严格意义上来说，list不是累，是接口，作为父类接收子类向上转型得到的对象
        java.util.List<Integer> mylisr1=new ArrayList();//还可以使用链表作为实现类
        mylisr1.add(11);
        mylisr1.add(123);
        mylisr1.add(23);
        mylisr1.add(45);
        out.println(mylisr1);//能直接输出原始Arraylist
        mylisr1.remove(2);//删除index=2的元素，并将后面的元素黔移，顺序连续存储，增删效率较低
        out.println("mylisr1的大小是"+mylisr1.size()+" .第2个数字是"+mylisr1.get(2-1));

        //练习使用Set接口
        Set<UpdateStu> stu_set=new TreeSet<>();
        stu_set.add(new UpdateStu(13,"13"));
        UpdateStu stu1=new UpdateStu(34,"34");
        stu_set.add(stu1);
        stu_set.add(new UpdateStu(55,"55"));
        stu_set.add(new UpdateStu(66,"66"));
        stu_set.add(new UpdateStu(78,"78"));
        Iterator<UpdateStu> it_of_stu_set=stu_set.iterator();//迭代器本身指向首位置之前的位置
       while (it_of_stu_set.hasNext()){
           UpdateStu stu=it_of_stu_set.next();  //next()方法的含义是，迭代器向后移动一位
           out.println(stu.getId()+"  "+stu.getName());
       }

       it_of_stu_set=((TreeSet<UpdateStu>) stu_set).headSet(stu1).iterator();  //由于只有treeset才有顺序的概念，因此，先要把set转型成treeset
        while (it_of_stu_set.hasNext()){
            UpdateStu stu=it_of_stu_set.next();  //next()方法的含义是，迭代器向后移动一位
            out.println(stu.getId()+"  "+stu.getName());
        }

        //map接口和map集合练习
        Map<Integer,String> stu_map=new HashMap<>();//向上转型，子类实例引用赋值给父类，map类的实现类有 hashmap 和 treemap
        stu_map.put(123,"123");//map集合没有
        stu_map.put(12,"12");
        stu_map.put(23,"23");
        stu_map.put(1234,"1234");
        Set<Integer> ketset_of_stu_map=stu_map.keySet();//将键值提取出来为set集合
        Iterator<Integer> it_of_omteger=ketset_of_stu_map.iterator();
        while(it_of_omteger.hasNext()){ //遍历键值集合并打印
            Integer integer_object= it_of_omteger.next();
            out.println(integer_object+"   "+stu_map.get(integer_object));
        }
        Collection<String> collection_of_values_of_stu_map=stu_map.values();
        Iterator<String> it_of_string=collection_of_values_of_stu_map.iterator();
        while (it_of_string.hasNext()){
            out.println(it_of_string.next());
        }

        //将hashmap类作为map集合的实现类，遍历该集合，并创建treemap实现map集合中元素的顺序输出
        Map<Integer,String> stu_treemap=new TreeMap<>(new Comparator<Integer>() {
            @Override //o1为原先的元素，o2为新加进的元素
            public int compare(Integer o1, Integer o2) {
                return o2-o1; //按照后一个元素较大的顺序
            }
        });
        stu_treemap.put(123,"123");//map集合没有
        stu_treemap.put(12,"12");
        stu_treemap.put(23,"23");
        stu_treemap.put(1234,"1234");
        out.println(stu_treemap);

        //file类,文件创建、删除练习
        java.io.File instance_of_File=new File("instance_of_File.txt");//创建文件对象
        if(instance_of_File.exists()){
            out.println("instance_of_File exists!");
            instance_of_File.delete();//删除文件，删除的是文件还是对象？？？？结果表面，删除文件实例对象，就是删除文件本身！和C++有区别
            out.println("文件已删除！");

        }else{//文件不存在时，尝试创建文件
            try{
                instance_of_File.createNewFile();//在磁盘中创建文件对象，且必须有IO异常捕捉
                out.println("文件已创建！");

            }catch(IOException e){
                out.println(e);//可以直接打印出
                //e.printStackTrace();也可以直接使用异常自带的打印输出方法
            }
        }

        //练习获取file类详细信息
        File instance_of_file_2=new File("instance_of_file_2.txt");
        try{
            instance_of_file_2.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(instance_of_file_2.exists()){
            out.println(instance_of_file_2.getName());
            out.println("文件的字节大小为："+instance_of_file_2.length());
            out.println("文件的隐藏状态是："+instance_of_file_2.isHidden());
        }


        //文件输入和文件输出流，和上面的文件类有一定的区别，上文的文件类只负责创建“空”文件，具体内容的写入依靠文件流类
        //使用fileinputstream和fileoutputstream，输入流意思是，从文件向程序输入数据流，输出的意思是，从程序向外输出数据流
        File instance_of_file3=new File("instance_of_file3");//创建文件，供文件输入输出流使用
        try{
            java.io.FileOutputStream instance_of_fileoutputstream=new java.io.FileOutputStream(instance_of_file3);//实例化输出流实例
            byte[] out_byte="Go big and go home!".getBytes();//字符串转为字节，IO流的操作对象是字节，本质为字节流
            instance_of_fileoutputstream.write(out_byte);//将字节数组写入到文件中
            instance_of_fileoutputstream.close();//关闭输出流

        }catch(IOException e){
            e.printStackTrace();
        }
        out.println("写入数据后的大小是"+instance_of_file3.length());
        try{
            java.io.FileInputStream instance_of_fileinputstream=new java.io.FileInputStream(instance_of_file3);
            byte[] in_byte=new byte[1024];
            int length=instance_of_fileinputstream.read(in_byte);//读取文件中的字节流，存储到字节数组中，并返回字节流的长度
            instance_of_fileinputstream.close();//关闭输出流
            out.println(new String(in_byte,0,length));//以字节数组的方式创建字符串并输出

        }catch(IOException e){
            e.printStackTrace();
        }

        //filereader类和filewriter类，字符流，避免多字节字符的乱码现象
        //输出输出这种字节处理流的用法一样，只不过不再使用字节数组作为载体，而是字符串

        /*带缓存的输入输出流bufferedinputstream和bufferedoutstream
        带缓存的是一种性能优化，是在流上进行skip，mark和reset成文可能
        就是对原本的文件输入输出流进行包装，增加缓存功能
        带缓存的字节流输入（Input）类的工作过程：文件（字节数据）→Fileinputstream→Bufferedinputstream→程序
        带缓存的字符流写入（writer或者Output）工作过程：文件（字符数据）→BufferedWriter→Output
        */

        //向磁盘文件中写入数据，并通过bufferedreader读取文件信息并显示

        //
        Class_sheng_cheng_全排列 instance_of_Class_sheng_cheng_全排列=new Class_sheng_cheng_全排列();
        instance_of_Class_sheng_cheng_全排列.generate_and_check();
        out.println(instance_of_Class_sheng_cheng_全排列.out_text);




        //
        Class_input_and_output instance_of_Class_input_and_output=new Class_input_and_output();
        instance_of_Class_input_and_output.set_path();
        instance_of_Class_input_and_output.read_text_to_stream();
        instance_of_Class_input_and_output.write_stream_to_text();








































    }
}
