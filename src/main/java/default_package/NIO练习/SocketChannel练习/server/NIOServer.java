package default_package.NIO练习.SocketChannel练习.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/*
要点：
1、readLength = socketChannel.read(byteBuffer)，-1表示channel已关闭，>0表示通道没关且读到了字节；
通道已经关闭时，务必cancel当前selectionKey，否则会死循环，因为selectionKey.isReadable()为true并不代表通道没有关闭！！

2、selectionKey被cancel后，还去调用isAcceptable之类的操作，会抛出已经取消的异常；因此，必须使用if  else 来保证情况之间是互斥的，
从而避免，前面的操作已经取消了，下面仍然去判断从而抛出异常

 */

public class NIOServer {
    public static void main(String[] args) {
        startServer();
    }


    private static final int BUF_SIZE = 1024 * 4;
    private static final int PORT = 8080;
    private static final int Selector_TIMEOUT = 3000;

    private static void startServer() {


        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;


        try {

            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));//绑定/启动的socket服务端口8080
            serverSocketChannel.configureBlocking(false);//配置了非阻塞才能使用Selector


            //重点来了！！！！！！！！！！！！！！！！！
            //在多路监控中注册当前的信道，就绪的时间类型是 SelectionKey.OP_ACCEPT
            //即，这个信道上发生 SelectionKey.OP_ACCEPT  时，则认为其信道已经就绪
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                //This method performs a blocking
                if (0 == selector.select(Selector_TIMEOUT)) {
                    System.out.println(Selector_TIMEOUT + " no channel ready");
                    continue;
                }

                System.out.println("就绪个数：" + selector.selectedKeys().size());

                //获取就绪的信道集合
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();


                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();

                    if (selectionKey.isAcceptable()) {
                        handleAccept(selectionKey);
                    } else if (selectionKey.isConnectable()) {
                        handleConnect(selectionKey);
                    } else if (selectionKey.isReadable()) {
                        handleRead(selectionKey);
                    } else if (selectionKey.isWritable()) {
                        handleWrite(selectionKey);
                    }

                    selectionKeyIterator.remove();
                    // 当前的获取accept和read的channel已经remove过了
                    // 但是！！！！channel的read状态确实一直有的，handleRead(selectionKey);  这一句会一直执行，造成死循环

                }


                System.out.println("就绪个数：" + selector.selectedKeys().size());


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void handleWrite(SelectionKey selectionKey) {
        System.out.println("Start to handle selectionKey.isWritable()");

        try {
            SocketChannel sc = (SocketChannel) selectionKey.channel();

            ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
            buf.flip();
            while (buf.hasRemaining()) {
                sc.write(buf);
            }

            //虽然前面已经使用了while循环进行读取，到这里的还是要压缩一下
            buf.compact();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleRead(SelectionKey selectionKey) {
        System.out.println("Start to handle selectionKey.isReadable()");

        //取出channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //取出附加的对象
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

        try {

            byteBuffer.clear();
            int readLength = 0;

            //socketChannel.read(byteBuffer)  会向Buffer中写入数据
            while ((readLength = socketChannel.read(byteBuffer)) > 0) {
                System.out.println("readLength:" + readLength);

                byteBuffer.flip();//更新position位置，为读取做好准备

                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }

                byteBuffer.clear();


            }

            if (-1 == (readLength = socketChannel.read(byteBuffer))) {
                System.out.println("收到Client的Close()指令！取消可读监听！");

                selectionKey.cancel();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            Common.closeObj(socketChannel);
        }


    }

    private static void handleConnect(SelectionKey selectionKey) {
        System.out.println("Start to handle selectionKey.isConnectable()");
    }

    private static void handleAccept(SelectionKey selectionKey) {
        System.out.println("Start to handle selectionKey.isAcceptable()");

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("Start to handle client:" + socketChannel.getRemoteAddress());

            socketChannel.configureBlocking(false);

            //将Client通道的可读状态进行注册，并且附加一个缓存对象，用于处理
            //因此，还要额外实现，对可读 SelectionKey  的处理
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
