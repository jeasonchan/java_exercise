package default_package.mybatis练习.接口和实现类;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSession sqlSession;




    @Override
    public List<User> queryAllUsers() {


        return null;
    }
}
