package default_package.exercise;

public class Class_dui_xiang_lei_xing_zhuan_huan {
    /*对象类型转换，有向上转型和向下转型两种，向上和向下的方向是从继承关系树的方向来看的
    * 向上转型，子类当作父类用，由于子类必定包含了父类的全部，因此，子类完全可以当作父类来用，且不用显式进行转型；
    * 向下转型，父类当作子类用，由于父类不一定包含子类的一些成员变量和方法，因此，父类转型成子类不一定会成功，必须先进行instanceof操作符判断父类的实例是否也为子类的实例，
    * 是的话，才能进行显式向下转型
    *
    * 转型是多态的基础！！！也是多态最直观的表现，作用是减少代码的冗余，增加代码的可维护性！比如，父类的一个方法可以适用于所有的子类，都不用进行重载，减少代码冗余*/

}

class QuardRange{
    //四边形类
    QuardRange(){   //java的构造函数
        System.out.println("四边形类构造函数！");
    }
    void draw(QuardRange q){
        System.out.println("执行QuardRange类的draw方法");
    }
}

class Parallelogram extends QuardRange{
    //平行四边形类，继承于 四边形类
    Parallelogram(){
        System.out.println("平行四边形类构造函数！");
    }
    void draw(Parallelogram p){//对父类方法的重载，会根据输入参数而自动选择执行父类还是子类的函数
        System.out.println("执行Parallelogram类的draw方法");
    }
    void new_draw(Parallelogram p){
        System.out.println("执行Parallelogram类的new_draw方法");
    }
}
