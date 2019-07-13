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
        student.setAge(2);
        student.setGrade(1);
        student.setName(CommonUtil.generateSpecificString(5));

        return student;
    }
}
