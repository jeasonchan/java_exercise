package default_package.NIO练习.Buffer用法;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("1234567890".getBytes());
        System.out.println((char) buffer.get(buffer.capacity() - 1));


        /*
        打印：
        0
         */
    }
}
