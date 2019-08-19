package default_package.file和inputStream和outputStream互转;

import java.io.*;

public class CommonUtil {
    public static void closeStream(Closeable stream) {
        if (stream == null) {
            return;
        }
        try {
            stream.close();
        } catch (IOException e) {
            System.out.println("流关闭失败！");
            e.printStackTrace();
        }
    }

    public static InputStream fileToInputStream(File file){
        try {
            InputStream inputStream=new FileInputStream(file);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }





}
