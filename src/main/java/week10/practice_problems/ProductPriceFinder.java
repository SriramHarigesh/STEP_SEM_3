public class ProductPriceFinder {
    static int lowerBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    static int upperBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int lo = lowerBound(prices, 150);
        int hi = upperBound(prices, 300);
        System.out.println("Lower bound index " + lo);
        System.out.println("Upper bound index " + hi);
        StringBuilder sb = new StringBuilder();
        for (int i = lo; i < hi; i++) {
            sb.append(prices[i]);
            if (i < hi - 1) {
                sb.append(", ");
            }
        }
        System.out.println("Count " + (hi - lo) + " (products with prices " + sb + ")");
    }
}
