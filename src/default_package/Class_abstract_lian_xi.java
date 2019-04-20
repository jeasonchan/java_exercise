package default_package;

public class Class_abstract_lian_xi {
    //抽象类练习
}

abstract class abstract_class_1{
    abstract void add();//抽象方法只需要声明，不需要有方法体
    abstract void add(int a);//只有返回值类型不同并不构成重载关系，编码检查都不通过
    void add(double a){
        System.out.println("抽象类中可以定义实际方法，但是没有必要……但是！！！抽象方法不能放置于非抽象类中！！");
    }

}

//只要继承于抽象类，要么将这个类声明为抽象类，要么在类方法中实现抽象类中的所有抽象方法

class sub_abstract_class_1 extends abstract_class_1{
    void add(){
        //方法体
        //既然继承于抽象类，就必须实现抽象类中的【所有】抽象方法；就算方法体中没有语句，也算是“执行”了语句的
    };
    void add(int a){
        //方法体
    };

}