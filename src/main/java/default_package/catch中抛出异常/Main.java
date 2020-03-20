package default_package.catch中抛出异常;

public class Main {
    public static void main(String[] args) {
        try {
            throw new NullPointerException("1");

        } catch (NullPointerException e1) {
            System.out.println("line 9");
            System.out.println(e1.getMessage());
            throw e1;

        } catch (Exception e) {
            System.out.println("line 13");
            System.out.println(e.getMessage());
        }


    }

    /*
    控制台输出：
    line 9
    1
    Exception in thread "main" java.lang.NullPointerException: 1
	at default_package.catch中抛出异常.Main.main(Main.java:6)

    Process finished with exit code 1


    得出结论：
    连环catch，一个catch生效后，其余的catch自动失效，就会造成：catch中抛出的异常不会被后面的catch捕捉




     */
}
