package default_package;

public class Class_ji_he_lei_lian_xi {
    //练习集合类
}

class UpdateStu implements Comparable<Object>{  //不写尖括号，默认为基于Object类的
    //更新学生信息的类
    int id;//学生学号
    String name;//学生姓名
    int getId(){
        return this.id;
    }
    String getName(){
        return this.name;
    }

    UpdateStu(int id,String name){  //构造函数
        this.id=id;
        this.name=name;
    }

    public int compareTo(Object o){//comaprable 接口必须实现的方法
        //虽然输入的形参是object类，其本质是向上转型之前的updateStu类
        //因此，还是必须先显式向下转型
        UpdateStu new_updatestu;
        new_updatestu=(UpdateStu)o;//显式向下转型，也就是回到本质
        int result;
        if(this.id>new_updatestu.id){
            result=1;
        }else{
            if(this.id==new_updatestu.id){
                result=0;
            }else{
                result=-1;
            }
        }
        return result;
    }




}
