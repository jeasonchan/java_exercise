package default_package.mybatis练习.接口和实现类;

import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;

import java.util.List;

public interface UserDao {

    public List<User> queryAllUsers();

}
