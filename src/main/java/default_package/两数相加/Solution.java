package default_package.两数相加;


/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        System.out.println("l1:" + getInt(l1));
        System.out.println("l2:" + getInt(l2));

//        return getListNode(getInt(l1) + getInt(l2));
        return dealNodeOneByOne(l1, l2);
    }


    public int getInt(ListNode listNode) {
        int exp = 0;
        int result = 0;
        while (null != listNode) {
            result += Math.pow(10, exp) * listNode.val;

            ++exp;
            listNode = listNode.next;
        }


        return result;
    }


    public ListNode getListNode(long number) {
        ListNode previousNode = null;
        ListNode startNode = null;

        if (0 == number) {
            startNode = new ListNode(0);
            startNode.next = null;
        }


        while (number > 0) {
            long temp = number / 10;
            int value = (int) (number - temp * 10);
            number = temp;

//            System.out.println(value);

            if (null == startNode) {
                startNode = new ListNode(value);
                previousNode = startNode;
            } else {
                ListNode currentNode = new ListNode(value);
                currentNode.next = null;
                previousNode.next = currentNode;
                previousNode = currentNode;
            }

        }

        return startNode;
    }

    //=======================================================
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


        System.out.println(solution.addTwoNumbers(startNode,startNode));

    }


    //=====================================================================
    public static ListNode dealNodeOneByOne(ListNode l1, ListNode l2) {
        ListNode startNode = null;
        ListNode previousNode = null;
        int carry = 0;

        while (null != l1 || null != l2) {


            if (null != l1 && null != l2) {
                //两个没没到头的情况

                int temp = l1.val + l2.val + carry;

                if (temp >= 10) {
                    carry = temp / 10;
                    temp = temp - 10 * carry;
                }

                ListNode currentNode = new ListNode(temp);
                currentNode.next = null;

                if (null == startNode) {
                    startNode = currentNode;
                } else {
                    previousNode.next = currentNode;
                }

                previousNode = currentNode;

                l1 = l1.next;
                l2 = l2.next;


            } else if (null != l1 && null == l2) {
                //l2到头的情况
                int temp = l1.val + carry;

                if (temp >= 10) {
                    carry = temp / 10;
                    temp = temp - 10 * carry;
                }

                ListNode currentNode = new ListNode(temp);
                currentNode.next = null;

                if (null == startNode) {
                    startNode = currentNode;
                } else {
                    previousNode.next = currentNode;
                }

                previousNode = currentNode;

                l1 = l1.next;

            } else if (null == l1 && null != l2) {
                //l1到头的情况

                int temp = l2.val + carry;

                if (temp >= 10) {
                    carry = temp / 10;
                    temp = temp - 10 * carry;
                }

                ListNode currentNode = new ListNode(temp);
                currentNode.next = null;

                if (null == startNode) {
                    startNode = currentNode;
                } else {
                    previousNode.next = currentNode;
                }

                previousNode = currentNode;

                l2 = l2.next;

            }


        }


        if (carry > 0) {
            ListNode currentNode = new ListNode(carry);
            currentNode.next = null;
            previousNode.next = currentNode;
        }


        return startNode;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
