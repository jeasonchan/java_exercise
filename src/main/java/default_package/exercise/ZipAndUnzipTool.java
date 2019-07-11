package default_package.exercise;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.*;
import java.util.*;

public class ZipAndUnzipTool {
    public static final String DESK_PATH = "F:\\桌面\\";


    public static List<String> unTarFile2(File file) throws IOException {
        List<String> fileNamesList = new ArrayList<>();
        String basePath = file.getParent() + File.separator;

        // 新建压输入流，从将压缩文件转化为流待处理
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new FileInputStream(file));
        TarArchiveEntry tarArchiveEntry = null;
        while ((tarArchiveEntry = tarArchiveInputStream.getNextTarEntry()) != null) {
            fileNamesList.add(tarArchiveEntry.getName());
            if (tarArchiveEntry.isDirectory()) {
                new File(basePath + tarArchiveEntry.getName()).mkdirs();
                continue;
            }
            FileOutputStream fileOutputStream = null;

            File realFile = new File(basePath + tarArchiveEntry.getName());
            if (!realFile.getParentFile().exists()) {
                realFile.getParentFile().mkdirs();
            }
            if (!realFile.exists()) {
                realFile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(realFile);
            byte[] bufferArray = new byte[2048];
            int len = -1;
            //从当前压缩流读取数据，并写入到缓存数组
            // 当数组写满时，进入循环体，将缓存数组中的数据写入文件流中，写入结束，进行while循环判断
            //当数组不满，且压缩流读取到末尾时，返回-1，再次进入循环体
            while ((len = tarArchiveInputStream.read(bufferArray)) != -1) {
                fileOutputStream.write(bufferArray, 0, len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

        }
        tarArchiveInputStream.close();
        // file.delete();  //delete origin tar pack
        return fileNamesList;
    }

    public static void tarFile(File file, TarArchiveOutputStream tarArchiveOutputStream) {
        TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(file);
    }


    /*
    事实证明，无法将一个实体文件通过File映射，转变为文件夹
     */
    public static void tryToTranslateFileToDir() throws IOException {
        File file = new File(DESK_PATH + File.separator + "newFile.txt");
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("same file delete failure");
            }
            file.createNewFile();
        }
        file.createNewFile();

        System.out.println("check desktop file and input sth to continue");
        InputStream is = System.in;
        System.out.println(is.read());

        File newFile = new File(DESK_PATH + File.separator + "newFile.txt");
        if (newFile.mkdirs()) {
            System.out.println("translate success");
        }
        System.out.println("translate failure");
    }


    public static void main(String[] args) throws IOException {
        tryToTranslateFileToDir();
        Map<String, Object> map = new HashMap<>();
        Iterator iterator=map.entrySet().iterator();


    }
}


