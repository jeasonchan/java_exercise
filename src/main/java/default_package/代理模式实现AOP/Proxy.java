package default_package.代理模式实现AOP;

public interface Proxy {
    //前置处理
    void doBefore(Object object);

    //后置处理
    void doAfter(Object object);
}
