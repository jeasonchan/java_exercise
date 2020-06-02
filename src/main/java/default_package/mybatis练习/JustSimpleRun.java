package default_package.mybatis练习;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * 使用配置
 * <p>
 * String myBatisConfig = "mybatis/mybatis-config.xml";
 */


public class JustSimpleRun {
    public static void main(String[] args) throws IOException {

        /*
        第一步，读取配置文件
         */
        String myBatisConfig = "mybatis/mybatis-config.xml";

        //正如注解所说，从类加载的地方寻找资源
        InputStream myBatisStream = ClassLoader.getSystemResourceAsStream(myBatisConfig);

        //也可以直接使用mybatis里提供的类读取文件
        // Resources.getResourceAsStream(myBatisConfig);

        //标记一下，为重复读取做一个书签
        ((BufferedInputStream) myBatisStream).mark(0);
        System.out.println("配置文件是：\n" + new String(myBatisStream.readAllBytes(),
                StandardCharsets.UTF_8));
        //读取游标重新设置到书签处，以供后续的重复读取
        ((BufferedInputStream) myBatisStream).reset();


        /*
        第二步，根据配置文件，按建造者模式，产生工厂实例
         */

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(myBatisStream);


        /*
        第三步，执行mapper中的语句
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println((String) sqlSession.selectOne("MyMapper.selectAuthor", 4));


    }
}
