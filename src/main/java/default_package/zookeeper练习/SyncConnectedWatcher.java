package default_package.zookeeper练习;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class SyncConnectedWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("触发了事件监视器：" + watchedEvent);

        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("捕捉到同步连接事件！");

//事时证明：构造函数里的watch只会被调用一次，并不会被zookeeper的原生api反复调用
//            if (Event.KeeperState.Disconnected == watchedEvent.getState()) {
//                System.out.println("捕捉到连接断开事件！");
//            }


        }
    }
}
