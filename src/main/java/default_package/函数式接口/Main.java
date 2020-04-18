package default_package.函数式接口;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        //适用匿名函数实现接口
        GreetService greetService = new GreetService() {

            @Override
            public void greet(String name) {
                System.out.println("匿名函数实现接口：");
                System.out.println("Hello " + name);
            }
        };

        greetService.greet("jeason");


        //使用lambda表达式实现接口
        //lambda能省略这么多是因为，输入输出能直接推断出来
        GreetService greetService1 = (name) -> System.out.println("Hello" + name);

        GreetService greetService2 = (name) -> {
            System.out.println("Hello" + name);
        };


        //因为接口中未定义的函数是唯一的，完全符合lambda表达式的唯一结果推断
        //单条语句时，甚至可以省略用于返回的return
        ReturnGreeting returnGreeting = (name) -> "Hello" + name;

        ReturnGreeting returnGreeting1 = (name) -> {
            return "Hello" + name;
        };


    }
}
