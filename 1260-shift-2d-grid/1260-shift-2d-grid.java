import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Integer>> result = new ArrayList<>();

        // Shift k times
        k %= (m * n);

        for (int shift = 0; shift < k; shift++) {
            int last = grid[m - 1][n - 1];

            // Shift each row from right to left
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (j > 0) {
                        grid[i][j] = grid[i][j - 1];
                    } else if (i > 0) {
                        grid[i][j] = grid[i - 1][n - 1];
                    }
                }
            }

            grid[0][0] = last;
        }

        for (int[] row : grid) {
            List<Integer> list = new ArrayList<>();
            for (int value : row) {
                list.add(value);
            }
            result.add(list);
        }

        return result;
    }
}