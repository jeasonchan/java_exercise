package default_package.NIO练习.SocketChannel练习.server;


import default_package.NIO练习.Common;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class BIOServer {
    public static void main(String[] args) {
        startServer();
    }


    /*
    基于阻塞的socket server端实现
    功能是，打印出client发过来的message


     */
    public static void startServer() {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;


        try {
            serverSocket = new ServerSocket(8080);

            int readLength = 0;
            byte[] receiveBufferBytes = new byte[1024 * 4];

            while (true) {
                //这一步会一直阻塞，直到有了client连接
                Socket client = serverSocket.accept();

                SocketAddress clientAddress = client.getRemoteSocketAddress();
                System.out.println("Handling client at:" + clientAddress);
                inputStream = client.getInputStream();
                while (-1 != (readLength = inputStream.read(receiveBufferBytes))) {
                    System.out.println("received message:" + new String(receiveBufferBytes, 0, readLength));
                }

                //对该client没有多余操作

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Common.closeObj(serverSocket);
            Common.closeObj(inputStream);
        }


    }


}

