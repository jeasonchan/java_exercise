package default_package.交换两个整数的值;

public class Main {
    public static void swap(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;

    }

    public static void swap(Number a, Number b) {
        System.out.println(a);
        Number temp = a;
        a = b;
        System.out.println(a);
        b = temp;

    }


    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        System.out.println(a + "**" + b);
        swap(a, b);
        System.out.println(a + "**" + b);

        //=====================
        Number number1 = new Number(1);
        Number number2 = new Number(2);
//        System.out.println(number1 + "****" + number2);
        swap(number1, number2);
//        System.out.println(number1 + "****" + number2);
        System.out.println(number1);
    }
}
