package default_package.Pattern和Matcher;

import java.util.Arrays;
import java.util.regex.Matcher;
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


        //=========================================
        Pattern pattern_1 = Pattern.compile("<.*>");

        String input = "<h1>jeason_chan</h1>";

        Matcher matcher = pattern_1.matcher(input);
        if (matcher.matches()) {
            System.out.println(matcher.toMatchResult().start());
            System.out.println(matcher.toMatchResult().end());

        }

        Pattern pattern_2 = Pattern.compile("<.*?>");
        Matcher matcher2 = pattern_2.matcher(input);

        if (matcher2.find()) {
            System.out.println(matcher2.toMatchResult().start());
            System.out.println(matcher2.toMatchResult().end());

        }

        System.out.println(Arrays.toString(pattern_2.split(input)));

    }
}
