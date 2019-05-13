package default_package;

import java.lang.reflect.Constructor;

public class ClassReflectExercise {
    //add this class to practice reflect in java
    //Class类
    //该类的常用方法有getPackage、getName等

    public static void runFunction(){
        //运行一下直接的例子，将执行语句从main方法转移到相应的类中
        Integer instanceIeteger=123456;
        Class instanceOfClassItegeger=instanceIeteger.getClass();
        Constructor[] arrayOfConstructor=instanceOfClassItegeger.getConstructors();//构造方法类组成的数组
        try {
            Constructor instanceOfConstructor=instanceOfClassItegeger.getConstructor(int.class);//通过反射获得特定形参的public权限的构造方法，必须有异常处理
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Ieteger类的public构造方法数为："+arrayOfConstructor.length);






    }

}
