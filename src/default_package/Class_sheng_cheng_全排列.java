package default_package;

import java.util.ArrayList;
import java.util.List;

public class Class_sheng_cheng_全排列 {
    private int counts_of_possible_bank_accounts=1;
    public ArrayList<String> out_text=new ArrayList<>();//最终的输出文本
    public String[] numbers=new String[]{"12","34","55","55","00","66","77","77","98"};
    private int[] db_point={1,2,3,4,5,6,7,8,9};//db校验各位置上的加权分数

    //从numbers数组中获得全部组合情况
    public void generate_and_check(){
        for(int level_0=0;level_0<this.numbers[0].length();level_0++)
            for(int level_1=0;level_1<this.numbers[1].length();level_1++)
                for(int level_2=0;level_2<this.numbers[2].length();level_2++)
                    for(int level_3=0;level_3<this.numbers[3].length();level_3++)
                        for(int level_4=0;level_4<this.numbers[4].length();level_4++)
                            for(int level_5=0;level_5<this.numbers[5].length();level_5++)
                                for(int level_6=0;level_6<this.numbers[6].length();level_6++)
                                    for(int level_7=0;level_7<this.numbers[7].length();level_7++)
                                        for(int level_8=0;level_8<this.numbers[8].length();level_8++)
                                        {
                                            String temp=""+numbers[0].charAt(level_0)+numbers[1].charAt(level_1)+numbers[2].charAt(level_2)+numbers[3].charAt(level_3)+numbers[4].charAt(level_4)+numbers[5].charAt(level_5)+
                                                    numbers[6].charAt(level_6)+numbers[7].charAt(level_7)+numbers[8].charAt(level_8);
                                            int sum=0;
                                            for(int i=0;i<9;i++){
                                                sum+=this.db_point[i]*(temp.charAt(i)-'0');
                                            }
                                            if(sum%2==0){
                                                this.out_text.add(temp);
                                            }
                                        }

        //标记一下



    }



}
