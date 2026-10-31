import java.util.Scanner;
public class MallFootfallRangeReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] visitors = new long[n];
        long[] prefix = new long[n];
        for (int i = 0; i < n; i++) {
            visitors[i] = sc.nextLong();
            if (i == 0) {
                prefix[i] = visitors[i];
            } else {
                prefix[i] = prefix[i - 1] + visitors[i];
            }
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            long total;
            if (start == 0) {
                total = prefix[end];
            } else {
                total = prefix[end] - prefix[start - 1];
            }
            System.out.println(total);
        }
        sc.close();
    }
}
