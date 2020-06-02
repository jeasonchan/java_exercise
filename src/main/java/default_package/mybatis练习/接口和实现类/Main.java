package default_package.mybatis练习.接口和实现类;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String myBatisConfig = "mybatis2/config.xml";

        InputStream mybatisConfigStream = ClassLoader.getSystemResourceAsStream(myBatisConfig);




    }
}
