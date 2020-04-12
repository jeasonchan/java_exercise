package default_package.NIO练习;

import java.io.Closeable;
import java.io.IOException;

public class Common {
    public static void closeObj(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
