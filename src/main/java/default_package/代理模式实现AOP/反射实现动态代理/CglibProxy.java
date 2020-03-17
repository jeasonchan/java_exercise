package default_package.代理模式实现AOP.反射实现动态代理;


import net.sf.cglib.Enhancer;
import net.sf.cglib.MethodInterceptor;
import net.sf.cglib.MethodProxy;

import java.awt.desktop.PreferencesHandler;
import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object target;


    public Object bind(Object target) {
        //this.target = target;
        //太麻烦，不做了……………………
        return null;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
