package default_package.zookeeper练习;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(
                "127.0.0.1:2181",
                5 * 1000,
                new SyncConnectedWatcher(),
                true
        );

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());

        TimeUnit.SECONDS.sleep(6);

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());


        zooKeeper.close();

        System.out.println(System.currentTimeMillis() + " " + zooKeeper.getState().toString());
    }
}
