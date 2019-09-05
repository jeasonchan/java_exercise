package default_package;

public class ExercieseForThread {
    public static void run(){
        Runnable runnable=new ThreadSafeTest();//向上转型，直接用原类也没问题
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        Thread thread3=new Thread(runnable);
        Thread thread4=new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }//end public static void run()
}


/*线程安全练习类
* */
class ThreadSafeTest implements Runnable{
    int sharedNumber=10;//用于多个线程共享访问

    @Override
    public void run() {
        while(true){
            synchronized (""){
                if(sharedNumber>0){//第一次出现所用到的资源的地方
                    try{
                        Thread.sleep(1);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    sharedNumber--;
                    System.out.println("sharedNumber:"+sharedNumber);
                }else{
                    break;
                }
            }

        }//end while

    }//end run()
}//end class tThreadSafeTest


