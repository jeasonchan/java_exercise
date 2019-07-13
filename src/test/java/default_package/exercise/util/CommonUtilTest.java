package default_package.exercise.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test
    public void generateSpecificNumbers() {
        for (int i = 0; i < 5; i++) {
            System.out.println(CommonUtil.generateSpecificNumbers(5));
        }
    }

    @Test
    public void generateSpecificString() {
        for (int i = 0; i < 5; i++) {
            System.out.println(CommonUtil.generateSpecificString(5));
        }
    }
}
