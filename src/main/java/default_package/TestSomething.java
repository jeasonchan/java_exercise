package default_package;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@Setter
public class TestSomething {
    public String message = "";
    public String[] messageString = new String[12];

    public TestSomething() {
    }

    /**
     * 使用流技术实现对象的深拷贝
     *
     * @return
     */
    public TestSomething deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this); //最终还是写进了字节数组输出流中

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (TestSomething) objectInputStream.readObject();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getMessage().equals(((TestSomething) obj).getMessage());
    }

    public static void main(String[] arg) {
        TestSomething testSomething1 = new TestSomething();
        TestSomething testSomething2 = new TestSomething();
        Map<String, TestSomething> map = new HashMap<>();

        map.put("1", testSomething1);
        testSomething1.message = "23333";
        System.out.println(map.get("1").message);
         /*
         输出结果，2333333
         再次证明，java里面只有基础数据类型是值，数据容器（数组、列表、类什么的）的名称全是引用/指针，无论还多少名字，
         只要是指针，更改和指向的内存区域都是一样的，因此，只要map放的是数据容器的指针/引用，先放进map后更新和先更新后
         放进map，完全没有影响！！！！
         另一个强有力的用处就是，方法传递数据容器的指针时，是能够改变父级调用里面的对象的值的，除非自己深拷贝一份。
          */


        Queue<TestSomething> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 20; i++) {
            TestSomething testSomething = new TestSomething();
            testSomething.setMessage(String.valueOf(i));
            queue.offer(testSomething);
        }
        System.out.println(queue.size());

        testSomething1.setMessage("18");
        queue.remove(testSomething1);
        System.out.println(queue.size());

        Iterator<TestSomething> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(queue.size());  //应该输出打印0，使用被遍历的对象的remove方法会使迭代器停止迭代，
        // 只有使用迭代器对象的remove才能已出迭代器指向的当前对象，且不会停止继续迭代
        // 达到了删除元素时，不破坏遍历过程


    }


}
