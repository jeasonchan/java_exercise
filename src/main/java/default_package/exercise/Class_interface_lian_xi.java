package default_package.exercise;

public class Class_interface_lian_xi {
    //接口说白了就是一种特殊的类，名字不同而已，按需“继承”，也可以进行转型
    //

}
interface draw_interface{
    //接口draw_interface，其中只包含一个方法，draaw()，接口中的方法必须全都要实现
    //接口中的只需要对方法进行声明，不需要方法体
   public void draw();//接口中声明的方法必须都是public的，对包内的类都是可见的
}

class QuardRange_interface implements draw_interface{
    //带接口的四边形类
    public void draw(){  //接口的中的方法声明时都是默认public的，所以，在实现的时候访问范围不能缩小，也必须是public的
        System.out.println("QuardRange_interface.draw()");
    }

}

class Parallelograme_interface extends QuardRange_interface implements draw_interface{
    //继承于带接口的四边形类，同是也必然继承了父类中实现的draw（）方法，所以，即使继承了drawtest接口，因为已经包含了父类的draw()方法，
    //不再显式实现draw()也不会报错
    public void draw(){
        System.out.println("Parallelograme_interface.draw()");
    }

}
