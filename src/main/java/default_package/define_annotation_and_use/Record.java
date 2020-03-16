package default_package.define_annotation_and_use;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//适用于方法
@Retention(RetentionPolicy.RUNTIME)//在运行期时要进行使用
public @interface Record {
    String user();

    String action();

    String detail();

}
