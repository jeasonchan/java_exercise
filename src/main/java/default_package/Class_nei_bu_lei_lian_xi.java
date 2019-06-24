package default_package;

public class Class_nei_bu_lei_lian_xi {
    //进行内部类练习
    //内部类主要分为三种：成员内部类、局部内部类、匿名类
}

class outer_class{
    //外部类
    public int number;

    class inner_class{
        //内部类
        int inner_number;
        inner_class(){   //内部类构造函数
            System.out.println("inner_class构造函数执行……");
        }
        public void doit(){   //内部类非静态成员方法
            number++;//可以直接使用外类中成员变量，毕竟内部类也是一个成员变量
            System.out.println("inner_class.doit()");
        }

    }




}

//构造outer_class2，和out_class的区别是探索一种新的返回内部类引用的方式，默认就可行的方法是 直接通过外部类的实例  调用内部类构造函数
//此处探讨的是通过外部类的方法，弹回
class outer_class2{
    //外部类
    public int number;
    inner_class instance_inner_class=new inner_class();//必须在这里实例化一个内部类！！！！！如果在下面的方法中实例化一个内部类，也只是一个局部变量，生存期只存在于方法执行期间，
    //因此，必须在外部内的成员变量中放一个内部类的实例用于传递到外面！！！！！！！！！
    inner_class get_reference_of_inner_class(){
        instance_inner_class.inner_number++;//外部类访问内部类的成员，要指定访问域
        return instance_inner_class;
    }

    class inner_class{
        //内部类
        int inner_number;
        inner_class(){   //内部类构造函数
            System.out.println("inner_class构造函数执行……");
        }
        public void doit(){   //内部类非静态成员方法
            System.out.println("inner_class.doit()");
        }

    }

}

//让内部类实现接口中的函数，再将实现的函数返回出来供人使用，从而隐藏函数的实现细节，要点是：
// 内部类为private类型，内部类实现的接口要通过外部类返回到外部，并且通过接口和向上转型原理存储这个返回出来的引用
interface Do_sth{
    public void do_sth();//只需要声明
}

class outer_class_3{


    //内部类
    private class inner_class_3 implements Do_sth{
        public void do_sth(){
            System.out.println("interface D0_sth.do_sth() implemented by inner_class_3");
        }
    }
    //由于内部类是 私有类型 ，想要传递出函数，只能用外部内的成员方法将其返回；但是，返回去用什么引用接收呢？？？
    //再加上inner class对外是不可访问的，只能通过向上转型接收这个inner class返回值，但是，实际还是inner class实例，只是引用是向上转型后的接口
    inner_class_3 instance_of_inner_class_3=new inner_class_3();
    public inner_class_3 get_inner_class_3(){
        return new inner_class_3();
    }
    public Do_sth get_interface_in_inner_class(){
        return new inner_class_3();
    }


}
