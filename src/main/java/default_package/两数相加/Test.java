package default_package.两数相加;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getListNode(123456));

        ListNode startNode = new ListNode(0);

        ListNode node1 = new ListNode(1);
        startNode.next = node1;

        ListNode node2 = new ListNode(2);
        node1.next = node2;

        ListNode node3 = new ListNode(3);
        node2.next = node3;

        node3.next = null;

        System.out.println(solution.getInt(startNode));


        System.out.println(solution.addTwoNumbers(startNode, startNode));

    }
}
