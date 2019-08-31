package default_package.exercise.H2数据库jdbc练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        String jdbcUrl = "jdbc:h2:./testdb";
        String userName = "jeason";
        String passward = "0425";
        Connection testDbConnection = null;

        /*
        使用前要先导入h2数据库的驱动，本文使用maven中的数据库驱动jar包，
        如果不在maven中引入 h2.1.4.199.jar则在运行时报错:
        java.sql.SQLException: No suitable driver found for jdbc:h2:./testdb
        很显然是没有找到h2数据库的驱动，那他是如何知道这个数据库的类型的？
        可以看一下 Connection getConnection(
        String url, java.util.Properties info, Class<?> caller) 的源码

        看上去好像是遍历所有注册类，一个接一个的进行连接尝试
         */



        try {
            testDbConnection = DriverManager.getConnection(jdbcUrl, userName, passward);
            //感觉就像是把某个具体的数据库抽象成某个类的实例，就想File类一样

            System.out.println(testDbConnection.isReadOnly());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
