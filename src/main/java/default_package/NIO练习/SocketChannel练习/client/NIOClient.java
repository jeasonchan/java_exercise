package default_package.NIO练习.SocketChannel练习.client;

import default_package.NIO练习.Common;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class NIOClient {
    public static void main(String[] args) {
        startClient();
    }


    /*
    NIO实现的socket client实现

    功能是：
     */

    public static void startClient() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            //如果socketChann是非阻塞模式的，这一步会阻塞，直到连接成功
            socketChannel.connect(new InetSocketAddress(8080));


            /*
             * finishConnect()
             *<p> If this channel is already connected then this method will not block
             * and will immediately return {@code true}.  If this channel is in
             * non-blocking mode then this method will return {@code false} if the
             * connection process is not yet complete.  If this channel is in blocking
             * mode then this method will block until the connection either completes
             * or fails, and will always either return {@code true} or throw a checked
             * exception describing the failure.
             * */
            while (!socketChannel.finishConnect()) {
                System.out.println("NOT connect yet...");
                //就算是 socketChannel.configureBlocking(false);  连接也是需要时间的
                //非阻塞的Channel，调用finishConnect会立即返回状态
                //但是，也只有返回true时，接下来的才可以继继续进行下去
                //这里暂时还未用到selector
            }

            System.out.println("Connect success!");

            int i = 0;
            while (i < 10) {
                TimeUnit.SECONDS.sleep(1);
                String message = "This is NO." + i + " message.";
                byteBuffer.clear();

                //向buffer中写入字节，position会移动
                byteBuffer.put(message.getBytes());

                //调整索引位置，准备让系统读取数据
                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer);
                    socketChannel.write(byteBuffer);//其实没必要用while，这种写法代表一次性写完

                }

                i++;

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.closeObj(socketChannel);
        }


    }


}
