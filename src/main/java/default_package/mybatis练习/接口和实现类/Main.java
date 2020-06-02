package default_package.mybatis练习.接口和实现类;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.*;
import javax.servlet.http.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String myBatisConfig = "mybatis2/config.xml";

        InputStream mybatisConfigStream = ClassLoader.getSystemResourceAsStream(myBatisConfig);


        ((BufferedInputStream) mybatisConfigStream).mark(0);
        System.out.println(myBatisConfig + ":\n" + new String(mybatisConfigStream.readAllBytes()));


    }
}
