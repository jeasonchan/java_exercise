package default_package.数据库练习;

import java.sql.*;
import java.util.ArrayList;
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
            System.out.println("origin list is:" + dataBaseList);


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
}
