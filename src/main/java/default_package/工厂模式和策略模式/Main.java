package default_package.工厂模式和策略模式;


import default_package.CommonUtil;

/**
 * 分别使用最传统的方式，简单工厂模式和策略模式，实现加减乘除情况处理。
 */
public class Main {
    public static void main(String[] args) {
        //需求，假设这里是动态变化的
        int number1 = 1;
        int number2 = 2;
        String operationSymbol = "+";


        //工厂模式示范
        Calculate addOptype = CommonUtil.checkNullAndReturn(CalculatorFactory.getCalculator(operationSymbol), Main.class);
        String result = addOptype.geResult(number1, number2);
        System.out.println(number1 + operationSymbol + number2 + "=" + result);

        //当需要增加新的计算符号时，需要对代码做三件事：
        //1、增加一个实现了“Calculate”接口的类，与工厂的输出类型保持一致
        //2、在工厂中增加相应的响应代码，返回那个新增的类的实例
        //3、不需要修改调用处的源码，


        //=============================================================
        //策略模式
        Context context = new Context(new Add());//应用了计算策略
        result = context.getResult(number1, number2);
        System.out.println(number1 + operationSymbol + number2 + "=" + result);

        //当需要增加新的计算符号时，需要对代码做两件事：
        //1、增加一个实现了“Calculate”接口的类
        //2、手动在调用处实现一个“应用计算策略”的情况


        //对比一下，对于单一的情况的，使用起来便利性没啥区别；
        //对于，情况多变的场景，工厂模式不需要在调用出改变、新增代码，而策略模式需要将分情况使用不同策略 这一过程展示出来，

    }
}
