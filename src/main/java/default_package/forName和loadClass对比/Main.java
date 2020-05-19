package default_package.forName和loadClass对比;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Main方法第一行。");

        if (args.length == 1) {
            String flag = args[0];

            switch (flag) {
                case "0":
                    //啥都不做
                    break;
                case "1":
                    //使用forName进行类加载
                    Class.forName("default_package.forName和loadClass对比.Person");

                case "2":
                    //使用classloader
                    ClassLoader.getSystemClassLoader().loadClass("default_package.forName和loadClass对比.Person");
//                    ClassLoader.getPlatformClassLoader().loadClass("default_package.forName和loadClass对比.Person");


            }


        }


    }
}
