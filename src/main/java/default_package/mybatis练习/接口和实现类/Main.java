package default_package.mybatis练习.接口和实现类;

import java.io.InputStream;

import javax.servlet.*;
import javax.servlet.http.*;

public class Main {
    public static void main(String[] args) {
        String myBatisConfig = "mybatis2/config.xml";

        InputStream mybatisConfigStream = ClassLoader.getSystemResourceAsStream(myBatisConfig);



    }
}
