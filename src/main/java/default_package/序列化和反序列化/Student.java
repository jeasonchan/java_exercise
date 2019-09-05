package default_package.序列化和反序列化;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private String name;
    private int age;
    private String school;

    public Student() {
        //默认的无参构造函数
    }

    public Student(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public String getName() {
        return this.name;
    }

//    @JsonProperty("school")
    public String getStudentSchool() {
        return this.school;
    }

//    @JsonProperty("school")
    public void set111(String school) {  //为了正常序列化特意加上的
        this.school = school;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                '}';
    }
}
