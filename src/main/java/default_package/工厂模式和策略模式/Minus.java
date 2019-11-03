package default_package.工厂模式和策略模式;

public class Minus implements Calculate {
    @Override
    public String geResult(int number1, int number2) {
        return String.valueOf(number1 - number2);
    }
}
