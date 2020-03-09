package main.chain.lastnelement;


/**
 * @description: 查找链表倒数第n个数
 *              思路：使用双指针p1,p2，p1从head开始，p2从head后的第n-1个节点开始。(也就是p2和p1保持n-1的间距)
 *                   之后p2每次移动n-1步(最后一次移动可能会小于n-1步)，p1和p2移动的步数相同,并且p2先移动，然后p1再移动，如此循环。
 *                   直到p2的下个节点为null时，说明p2移动到了链表尾端，此时p1再移动和p2相同的步数，最后p1节点就是倒数第n个数。
 *              时间复杂度：O(n)
 *              空间复杂度：O(1)
 * @author: Mr.He
 * @date: 2020-03-09 16:21
 **/
public class LastN {

    public static void main(String[] args) throws Exception {
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(10);
        Node node4 = new Node(17);
        Node node5 = new Node(12);
        Node node6 = new Node(4);
        Node node7 = new Node(1);
        Node node8 = new Node(5);
        Node node9 = new Node(8);
        Node node10 = new Node(7);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(node4);
        node4.setNextNode(node5);
        node5.setNextNode(node6);
        node6.setNextNode(node7);
        node7.setNextNode(node8);
        node8.setNextNode(node9);
        node9.setNextNode(node10);

        int result = getLastNode(node1,4);
        System.out.println(result);
    }

    private static int getLastNode(Node head,int n) throws Exception {
        // 定义p1从head节点开始
        Node p1 = head;
        Node p2 = head;
        // p2从head后的第n-1个节点开始
        int step = n - 1; // p2要移动的步数
        int count = 0; // p1要移动的步数
        while (step != 0 && p2 != null) {
            p2 = p2.nextNode;
            step--;
            count++; // 正常情况下count和step的最大值都是n，但最后一次移动可能会小于n
        }
        // 如果要查询的倒数第n个数大于链表的长度，则报错
        if(step != 0){
            throw new Exception("链表长度不够");
        }

        while (true) {
            // 每次都重置step和count
            step = n - 1;
            count = 0;
            while (step != 0 && p2.nextNode != null) {
                p2 = p2.nextNode;
                step--;
                count++;
            }
            while (count != 0) {
                p1 = p1.nextNode;
                count--;
            }
            if (p2.nextNode == null) {
                break;
            }
        }
        return p1.val;
    }

    static class Node {
        int val;
        Node nextNode;

        public Node(int val) {
            this.val = val;
        }

        public void setNextNode(Node node){
            this.nextNode = node;
        }
    }
}
