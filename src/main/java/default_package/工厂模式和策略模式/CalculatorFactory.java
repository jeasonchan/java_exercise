package default_package.工厂模式和策略模式;

public class CalculatorFactory {
    public static Calculate getCalculator(String opType) {
        switch (opType) {
            case "+":
                return new Add();

            case "-":
                return new Minus();

            case "*":
                return new Multiple();

            default:
                return null;

        }
    }
}
