package default_package.数据库练习;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                statement.execute("create database test;");

                resultSet = statement.executeQuery("show databases;");
                while (resultSet.next()) {
                    //因为非对象型数据库的字段类型有限,
                    //不同的字段类型，从要从resultSet中调用的不同的get类型方法
                    dataBaseList.add(resultSet.getString(1));
                }

                System.out.println("after create, list is:" + dataBaseList);

            }

            //在数据库 test 中 创建表

            statement.addBatch("use test;");
            statement.addBatch("create table table1(id int not null AUTO_INCREMENT," +
                    "title varchar(100) not null ," +
                    "author varchar(40) not null ," +
                    "submission_date date ," +
                    "primary key(id));");

            int[] ints = statement.executeBatch();

            System.out.println("create table result:" + Arrays.toString(ints));


            statement.clearBatch();


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
