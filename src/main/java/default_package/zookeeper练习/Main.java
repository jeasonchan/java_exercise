package default_package.zookeeper练习;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(
                "127.0.0.1:2181",
                5 * 1000,
                new SyncConnectedWatcher(),
                true
        );

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());

        TimeUnit.SECONDS.sleep(6);

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());

        //=============关闭session=========
        zooKeeper.close();

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());


        //============================================================

        //连接server
        CountDownLatch connectLatch = new CountDownLatch(1);

        zooKeeper = new ZooKeeper(
                "127.0.0.1:2181",
                5 * 1000,
                new LatchWatcherProxy(connectLatch,
                        new Watcher() {
                            @Override
                            public void process(WatchedEvent watchedEvent) {
                                System.out.println("触发了监听事件：" + watchedEvent);

                                //经过试验，任何WatchedEvent都出触发该函数调用
                                //但是，与zookeeper连接的过程中，第一个

                                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                                    System.out.println("检测到检测事件，状态为未连接！");
                                }
                            }
                        }),
                true
        );

        connectLatch.await();


        //创造节点，分为异步和同步两种方式

        //调用同步接口创建节点
        String path = zooKeeper.create("/test01", "123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("创建持久化有序号节点：" + path);

//        String path2 = zooKeeper.create("/test01", "123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println("创建持久化无序号节点：" + path2);
//        CreateMode.PERSISTENT 持久、无序号节点


        //调用异步接口创造节点

        //创建临时  有序号节点
        CountDownLatch createdLatch = new CountDownLatch(1);

        zooKeeper.create("/test", "233".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new AsyncCallback.Create2Callback() {

                    @Override
                    public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
                        System.out.println("创建晚开始调用回调对象的processResult方法：");

                        System.out.println(
                                "rc:" + rc + "\n" +
                                        "path:" + path + "\n" +
                                        "ctx:" + ctx + "\n" +
                                        "name:" + name + "\n" +
                                        "stat:" + stat + "\n"
                        );
                        /*
                        rc:0
                        path:/test
                        ctx:异步创建方法的ctx入参
                        name:/test0000000006
                        stat:58,58,1588679750798,1588679750798,0,0,0,72132398734704662,3,0,58

                        其中，对于无序号的节点，path就是name，对于有序号的节点，name比path后面多十位数表示创建顺序
                         */





                        //使用CountDownLatch和匿名函数达到异步转同步
                        createdLatch.countDown();
                    }
                }, "异步创建方法的ctx入参");


        createdLatch.await();

        //以同步的方式查询创建的临时节点的  父节点  下面的全部子节点  名称
        System.out.println(zooKeeper.getChildren("/", false));


    }
}
