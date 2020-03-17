package default_package.代理模式实现AOP.反射实现动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object target;//被代理的对象实例

    public Object bind(Object target) {
        this.target = target;

        //返回一个类，拥有被代理的类的所有接口
        //并绑定到当前的this实例
        return
                Proxy.newProxyInstance(
                        target.getClass().getClassLoader(),
                        target.getClass().getInterfaces(),
                        this
                );
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("接口调用前进行打印！");

        //每次调用target的方法时，就是用调用下一行，
        //使用proxy的同名方法，传入args作为入参
        result = method.invoke(target, args);

        System.out.println("接口调用后进行打印！");

        return result;

    }
}
