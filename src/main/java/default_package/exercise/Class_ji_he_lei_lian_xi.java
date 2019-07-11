package default_package.exercise;

public class Class_ji_he_lei_lian_xi {
    //练习集合类
}


//练习set中的treeset实现
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
        UpdateStu old_updatestu;//和前者比较
        old_updatestu=(UpdateStu)o;//显式向下转型，也就是回到本质
        int result;//方法的return值
        if(this.id>old_updatestu.id){
            result=1;//按照新的元素比前者大的顺序排列，即为从小到大的顺序排列
        }else{
            if(this.id==old_updatestu.id){
                result=0;
            }else{
                result=-1;
            }
        }
        return result;
    }

}

class Emp implements Comparable<Object>{
    //
    private int e_id;
    private String e_name;
    Emp(int e_id,String e_name){
        this.e_id=e_id;
        this.e_name=e_name;
    }
    public int compareTo(Object o){
        Emp front_e=(Emp)o;
        if(this.e_id>front_e.e_id){
            return -1;
        }else{
            if(this.e_id==front_e.e_id) return 0;
            else return 1;
        }
    }
}


