import java.util.Scanner;
public class ExamScoreBandCounter {
    static int lowerBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (scores[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    static int upperBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (scores[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }
        int low = sc.nextInt();
        int high = sc.nextInt();
        int first = lowerBound(scores, low);
        int last = upperBound(scores, high);
        System.out.println(last - first);
        sc.close();
    }
}
