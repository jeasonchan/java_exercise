package default_package.zookeeper练习;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class LatchWatcherProxy implements Watcher {
    private CountDownLatch countDownLatch = null;
    private Watcher watcher = null;

    public LatchWatcherProxy(CountDownLatch countDownLatch, Watcher watcher) {
        this.countDownLatch = countDownLatch;
        this.watcher = watcher;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        watcher.process(watchedEvent);

        countDownLatch.countDown();
    }
}
