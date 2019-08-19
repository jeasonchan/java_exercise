package default_package.file和inputStream和outputStream互转;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File file = new File("testFile.txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除文件成功！");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getAbsoluteFile());
                /*
                /home/jeason/IdeaProjects/java_exercise/testFile.txt
                /home/jeason/IdeaProjects/java_exercise/testFile.txt
                可见，File file = new File("testFile.txt"); 这样的创建路径，会在工程的最顶层创建文件
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //=================================================
        file = new File("./testFile2.txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除文件成功！");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getAbsoluteFile());
                /*
                /home/jeason/IdeaProjects/java_exercise/./testFile2.txt
                /home/jeason/IdeaProjects/java_exercise/./testFile2.txt
                file = new File("./testFile2.txt");
                虽然路径中间多了个点，但是，实际上，和前一个文件在同一级
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //============================================
        file = new File("../testFile3.txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除文件成功！");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getAbsoluteFile());
                /*
                /home/jeason/IdeaProjects/java_exercise/../testFile3.txt
                /home/jeason/IdeaProjects/java_exercise/../testFile3.tx
                file = new File("../testFile3.txt");
                路径开头时两个点，生成的文件已经和项目的顶层目录同一级了，可见  ../  这个相对地址是有效的
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 总结！！！
         * new File()中的path，其实都是相对于项目的最外层文件夹来说的，即和 .idea 文件同级的文件夹，
         * 使用 ./xxxx  这种相对路径写法就说明和 .idea 同级，什么的都不写，和 ./xxxxxx  效果一样
         * 同理，使用  ../xxxxxx 自然就是和 .idea 的父文件夹同级了
         * 最后，相对地址全都是相对于项目的总目录来说的！
         */

        //==============================开始输入流、输出流、文件类的相互转换==============================


    }


}
