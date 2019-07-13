package default_package.exercise;


import default_package.exercise.bean.Student;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
流式处理学习
 */
public class StreamProcess {
    public static void main(String[] args) {
        StreamProcess instance = new StreamProcess();
        List<Student> filteredList = instance.getAstudentListWhoseAgeIsOushu(instance.getStudentList());
        System.out.println(filteredList);

        filteredList=instance.getAstudentListWhoseAgeIsOushu2(instance.getStudentList());
        System.out.println(filteredList);


    }


    //------------------------------------------------------


    @Getter
    private List<Student> studentList = new ArrayList<Student>() {
        {
            //add 10 random instances
            for (int i = 0; i < 10; i++) {
                add(Student.newRandomStudent());
            }

        }
    };

    public List<Student> getAstudentListWhoseAgeIsOushu(List<Student> list) {
        List<Student> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (Student everyStudent : list) {
            if (everyStudent.getAge() % 2 == 0) {
                result.add(everyStudent);
            }
        }

        return result;

    }

    public List<Student> getAstudentListWhoseAgeIsOushu2(List<Student> list) {
        List<Student> result = new ArrayList<>();

        result = list.stream()
                .filter(entry -> entry.getAge() % 2 == 0)
                .collect(Collectors.toList());

        return result;

    }


}
