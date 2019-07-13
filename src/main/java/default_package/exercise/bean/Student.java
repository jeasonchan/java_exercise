package default_package.exercise.bean;

import default_package.exercise.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int number;
    private int age;
    private int grade;
    private String name;

    public static Student newRandomStudent(){
        Student student=new Student();
        student.setNumber(CommonUtil.generateSpecificNumbers(6));
        student.setAge(CommonUtil.generateSpecificNumbers(1));
        student.setGrade(CommonUtil.generateSpecificNumbers(0));
        student.setName(CommonUtil.generateSpecificString(5));

        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", age=" + age +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }
}
