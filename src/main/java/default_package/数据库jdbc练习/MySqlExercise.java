package default_package.数据库jdbc练习;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MySqlExercise {
    public static void main(String[] args) throws SQLException {
        /*导入的jar包依赖是

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.19</version>
        </dependency>

        jdbc的驱动名称是  com.mysql.cj.jdbc.Driver

         */

        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "123456";
        String targetDataBase = "test";

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);

            connection.setAutoCommit(false);//禁用批处理sql自动事务

            statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("show databases;");
            int index = 0;
            List<String> dataBaseList = new ArrayList<>();

            while (resultSet.next()) {
                //因为非对象型数据库的字段类型有限,
                //不同的字段类型，从要从resultSet中调用的不同的get类型方法
                dataBaseList.add(resultSet.getString(1));
            }
            System.out.println("origin databases list is:" + dataBaseList);


            //查询有无数据库 test，没有的话就先创建
            if (!dataBaseList.contains(targetDataBase)) {
                dataBaseList.clear();

                statement.execute("create database test;");

                resultSet = statement.executeQuery("show databases;");
                while (resultSet.next()) {
                    //因为非对象型数据库的字段类型有限,
                    //不同的字段类型，从要从resultSet中调用的不同的get类型方法
                    dataBaseList.add(resultSet.getString(1));
                }

                System.out.println("after create, list is:" + dataBaseList);

            }

            //先查询的test 中有无表 table1
            resultSet = statement.executeQuery("show tables from test;");
            boolean hasTable1 = false;
            while (resultSet.next()) {
                if ("table1".equals(resultSet.getString(1))) {
                    hasTable1 = true;
                    break;
                }
            }


            //如果批处理中包含有试图返回结果集的命令，则当调用 Statement. executeBatch() 时，
            // 将抛出 SQLException。
            // 只有 DDL 和 DML 命令（它们只返回简单的更新计数）才能作为批处理的一部分来执行。
            // 批处理中不能使用 show tables
            //statement.addBatch("show tables");
            int[] ints = null;


            //test 不含有表 table1 则在数据库 test 中 创建表
            if (!hasTable1) {
                statement.clearBatch();
                statement.addBatch("use test;");
                statement.addBatch("create table table1(id int not null AUTO_INCREMENT," +
                        "title varchar(100) not null ," +
                        "author varchar(40) not null ," +
                        "submission_date date ," +
                        "primary key(id));");

                ints = statement.executeBatch();

                System.out.println("create table result:" + Arrays.toString(ints));
            }

            //睡眠十秒
            System.out.println("sleep for 20 sec, check the dataBase test and table table1");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //在数据库  test 中删除数据表 table1
//            statement.clearBatch();
//            statement.addBatch("use test;");
//            statement.addBatch("drop table table1;");
//            statement.executeBatch();


            //查找author是jeason的条目
            resultSet = statement.executeQuery("select * from test.table1 where author='jeason';");
            boolean hasJeason = false;
            while (resultSet.next()) {
                System.out.println("jeason条目的id是：" + resultSet.getInt(1));
                hasJeason = true;
            }

            if (!hasJeason) {
                //向test table1 中插入数据
                //不列出具体具体的列，则必须每个列都有对应的值
                statement.clearBatch();
                statement.addBatch("use test;");
                statement.addBatch("insert into table1 values (4,'2333','jeason','2020-12-12');");
                ints = statement.executeBatch();
                System.out.println("insert into table:" + Arrays.toString(ints));

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                connection.close();
            }
            System.out.println("connection.close()");

            if (null != statement) {
                statement.close();
            }
            System.out.println("statement.close()");

        }


    }


    //todo 手动实现一个连接池
    public static Connection getConnection(String driverName, String jdbcUrl, String userName, String password) {
        return null;
    }
}
