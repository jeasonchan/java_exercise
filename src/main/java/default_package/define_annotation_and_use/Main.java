package default_package.define_annotation_and_use;

import java.lang.reflect.Method;


public class Main implements HandlerRecordAnnotation {

    @Record(user = "admin", action = "delete", detail = "delete log")
    public void admin_delete_log() {

        handlerRecordAnnotation();//没有面向切面，还是要手动出发一下

        System.out.println("admin is trying to delete log.");
    }


    @Override
    public void handlerRecordAnnotation() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Record.class)) {
                Record record = method.getAnnotation(Record.class);
                System.out.println("Record annotation info:" +
                        record.user() + " " +
                        record.action() + " " +
                        record.detail());
            }
        }

    }


    public static void main(String[] args) {
        new Main().admin_delete_log();

    }
}
