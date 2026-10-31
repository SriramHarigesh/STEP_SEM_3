import java.util.Scanner;
public class LongestBudgetFriendlyStreak {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] costs = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }
        long budget = sc.nextLong();
        long sum = 0;
        int left = 0;
        int maxLength = 0;
        int startIndex = -1;
        for (int right = 0; right < n; right++) {
            sum += costs[right];
            while (sum > budget && left <= right) {
                sum -= costs[left];
                left++;
            }
            int length = right - left + 1;
            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }
        System.out.println("(" + maxLength + ", " + startIndex + ")");
        sc.close();
    }
}
