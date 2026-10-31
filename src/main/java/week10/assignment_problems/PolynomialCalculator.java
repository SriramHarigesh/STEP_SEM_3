public class PolynomialCalculator {
    static class Node {
        int coef, exp;
        Node next;
        Node(int coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }
    }
    static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;
        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coef, p.exp);
                tail = tail.next;
                p = p.next;
            } else if (q.exp > p.exp) {
                tail.next = new Node(q.coef, q.exp);
                tail = tail.next;
                q = q.next;
            } else {
                int sum = p.coef + q.coef;
                if (sum != 0) {
                    tail.next = new Node(sum, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }
        while (p != null) {
            tail.next = new Node(p.coef, p.exp);
            tail = tail.next;
            p = p.next;
        }
        while (q != null) {
            tail.next = new Node(q.coef, q.exp);
            tail = tail.next;
            q = q.next;
        }
        return dummy.next;
    }
    static Node build(int[][] terms) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;
        for (int[] t : terms) {
            tail.next = new Node(t[0], t[1]);
            tail = tail.next;
        }
        return dummy.next;
    }
    static String toStr(Node head) {
        if (head == null) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Node t = head; t != null; t = t.next) {
            if (first) {
                if (t.coef < 0) {
                    sb.append("-");
                }
            } else {
                sb.append(t.coef < 0 ? " - " : " + ");
            }
            first = false;
            int a = Math.abs(t.coef);
            if (t.exp == 0) {
                sb.append(a);
            } else {
                if (a != 1) {
                    sb.append(a);
                }
                sb.append("x");
                if (t.exp > 1) {
                    sb.append("^").append(t.exp);
                }
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Node p1 = build(new int[][]{{5, 3}, {4, 2}, {2, 0}});
        Node q1 = build(new int[][]{{5, 2}, {-3, 1}, {1, 0}});
        System.out.println(toStr(addPolynomials(p1, q1)));
        Node p2 = build(new int[][]{{3, 2}, {1, 0}});
        Node q2 = build(new int[][]{{-3, 2}, {4, 1}});
        System.out.println(toStr(addPolynomials(p2, q2)));
    }
}
