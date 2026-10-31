public class CancellingTheKthLastOrder {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }
    static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node lead = dummy;
        Node trail = dummy;
        for (int i = 0; i < k + 1; i++) {
            lead = lead.next;
        }
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        trail.next = trail.next.next;
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
        int[] orders = {10, 20, 30, 40, 50};
        print(removeKthFromEnd(build(orders), 2));
        print(removeKthFromEnd(build(orders), 5));
    }
}
