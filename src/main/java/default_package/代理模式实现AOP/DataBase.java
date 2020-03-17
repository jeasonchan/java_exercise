package default_package.代理模式实现AOP;

public class DataBase implements Delete {
    @Override
    public void delete(Object object) {
        System.out.println("deleting dateBase:" + object);
    }
}
