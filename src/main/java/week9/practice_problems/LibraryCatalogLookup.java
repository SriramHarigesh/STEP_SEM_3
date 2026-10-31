import java.util.Scanner;
public class LibraryCatalogLookup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] isbn = new String[n];
        String[] title = new String[n];
        for (int i = 0; i < n; i++) {
            isbn[i] = sc.next();
            title[i] = sc.next();
        }
        String target = sc.next();
        int low = 0;
        int high = n - 1;
        boolean found = false;
        while (low <= high) {
            int mid = (low + high) / 2;
            int result = isbn[mid].compareTo(target);
            if (result == 0) {
                System.out.println(title[mid]);
                found = true;
                break;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (!found) {
            System.out.println("Not Found");
        }
        sc.close();
    }
}
