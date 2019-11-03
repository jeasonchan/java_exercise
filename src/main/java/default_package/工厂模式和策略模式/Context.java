package default_package.工厂模式和策略模式;

import java.util.PrimitiveIterator;

public class Context {
    private Calculate calculate;

    public Context(Calculate calculate) {
        this.calculate = calculate;
    }


    public String getResult(int number1, int number2) {
        return calculate.geResult(number1, number2);
    }
}
