import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class NetBalancePeriodCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] transactions = new int[n];
        for (int i = 0; i < n; i++) {
            transactions[i] = sc.nextInt();
        }
        long k = sc.nextLong();
        Map<Long, Long> count = new HashMap<>();
        count.put(0L, 1L);
        long sum = 0;
        long answer = 0;
        for (int i = 0; i < n; i++) {
            sum += transactions[i];
            if (count.containsKey(sum - k)) {
                answer += count.get(sum - k);
            }
            count.put(sum, count.getOrDefault(sum, 0L) + 1);
        }
        System.out.println(answer);
        sc.close();
    }
}
