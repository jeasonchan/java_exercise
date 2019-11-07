package default_package.Optional探索;


import java.util.Optional;

/**
 * 探索一下的optional的用法
 */


public class Main {
    public static void main(String[] args) {

        try {

            Integer integer1 = null;
            Optional<Integer> optionalInteger = Optional.of(integer1);









        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
