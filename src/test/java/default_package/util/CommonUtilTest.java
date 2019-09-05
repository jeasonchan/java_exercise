package default_package.util;

import org.junit.Test;

public class CommonUtilTest {

    @Test
    public void generateSpecificNumbers() {
        for (int i = 0; i < 100; i++) {
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
