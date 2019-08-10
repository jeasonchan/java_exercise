package default_package.exercise_singleten;

public class Hungry_man_pattern {
    private Hungry_man_pattern(){
        System.out.println("调用"+this.getClass()+"饿汉模式构造函数!");
    }

    private static Hungry_man_pattern singleton=new Hungry_man_pattern();

    public static Hungry_man_pattern getSingleton(){
        return singleton;
    }
}
