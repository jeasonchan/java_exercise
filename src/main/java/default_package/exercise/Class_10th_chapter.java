package default_package.exercise;

public class Class_10th_chapter {
    //第十章，接口、继承与多态


}



class Class_test1{
    public Class_test1(){
        System.out.println("执行Class_test1构造方法！");
    }

    protected Class_test1 doit(){
        //非静态方法
        System.out.println("执行Class_test1非静态成员方法！");
        return new Class_test1();//要返回一个对象，需要调用构造函数，返回的其实是新构造出来的对象的引用，并不是真的对象
    }

}


class Class_test2 extends Class_test1{//显式继承于Class_test1
    public Class_test2(){
        super();//调用父类构造方法
        super.doit();//调用父类非静态成员方法
    }
    protected Class_test2 doit(){
        //非静态方法
        return new Class_test2();//返回值，重写了父类的doit()非静态方法，重写方法的返回值为类时，新方法的返回值必须是旧返回值的子类
    }

}

//calss Class_1   暂未完成
