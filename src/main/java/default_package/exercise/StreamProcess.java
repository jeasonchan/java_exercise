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

        filteredList = instance.getAstudentListWhoseAgeIs_Oushu_2(instance.getStudentList());
        System.out.println(filteredList);

        filteredList = instance.getAstudentList_age_cong_da_dao_xiao_pai_xu(instance.getStudentList());
        System.out.println(filteredList);

        List<String> temp= instance.getListMappedByName(instance.getStudentList());
        System.out.println(temp);


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

    public List<Student> getAstudentListWhoseAgeIs_Oushu_2(List<Student> list) {
        List<Student> result = new ArrayList<>();

        result = list.stream()   //集合转换为Stream类，能转换的还有数组、文件
                .filter(entry -> entry.getAge() % 2 == 0)   //中间操作，Stream的实例方法，函数式写法，还有其余多种中间操作
                .collect(Collectors.toList());  //终端操作，对中间操作产生的新Stream的后续处理，本质仍然是Stream的实例方法，
        //只要是Stream的实例方法，都可以直接往后接；可以追加多个Stream方法进行处理

        return result;

    }

    public List<Student> getAstudentList_age_cong_da_dao_xiao_pai_xu(List<Student> list) {
        List<Student> result = new ArrayList<>();

        result = list.stream()   //集合转换为Stream类，能转换的还有数组、文件
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())   //中间操作，Stream的实例方法，函数式写法，还有其余多种中间操作
                .collect(Collectors.toList());  //终端操作，对中间操作产生的新Stream的后续处理，本质仍然是Stream的实例方法，
        //只要是Stream的实例方法，都可以直接往后接；可以追加多个Stream方法进行处理

        return result;

    }

    public List<String> getListMappedByName(List<Student> list){
        List<String> result=new ArrayList<>();

        result=list.stream()
                .map(Student::getName)   //将Stream中的每个entry，映射为entry的Name成员属性，每个entry的类型变为Name属性的String类
                .collect(Collectors.toList());

        return result;
    }



}
