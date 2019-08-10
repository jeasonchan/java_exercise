package default_package.exercise_singleten;

public class double_check_thread_safe_lazy_man_pattern {
    private double_check_thread_safe_lazy_man_pattern() {
        System.out.println("调用" + this.getClass() + "构造函数！");
    }

    private static double_check_thread_safe_lazy_man_pattern singleton = null;

    public static double_check_thread_safe_lazy_man_pattern getSingleton() {
        if (singleton == null) {
            synchronized (double_check_thread_safe_lazy_man_pattern.class) {
                if (singleton == null) {
                    singleton = new double_check_thread_safe_lazy_man_pattern();
                }
            }
        }
        return singleton;
    }

}
