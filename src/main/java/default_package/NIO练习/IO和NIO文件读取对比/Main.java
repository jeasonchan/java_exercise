package default_package.NIO练习.IO和NIO文件读取对比;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Main {


    /*
    文件的流操作，务必注意的在Finally中进行流关闭，此例暂不考虑这些
     */
    public static void main(String[] args) throws IOException {
        //==========用IO流读取文件===========
        long start = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream("test.txt");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        byte[] bytesBuffer = new byte[1024 * 4];//1个page的大小，符合IO读取的设计逻辑
        int readLength = 0;

        while ((readLength = bufferedInputStream.read(bytesBuffer)) != -1) {
            System.out.println(new String(bytesBuffer, 0, readLength, StandardCharsets.UTF_8));
        }

        //IO流操作，哪一步是从用户态切换到内核态，也就是哪一步会形成IO阻塞？？？？？


        //=================NIO========并没有使用Select，也没有使用reactor模式==================
        fileInputStream = new FileInputStream("test.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4);

        byteBuffer.clear();//以防万一，先clear一下

        while (-1 != (readLength = fileChannel.read(byteBuffer))) {
            byteBuffer.flip();//需要将buffer从读完的状态切换到写入状态时调用该方法

            while (byteBuffer.hasRemaining()) {
                //!!!!!!!!!!!!!!!!!!!!!!!!!
                // byteBuffer.get()  会移动当前的指针索引，就跟Iterator.next()差不多
                // 如果 只 byteBuffer.array()  而不进行索引移动，会一直死循环，因为 byteBuffer.hasRemaining()  始终为true

                System.out.println((char) byteBuffer.get());
                //因为是英文，用char足够表示，如果是中文，必乱码，一个中文无法用一个char表示

//                System.out.println(new String(byteBuffer.array(), 0, readLength, StandardCharsets.UTF_8));


            }

            byteBuffer.compact();//

        }

        //======================================================


    }


}
