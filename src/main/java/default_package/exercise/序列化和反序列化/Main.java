package default_package.exercise.序列化和反序列化;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Student student1 = new Student();
        System.out.println("student1:\n" + student1);
        Student student2 = new Student("jeason", 25, "ZJU");
        System.out.println("student2:\n" + student2);

        /**
         * 使用objectMapper进行序列化和反序列化
         */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);//序列化设置，为true是标准的json串，自带换行
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String student1_o2m_json = objectMapper.writeValueAsString(student1);

        System.out.println(student1_o2m_json);
        /*
        输出：
        {
          "name" : null,
          "age" : 0,
          "studentSchool" : null
        }
         */

//        System.out.println(objectMapper.readValue(student1_o2m_json, Student.class));
        //单纯的使用上面的方式，会报错，因为:
        //1、json串中的 studentSchool 并不是属性名！无法映射为一个属性值
        //2、同时，该属性于是private的，并且又缺少 setStudentSchool() 的方法，更加不能映射了

        //改进方法：增加 public void setStudentSchool(String school)
        //System.out.println(objectMapper.readValue(student1_o2m_json, Student.class));
        //输出：
        //Student{name='null', age=0, school='null'}


        System.out.println("========无脑的的jackson用法=======");
        //以上objectmapper使用方式有一定的弊端，转换得到的json串中得到属性名依赖get set方法中的名字
        //推荐以下方法，只使用属性名，不使用get set方法
        ObjectMapper objectMapper_recommand = new ObjectMapper();
        objectMapper_recommand.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper_recommand.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper_recommand.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);//忽略getter方法1
        objectMapper_recommand.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);//忽略setter方法
        objectMapper_recommand.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);//所有可见范围的属性都进行序列化
        String student2_json = objectMapper_recommand.writeValueAsString(student2);
        System.out.println(student2_json);
        System.out.println(objectMapper_recommand.readValue(student2_json, Student.class));
        //序列化和反序列化都能完美执行，但是！！这种方法会被迫对全部的属性都进行序列化，并且忽略ignore的注解

        objectMapper.readTree(student2_json).forEach(jsonNode -> {
            System.out.println(jsonNode.asText("not get the name content"));
        });
        //输出：
        /*
        jeason
        25
        ZJU
         */


    }
}
