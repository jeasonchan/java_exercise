package default_package.list线程安全实践;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<String> list=new ArrayList<>();
        Main main=new Main();
        List<String> list2=list;


        new Thread(()->main.add_1(list),"add_1").start();
        new Thread(()->main.add_2(list2),"add_2").start();
        //试验证明，无论引用名是什么，只要指向同一内存区域，锁的都是同一实例！！！！！



    }

    //-----------------------------------------------------------------------
    private int i=0;


    public void add_1(List<String> list){
        synchronized (list){//这种写法，一旦拿到锁，就不会释放了，因为while（true）永远不会跳出，不会把锁让出去
            while (true){
                list.add(String.valueOf(System.currentTimeMillis()));
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public void add_2(List<String> list){
        synchronized (list){
            while (true){
                list.add(String.valueOf(System.currentTimeMillis()));
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
