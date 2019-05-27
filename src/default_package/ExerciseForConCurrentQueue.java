package default_package;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class ExerciseForConCurrentQueue {
    /*
     * 测试
     */
    public static void startTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        MyBlockingTaskQueen<Task> queue = new MyBlockingTaskQueen<>(1);
        /*
         * 模拟两个添加任务的线程,共添加2000条任务
         */
        Thread supplier1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                queue.put(new Task("task"+System.currentTimeMillis()));
            }
            System.out.println("===============");
        },"生产者1");
        Thread supplier2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                queue.put(new Task("task"+System.currentTimeMillis()));
            }
            System.out.println("======================");
        },"生产者2");
        /*
         * 模拟两个处理任务的线程，每个线程分1000条
         */
        Thread consumer = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                queue.handle((task)->System.out.println(Thread.currentThread().getName()+":处理"+task));  //对accept接口方法就进行了实现，产生了一个匿名的实现类
                queue.handle(new Consumer<Task>() {
                    @Override
                    public void accept(Task task) {
                        System.out.println(Thread.currentThread().getName()+":处理"+task);
                    }
                });  //两种方法等效，一个是lamda表达式实现，一个是匿名类，两种方法都在接口具体用到时才对接口进行了实现;注意！！lambda表达式只适用于函数式接口，即一个方法的接口！

            }
            System.out.println("c1执行结束");
            latch.countDown();
        },"消费者1");
        Thread consumer2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                queue.handle((task)->System.out.println(Thread.currentThread().getName()+":处理"+task));
            }
            System.out.println("c2执行结束");
            System.out.println(consumer.getState());
            latch.countDown();
        },"消费者2");


        long startT = System.currentTimeMillis();

        consumer.start();
        consumer2.start();
        supplier1.start();
        supplier2.start();

        latch.await();
        long endT = System.currentTimeMillis();

        System.out.println("耗费时间:"+(endT-startT));
        System.out.println("c1:"+consumer.getState());
        System.out.println("c2:"+consumer2.getState());
        System.out.println("s1:"+supplier1.getState());
        System.out.println("s2:"+supplier2.getState());
        System.out.println("队列剩余数量："+queue.getSize());

    }




}

class MyBlockingTaskQueen<T> {

    private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public int getSize(){
        return queue.size();
    }

    private int taskCapacity=10;
    public MyBlockingTaskQueen(int taskCapacity){
        this.taskCapacity = taskCapacity;
    }
    /*
     * 添加任务
     */
    public void put(T task) {
        try{
            lock.lock();
            while(queue.size()>taskCapacity) {
                System.out.println(Thread.currentThread().getName()+":任务数量达到上限，当前线程被挂起");
                cond.await();
            }
            queue.add(task);
            cond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /*
     * 处理任务
     */
    public void handle(Consumer<T>cons) {  //接口，具体的实现方法在使用的时候去实现，是一种新见到的新用法！！！！牛逼！！！
        try {
            lock.lock();
            while(queue.size()<=0) {
                System.out.println(Thread.currentThread().getName()+":当前没有任务，线程被挂起");
                cond.await();
            }
            cons.accept(queue.poll());//这一步只是为了接收弹出去的任务的名称，具体的处理方法，在实际使调用中确定
            cond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}


class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task [name=" + name + "]";
    }
}