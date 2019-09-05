package default_package;

public class Class_overload {
    //用于练习函数重载，函数的重载主要是根据参数的类型、个数和顺序来体现
    public int add(int a,int b){
        System.out.println("调用int add(int a,int b)方法");
        return a+b;
    }

    public int add(int...a){  //不定长参数写法
        System.out.println("调用int add(int...a)方法");
        int return_result=0;//返回值
        for(int i=0;i<a.length;i++){
            return_result+=a[i];//不定长参数的使用像数组一样
        }
        return return_result;
    }

    public void add(double b,int...a){//不定长参数必须作为参数列表的最后一个参数，也就是，一个函数只能有一个不定长参数
        //函数体 或者 方法体
    }

}

