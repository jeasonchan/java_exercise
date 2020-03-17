package default_package.代理模式实现AOP;

import default_package.代理模式实现AOP.反射实现动态代理.DynamicProxy;

public class Main {
    public static void main(String[] args) {
        new DeleteProxy(new DataBase()).delete("something");

        System.out.println("=====使用反射实现的动态代理========");
        Delete deleteImpl = (Delete) new DynamicProxy().bind(new DataBase());
        deleteImpl.delete("sth");

        System.out.println("=====使用cglib实现的动态代理========");

    }
}
