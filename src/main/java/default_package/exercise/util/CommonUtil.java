package default_package.exercise.util;

import java.util.Random;

public class CommonUtil {

    /*
    生成相应位数的随机整数
     */
    public static int generateSpecificNumbers(int expNumber) {
        Random random = new Random();
        return random.nextInt((int) Math.pow(10, expNumber + 1)-(int) Math.pow(10, expNumber))
                + (int) Math.pow(10, expNumber);
    }

    /*
    生成相应长度的字符串
     */
    public static String generateSpecificString(int number){
        Random random=new Random();
        StringBuilder result=new StringBuilder();

        for(int i=0;i<number;i++){
            int intChar=random.nextInt((int)'z'-(int)'a'+1)+(int)'a';
            result.append((char)intChar);
        }

        return result.toString();
    }

}
