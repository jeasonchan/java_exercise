package default_package.单链表快速排序;


import com.mysql.cj.jdbc.util.ResultSetUtil;

import javax.sound.midi.InvalidMidiDataException;

/*
单链表快速排序
 */
public class Solution {
    public Node quickSort(Node node) {
        quickSortImpl(node, null);

        return node;
    }


    public void quickSortImpl(Node startNode, Node endNode) {
        if (startNode == endNode || startNode.next == endNode) {
            //显然，指向同一个节点，或者，节点之间没有其他节点时，就不要对这个区间上的节点进行排序
            return;
        }

        //正常排序
        Node midNode = split(startNode, endNode);
        quickSortImpl(startNode, midNode);
        quickSortImpl(midNode.next, endNode);
    }


    //将链表分为两部分，前半部分比基准小，后半部分比基准大
    //并返回分界点的引用
    private Node split(Node startNode, Node endNode) {
        int pivotValue = startNode.value;

        Node mid = startNode;
        Node move = startNode;

        while (endNode != move) {
            if (move.value < pivotValue) {
                swapNodeValue(move, mid);
                mid = mid.next;
            }


            move = move.next;
        }


        return mid;
    }

    private void swapNodeValue(Node node1, Node node2) {
        int temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }


}

class Node {
    public int value;
    public Node next = null;
}
