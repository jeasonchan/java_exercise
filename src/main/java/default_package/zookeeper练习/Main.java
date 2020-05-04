package default_package.zookeeper练习;

import org.apache.zookeeper.*;

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

        String path2 = zooKeeper.create("/test01", "123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建持久化无序号节点：" + path2);


    }
}
