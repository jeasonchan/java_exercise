package default_package;

public class Class_yi_chang_lian_xi {
    //异常练习
}

class my_exceptions extends Exception{
    my_exceptions(String ErroeMessage){
        //继承java自带的异常类，可见自带的异常实例可以使用字符串产生一个定义的  异常实例对象
        super(ErroeMessage);//调用父类的构造函数
        message=ErroeMessage;
    }
    String message;
    public String getMessage(){//者覆盖不能减小父类同名方法的访问范围，重载可以。
        return message;
    }
}

class Tran{
    //自定义类，并在方法中抛出异常
    //抛出的异常类是my_exception，当有这个异常发生时，产生一个相应相应的异常类的实例
    static int avg(int a,int b)throws my_exceptions{
        if(a<0||b<0){
            throw new my_exceptions("输入值过小！"); //抛出一个异常实例
        }
        if(a>00||b>100){
            throw new my_exceptions("输入值过大！");
        }
        return (a+b)/2;
    }

}

class Shoot{
    //在方法中抛出异常；上文就已经是在方法中抛出异常类了，而且还是抛出的自定义的异常类
    static void pop()throws NegativeArraySizeException,ArithmeticException{ //定义抛出异常类，这个异常类是系统自带的；方法后面要跟，此方法中可能出现的异常类
        //int a=10/0;
        int[] array=new int[-3];

    }

}

class Captor{
    static int quotient(int x,int y)throws my_exceptions{
        if(y<0){
            throw new my_exceptions("除数不能是负数！");
        }
        return x/y;
    }


}