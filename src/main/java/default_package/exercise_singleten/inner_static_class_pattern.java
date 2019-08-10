package default_package.exercise_singleten;

public class inner_static_class_pattern {
    private static class SingetonHoler{
        public final static inner_static_class_pattern singleton=new inner_static_class_pattern();
    }

    private inner_static_class_pattern(){
        System.out.println("调用"+this.getClass()+"构造函数！");
    }

    public static inner_static_class_pattern getSingleton(){
        return SingetonHoler.singleton;
    }
}
