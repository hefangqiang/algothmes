package main.chain.ring;

/**
 * @description: 链表   是否有环？环的长度？环点？
 * @author: Mr.He
 * @date: 2020-02-29 11:50
 **/
public class Linked {
    private Node head; // 头结点

    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(7);
        Node node3 = new Node(5);
        Node node4 = new Node(2);
        Node node5 = new Node(8);
        Node node6 = new Node(1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);
        Linked linked = new Linked();
        linked.head = node1;

        boolean isRing = isRing(linked);
        System.out.println("是否有环：" + isRing); // 打印是否有环

        int count = ringLength(linked);
        System.out.println("环的长度：" + count); // 打印环的长度

        Node ringNode = ringNode(linked);
        System.out.println("环点的值：" + (ringNode == null ? null : ringNode.val)); // 打印环点的值
    }

    // 判断链表是否有环
    /* 思想：定义p1、p2指针，两者都从链表的head节点开始遍历，p1每次移动1步，p2每次移动2步，
            因为p1和p2是差速的，所以如果链表有环，那么p1和p2一定会相遇 */
    private static boolean isRing(Linked linked){
        Node head = linked.head;
        Node p1 = head.next; // 定义指针p1，每次移动1步
        Node p2 = head.next.next; // 定义指针p2，每次移动2步

        while (p2 != null && p2.next != null) {
            if (p1 == p2) { // 相遇，说明有环
                return true;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return false;
    }

    // 求出环的长度
    /* 思想：p1和p2肯定是在环中相遇的，而p2移动步数是p1的两倍，所以两者从相遇点开始
             继续照此移动，那么下次相遇时，p2肯定比p1多移动了一圈，这一圈其实就是环的长度 */
    private static int ringLength(Linked linked){
        Node head = linked.head;
        Node p1 = head.next; // 定义指针p1，每次移动1步
        Node p2 = head.next.next; // 定义指针p2，每次移动2步
        int count = 0; //环的长度

        while (p2 != null && p2.next != null) {
            if (p1 == p2) { // 第一次相遇点
                // 相遇之后，继续让p1和p2按之前的步数走，因为p2的步数是p1的两倍，所以下次相遇时，正好是一圈
                while(true){
                    count++;
                    p1 = p1.next;
                    p2 = p2.next.next;
                    if (p1 == p2) { //再次相遇
                        return count;
                    }
                }
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return 0;
    }

    // 求环点
    /* 思想：head节点->环点的距离=相遇点->环点的距离
             所以在p1和p2相遇时，重置p1从head开始，p2从相遇点开始，两者每次都移动一步，下次相遇时，相遇点就是环点*/
    private static Node ringNode(Linked linked){
        Node head = linked.head;
        Node p1 = head.next; // 定义指针p1，每次移动1步
        Node p2 = head.next.next; // 定义指针p2，每次移动2步

        while (p2 != null && p2.next != null) {
            if (p1 == p2) { // 相遇，此处为p1和p2的相遇点
                // 重置p1从head开始，p2从相遇点开始，两者每次都移动一步，下次相遇时，相遇点就是环点
                p1 = head;
                while (true) {
                    p1 = p1.next;
                    p2 = p2.next;
                    if (p1 == p2){ // 再次相遇，此点为环点
                        return p1; // 环点
                    }
                }
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return null;
    }



    // 节点
    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
