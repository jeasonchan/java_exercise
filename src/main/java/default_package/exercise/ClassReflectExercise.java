package default_package.exercise;

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

        Class ClassForExample_01=Example_01.class;//新建Class类用于信息获取
        Constructor[] declaredConstructorsOfExample_01=ClassForExample_01.getDeclaredConstructors();//获取所有的声明过的，包括私有、保护、公开构造方法
        for(Constructor it:declaredConstructorsOfExample_01){//foreach遍历构造对象数组
            Constructor instanceOfConstructor=it;//创建引用指向遍历的每一个对象，其实不是很必要……
            System.out.println("当前构造方法是否允许可变数量参数："+instanceOfConstructor.isVarArgs());

            System.out.println("当前构造方法的参数类型是：");
            Class[] parameterTypes=instanceOfConstructor.getParameterTypes();//获取参数列表
            for(Class itOfClass:parameterTypes){
                System.out.println(itOfClass);
            }

            System.out.println("当前构造方法可能抛出的异常类型是：");//获取异常列表
            Class[] instanceOfExceptions=it.getExceptionTypes();
            for(Class itOfClass:instanceOfExceptions){
                System.out.println(itOfClass);//java中一切皆对象，;可以说，Class类应该是和Object差不多的顶层类了
            }

        }

        //通过反射得到的构造方法访问实例
        Example_01 instanceOfExample=null;



    }//end public static void runFunction()


}


class Example_01{
    //用于反射练习
    String s;
    int i,i2,i3;

    private Example_01(){
        //构造函数
    }

    protected Example_01(String input_string,int intput_i){
        //构造函数
        this.s=input_string;
        this.i=intput_i;
    }

    public Example_01(String...input_string)throws NumberFormatException{
        //抛出异常说明，本方法不想/不能处理抛出的异常，要交给调用本方法的地方处理异常
        if(input_string.length>0){
            this.i=Integer.valueOf(input_string[0]);
        }
        if(input_string.length>1){
            this.i2=Integer.valueOf(input_string[1]);
        }
        if(input_string.length>2){
            this.i=Integer.valueOf(input_string[2]);
        }

    }

    public void print(){
        System.out.println("s:"+this.s);
        System.out.println("i:"+this.i);
        System.out.println("i2:"+this.i2);
        System.out.println("i3:"+this.i3);
    }




}
