package default_package.代理模式实现AOP.反射实现动态代理;


import net.sf.cglib.MethodInterceptor;
import net.sf.cglib.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
