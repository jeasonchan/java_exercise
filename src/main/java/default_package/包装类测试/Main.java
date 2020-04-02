package default_package.包装类测试;

public class Main {
    public static void main(String[] args) {
        Integer i1 = 1;
        Integer i2 = 2;
        System.out.println(i1 == i2);
        System.out.println(new Integer(1) == new Integer(1));
    }
}
