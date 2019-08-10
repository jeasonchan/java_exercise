package default_package.exercise_singleten;

public class Lazy_man_pattern {
    /*
    饿汉模式,类还没有使用前就创建进内存
     */
    private Lazy_man_pattern() {
        System.out.println("调用" + this.getClass() + "的构造函数!");
    }

    private static Lazy_man_pattern singleton = null;

    public static Lazy_man_pattern GetSingleton() {
        if (singleton == null) {
            singleton = new Lazy_man_pattern();
            System.out.println("完成懒汉模式单例的初次初始化!");
        }
        return singleton;
    }

}
