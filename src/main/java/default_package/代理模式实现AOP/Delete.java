package default_package.代理模式实现AOP;

public interface Delete {
    default void delete(Object object) {
        System.out.println("deleting " + object);
    }
}
