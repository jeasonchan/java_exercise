package default_package.单链表快速排序;

import com.mysql.cj.xdevapi.SelectStatement;

public class Test {
    public static void main(String[] args) {
        Node previous = null;
        Node startNode = null;

        for (int i = 10; i > 0; i--) {
            Node current = new Node();
            current.value = i;

            if (10 == i) {
                startNode = current;
            }

            if (10 != i) {

                previous.next = current;
            }

            previous = current;

            if (1 == i) {
                current.next = null;
            }
        }


        printNode(startNode);


        System.out.println("=======================");


        printNode(new Solution().quickSort(startNode));


    }


    public static void printNode(Node node) {
        while (null != node) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
