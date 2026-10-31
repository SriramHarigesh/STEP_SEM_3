import java.util.Scanner;
public class WarehouseBinGridScan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] grid = new int[rows][cols];
        int total = 0;
        int max = -1;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
                total += grid[i][j];
                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }
        System.out.println("Total = " + total);
        System.out.println("Maximum = " + max);
        System.out.println("Maximum Coordinate = (" + maxRow + ", " + maxCol + ")");
        sc.close();
    }
}
