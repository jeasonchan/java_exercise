package default_package;

public class CommonUtil {
    public static <T> T checkNullAndReturn(T object, Class classtype) {
        if (object == null) {
            throw new RuntimeException(classtype.getName() + ", Null Exception!");
        }
        return object;
    }
}
