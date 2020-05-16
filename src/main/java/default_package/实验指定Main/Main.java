package default_package.实验指定Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));

        File tempFlile = new File("tempFile");
        if (tempFlile.exists()) {
            System.out.println(tempFlile.getParent());
            System.out.println(tempFlile.getAbsolutePath());
        } else {
            tempFlile.createNewFile();
            System.out.println(tempFlile.getParent());
            System.out.println(tempFlile.getAbsolutePath());
        }


        if (args.length > 0) {
            //java -classpath java_exercise_based_on_idea-1.0-SNAPSHOT.jar default_package.实验指定Main.Main  ../tempFile
            File newFile = new File(args[0]);
            if (newFile.exists()) {

                // 事实证明，运行jar包时，相对路径都是以jar包所在的目录为根目录的
                System.out.println(newFile.getAbsolutePath());
            }

        }

    }
}
