package default_package.forName和loadClass对比;

public class Person {
    public static String palnet = "NULL";


    static {
        System.out.println("line7 执行了。静态初始化区域代码执行。");
    }


    public static void chanePalnet() {
        Person.palnet = "earth";
    }


}
