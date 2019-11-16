package default_package.Optional探索;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 探索一下的optional的用法
 */


public class Main {
    public static void main(String[] args) {

        try {

            Integer integerNull = null;
            //直接报空指针异常
//            Optional<Integer> optionalInteger = Optional.of(integerNull);


            Integer integer2 = 1;
            Optional<Integer> optionalInteger2 = Optional.ofNullable(integer2);
            Optional<Integer> optionalIntegerNull = Optional.ofNullable(integerNull);
            System.out.println(optionalIntegerNull.isPresent());//因为optional包住的就是一是一个空指针
            System.out.println(optionalInteger2.get());//如果对保包含null的使用则会抛出空指针异常
            System.out.println(optionalIntegerNull.orElse(666));//如果是null则返回一个默认值

            //通过这样的方式可以直接优雅的抛出异常
            //System.out.println(optionalIntegerNull.orElseThrow());


            //值存在时则使用函数式编程的函数实例
            Consumer<Integer> doSomething = (x) -> {
                System.out.println("原始的值为:" + x + "\n经过自身的次幂为：");
                System.out.println(Math.pow(x, x));
            };

            //本质上就是传上面的doSomething进去
            optionalInteger2.ifPresent((x) -> {
                System.out.println("原始的值为:" + x + "\n经过自身的次幂为：");
                System.out.println(Math.pow(x, x));
            });


            //存在的就话就会执行 doSomething，不存在就会执行后面的runnable实例
            Runnable runnable = () -> {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + ":value is null");
                }
            };

            optionalIntegerNull.ifPresentOrElse(doSomething, runnable);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
