import java.util.Scanner;
public class MaximumSumSubarrayFixedSize {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sales = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }
        long maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - sales[i - k] + sales[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        System.out.println(maxSum);
        sc.close();
    }
}
