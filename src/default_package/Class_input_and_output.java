package default_package;

import java.util.ArrayList;

class SingleNumber {//假装有这个类
    public void identify_single_number(int[][] array){
        return;
    }
}
//NumberChar2Int将一个长度为27的string字符串转化为int二维数组 int[][]

public class Class_input_and_output {
    //
    String input_path;
    String output_path;
    ArrayList<String> origin_stream=new ArrayList<>();//最原始的，包含上下的分界线




    //
    void set_path(){
        java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.print("输入文件全路径（包含扩展名）：");
        try{
            input_path= stdin.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }
        output_path=input_path.substring(0,input_path.length()-4)+"识别结果.txt";
    }

    //将字符串传递给NumberChar2Int,这个好像是将3*4*9个字符串转为二维int数组

    //将源文件读取到Arraylist 类型的 origin_stream中
    void read_text_to_stream(){
        try {
            java.io.FileReader fr = new java.io.FileReader(input_path);
            java.io.BufferedReader bf = new java.io.BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                origin_stream.add(str);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    void write_stream_to_text(){
        int number_of_bank_account=(this.origin_stream.size()-2)/4;  //除去头尾两个，剩下的每4行一个银行账户，得到理想状态下的账户个数
        int number_of_lines=number_of_bank_account+2;
        try{
            java.io.FileWriter fw = new java.io.FileWriter(output_path);
            for(int i=0;i<number_of_lines;i++){//以每一行为单位，逐行处理并写入到文本中
                if(i==0||i==number_of_lines-1){
                    fw.write("***\r\n");//第一行和最后一行分割线
                }else{

                    //
                    Class_sheng_cheng_全排列 instance_of_Class_sheng_cheng_全排列=new Class_sheng_cheng_全排列();//银行账户类
                    for(int j=0;j<9;j++){
                        instance_of_Class_sheng_cheng_全排列.add(xxxxxxxx);//循环9次，加入SingleNumber实例
                    }
                    if(instance_of_Class_sheng_cheng_全排列.out_text.size()==1){
                        fw.write(instance_of_Class_sheng_cheng_全排列.out_text.toString()+"\r\n");//只有一个结果，直接打印即可
                    }else{
                        //具有多种结果时
                        String write_string=instance_of_Class_sheng_cheng_全排列.out_text.get(0)+" AMB "+instance_of_Class_sheng_cheng_全排列.out_text.set(1,"")+"\r\n";//将也已经打印过得元素设为空，再将剩下的全部打印
                        fw.write(write_string);
                    }


                }

            }
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
