public class MergingTwoTicketCounters {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }
    static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
    static Node build(int[] arr) {
        Node dummy = new Node(0);
        Node tail = dummy;
        for (int x : arr) {
            tail.next = new Node(x);
            tail = tail.next;
        }
        return dummy.next;
    }
    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        print(mergeSorted(build(new int[]{3, 8, 15}), build(new int[]{5, 8, 12, 20})));
        print(mergeSorted(build(new int[]{}), build(new int[]{4})));
    }
}
