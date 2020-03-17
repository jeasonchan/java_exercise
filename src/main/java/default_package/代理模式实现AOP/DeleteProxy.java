package default_package.代理模式实现AOP;


import lombok.AllArgsConstructor;

/**
 * Delete接口的代理类，用于在接口执行前和执行后进行一操作
 */

@AllArgsConstructor
public class DeleteProxy implements Delete, Proxy {
    private Delete deleteImpl;


    @Override
    public void doBefore(Object object) {
        System.out.println("doBefore");
    }

    @Override
    public void doAfter(Object object) {
        System.out.println("doAfter");
    }

    //delte 接口是真正的业务接口
    @Override
    public void delete(Object object) {

        //悄悄的调用前置处理
        doBefore(deleteImpl);

        this.deleteImpl.delete(object);

        //悄悄调用后置处理
        doAfter(deleteImpl);

    }
}
