package default_package.exercise_notify_wait;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    //----------------------------------------
    List<String> stringList = new ArrayList<>();

    /*
     以stringList为锁，一次往其中添加一条信息，然后将所锁对象让给消费者
     */
    public void producer() {
        while (stringList != null) {
            try {
                synchronized (stringList){
                    stringList.add(String.valueOf(System.currentTimeMillis()));
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    消费者，列表不为空时，去取值并且删除相应的值
     */
    public void consumer() {
        while (!stringList.isEmpty()){
            try {
                synchronized (stringList){
                    stringList.add(String.valueOf(System.currentTimeMillis()));
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
