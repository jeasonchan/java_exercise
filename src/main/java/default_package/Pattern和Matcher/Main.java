package default_package.Pattern和Matcher;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Pattern.matches(
                        "[1-9][0-9]{0,}",
                        "84491789"
                )
        );


        System.out.println(
                Pattern.matches(
                        "[0-9]{1,2}",
                        "98"
                )
        );

        System.out.println(
                Pattern.matches(
                        "[1-9][0-9]?",
                        "10"
                )
        );
        //或者
        System.out.println(
                Pattern.matches(
                        "[1-9][0-9]{0,1}",
                        "10"
                )
        );

    }
}
