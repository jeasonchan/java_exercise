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

            connection.setAutoCommit(false);//设为false，关闭自动事务；对数据库的更改生效只发生在commit之后
            // 为true时，每句statement执行都是一条事务；

            //JDBC默认隔离级别是4 可重复读级别，可避免不可重复读，不可避免幻读
            System.out.println("当前事务隔离级别是：" + connection.getTransactionIsolation());

//            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

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
            System.out.println("sleep for 10 sec, check the dataBase test and table table1");
            try {
                TimeUnit.SECONDS.sleep(10);
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

            //练习jdbc中的事务
            //由于之前的以已设置为  非自动提交，如果不connection.commit()，创库、创表会有效，但增删改数据会无效
            //设置false，直接close connection  不会触发  commit操作，也就不会

            statement.clearBatch();
            statement.addBatch("use test;");
            statement.addBatch("insert into table1 (title,author,submission_date) values ('title','jeason','2020-12-12');");
            statement.executeBatch();

            System.out.println("查看一下，未提交之前是否已经更新数据！");
            //实际证明：DML 语句 不进行commit，数据库就不会被改变！！！增删改的实际生效，只在commit之后！！！
            //不commit，直接退出并不会修改的表数据，为什么还要rollback？？
            //因为：事务的数据是先更新到日志之中的，只有commit提交之后,才真正再从日志中更新到mysql表中
            TimeUnit.SECONDS.sleep(10);


            System.out.println("开始connection commit！");
//            connection.commit();

        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
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
