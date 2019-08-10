package default_package.exercise_singleten;

public class simple_thread_safe_lazy_man_pattern {
    private static simple_thread_safe_lazy_man_pattern singleton = null;

    private simple_thread_safe_lazy_man_pattern() {
        System.out.println("调用了" + this.getClass() + "简单线程懒汉模式构造函数！");
    }

    public static synchronized simple_thread_safe_lazy_man_pattern getSingleton() {
        //由于同步方法的资源比同步快的资源消耗大，因此，可以考虑进一步缩小同步块范围
        if (singleton == null) {
            singleton = new simple_thread_safe_lazy_man_pattern();
        }
        return singleton;
    }
}
