package org.example.methods;

import java.util.Arrays;

public class VogelsApproximationMethod {
    static int INF = 1000;
    private final int[][] plan;
    private final int[][] grid;
    private final int[] supply;
    private final int[] demand;
    private int ans;

    public VogelsApproximationMethod() {
        this.grid = new int[][]{
                {5, 4, 6, 5, 0},
                {4, 2, 2, 4, 0},
                {3, 5, 6, 7, 0},
                {5, 6, 2, 5, 0}
        };
        this.plan = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        this.supply = new int[]{50, 25, 45, 30};
        this.demand = new int[]{70, 25, 25, 20, 10};
        ans = 0;
    }

    private void calculate() {
        int n = grid.length;
        int m = grid[0].length;

        while (!isEmpty(supply) && !isEmpty(demand)) {
            int[] rowDiff = new int[n];
            int[] colDiff = new int[m];


            findDiff(grid, rowDiff, colDiff);

            int maxi1 = Arrays.stream(rowDiff).max().getAsInt();
            int maxi2 = Arrays.stream(colDiff).max().getAsInt();


            if (maxi1 >= maxi2) {
                for (int i = 0; i < n; i++) {
                    if (rowDiff[i] == maxi1) {
                        int mini1 = Arrays.stream(grid[i]).min().getAsInt();
                        for (int j = 0; j < m; j++) {
                            if (grid[i][j] == mini1) {
                                int mini2 = Math.min(supply[i], demand[j]);
                                ans += mini2 * mini1;
                                plan[i][j] = mini2;
                                supply[i] -= mini2;
                                demand[j] -= mini2;
                                if (demand[j] == 0) {
                                    for (int r = 0; r < n; r++) {
                                        grid[r][j] = INF;
                                    }
                                } else {
                                    Arrays.fill(grid[i], INF);
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            } else {

                for (int j = 0; j < m; j++) {
                    if (colDiff[j] == maxi2) {
                        int mini1 = INF;
                        for (int[] ints : grid) {
                            mini1 = Math.min(mini1, ints[j]);
                        }

                        for (int i = 0; i < n; i++) {
                            int val2 = grid[i][j];
                            if (val2 == mini1) {
                                int mini2 = Math.min(supply[i], demand[j]);
                                ans += mini2 * mini1;
                                plan[i][j] = mini2;
                                supply[i] -= mini2;
                                demand[j] -= mini2;
                                if (demand[j] == 0) {
                                    for (int r = 0; r < n; r++) {
                                        grid[r][j] = INF;
                                    }
                                } else {
                                    Arrays.fill(grid[i], INF);
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public static boolean isEmpty(int[] array) {
        for (int value : array) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    public static void findDiff(int[][] grid, int[] rowDiff, int[] colDiff) {
        for (int i = 0; i < grid.length; i++) {
            int[] arr = Arrays.copyOf(grid[i], grid[i].length);
            Arrays.sort(arr);
            rowDiff[i] = arr[1] - arr[0];
        }

        for (int col = 0; col < grid[0].length; col++) {
            int[] arr = new int[grid.length];
            for (int i = 0; i < grid.length; i++) {
                arr[i] = grid[i][col];
            }
            Arrays.sort(arr);
            colDiff[col] = arr[1] - arr[0];
        }
    }

    public void showResults() {
        calculate();
        System.out.println(ans);
        System.out.println(Arrays.deepToString(plan));
    }

}
