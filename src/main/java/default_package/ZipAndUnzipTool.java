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

    public static List<String> unTarFile2(File file) {
        String basePath = file.getParent() + File.separator;
        TarArchiveInputStream tarArchiveInputStream = null;
        List<String> fileNamesList = new ArrayList<>();
        TarArchiveEntry tarArchiveEntry = null;
        try {
            tarArchiveInputStream = new TarArchiveInputStream(new FileInputStream(file));
            while ((tarArchiveEntry = tarArchiveInputStream.getNextTarEntry()) != null) {
                fileNamesList.add(tarArchiveEntry.getName());
                if (tarArchiveEntry.isDirectory()) {
                    new File(basePath + tarArchiveEntry.getName()).mkdirs();
                }
                FileOutputStream fileOutputStream = null;
                try {
                    File f = new File(basePath + tarArchiveEntry.getName());
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdirs();
                    }
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(f);
                    byte[] bs = new byte[2048];
                    int len = -1;
                    while ((len = tarArchiveInputStream.read(bs)) != -1) {
                        fileOutputStream.write(bs, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tarArchiveInputStream.close();
                // 解压后删除tar包
                // file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回tar包下所有文件名
        return fileNamesList;
    }


}
