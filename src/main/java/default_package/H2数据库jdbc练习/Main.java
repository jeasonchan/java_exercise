package default_package.H2数据库jdbc练习;

import java.sql.*;

public class Main {


    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:h2:C:\\CRroot\\documents\\17706436669";//内嵌模式，直接操作文件
        String jdbcUrl = "jdbc:h2:tcp://localhost/C:\\CRroot\\documents\\17706436669";//tcp模式，需要手动打开浏览器连接一下
        String userName = "jeason";
        String passward = "jeason";
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
            //设置全局的数据库超时连接
            DriverManager.setLoginTimeout(1);

            testDbConnection = DriverManager.getConnection(jdbcUrl, userName, passward);
            //一开始感觉就像是把某个具体的数据库抽象成某个类的实例，就想File类一样
            //然而，其实这个connection就只是一个“管道”，去进行增删查改等操作还需要出通过statement和prepareStatement等借口实现

            //显式加载驱动类，对于h2数据库可有可无，如果不显式加载，有些编译器会优化掉没有使用的类
            Class.forName("org.h2.Driver");

            System.out.println(testDbConnection.isClosed());

            //Statement的实例对象用来执行相关的SQL语句
            Statement statement = testDbConnection.createStatement();

            //开始对数据库进行一些操作

            //如果存在相应的表，则删除
            statement.execute("drop table test_table if exists");

            //创建表
            statement.execute("create table test_table" +
                    "(id int primary key,name varchar(100),age int,sex varchar(100))");

            //新增数据
            for (int i = 0; i < 10; i++) {
                String sqlStatement = String.format("insert into test_table(id,name,age,sex) values(%s,%s,%s,%s)",
                        String.valueOf(i), '\u039A' + (char) i + '\u039A', 10 * i, '\u039A'+'男'+'\u039A');
                System.out.println(sqlStatement);
                statement.execute(sqlStatement);
            }

            //查询结果
            System.out.println("开始查询并打印数据库内容：");
            ResultSet resultSet = statement.executeQuery("select * from test_table");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "--" +
                        resultSet.getString(2) + "--" +
                        resultSet.getInt(3) + "--" +
                        resultSet.getString(4));
            }

            //显式释放资源
            statement.close();
            testDbConnection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
