package default_package.exercise;


import default_package.exercise.bean.Student;

import java.util.ArrayList;
import java.util.List;

/*
流式处理学习
 */
public class StreamProcess {
    public static void main(String[] args) {



    }


    //------------------------------------------------------

    private List<Student> list = new ArrayList<Student>(){
        {
            //add 10 random instances
            for(int i=0;i<10;i++){
                add(Student.newRandomStudent());
            }
        }
    };





}
