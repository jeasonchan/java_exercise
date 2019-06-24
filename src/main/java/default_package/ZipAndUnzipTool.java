package default_package;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ZipAndUnzipTool {
    public static List<String> unTarFile(File file) throws IOException {
        List<String> fileNameList = new ArrayList<>();
        String parentPath = file.getParent(); //得到父目录
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new FileInputStream(file));

        TarArchiveEntry tarArchiveEntry = null;

        while ((tarArchiveEntry = tarArchiveInputStream.getNextTarEntry()) != null) {
            String name = tarArchiveEntry.getName();
            fileNameList.add(name);
            File targetDir = new File(parentPath + "\\" + name);  // 从将父目录下的文件条目新建一个文件对象
            if (targetDir.getParentFile().exists()) {
                targetDir.mkdirs();
            }

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetDir));

            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = tarArchiveInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, read); //将解压出的文件写入到缓冲流中
            }
            bufferedOutputStream.close();
        }

        tarArchiveInputStream.close();
        return fileNameList;
    }

    public static List<String> unTarFile2(File file) throws IOException {
        List<String> fileNamesList = new ArrayList<>();
        String basePath = file.getParent() + File.separator;

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



}
