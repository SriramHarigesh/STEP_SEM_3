public class CampusPortalBrowserHistory {
    static class Node {
        String data;
        Node prev, next;
        Node(String data) {
            this.data = data;
        }
    }
    static class BrowserHistory {
        Node current;
        BrowserHistory(String home) {
            current = new Node(home);
        }
        void visit(String url) {
            Node node = new Node(url);
            node.prev = current;
            current.next = node;
            current = node;
        }
        String back(int steps) {
            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }
            return current.data;
        }
        String forward(int steps) {
            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }
            return current.data;
        }
    }
    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("srm.edu");
        bh.visit("a.com");
        bh.visit("b.com");
        bh.visit("c.com");
        StringBuilder sb = new StringBuilder();
        sb.append(bh.back(1)).append(", ");
        sb.append(bh.back(1)).append(", ");
        sb.append(bh.forward(1)).append(", ");
        bh.visit("d.com");
        sb.append(bh.forward(2)).append(", ");
        sb.append(bh.back(2)).append(", ");
        sb.append(bh.back(7));
        System.out.println(sb);
    }
}
