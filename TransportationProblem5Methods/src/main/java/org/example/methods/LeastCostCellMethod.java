package org.example.methods;

import java.util.Arrays;

public class LeastCostCellMethod {

    private final int[][] grid;
    private final int[][] plan;
    private final int[] supply;
    private final int[] demand;
    int ans;

    public LeastCostCellMethod() {
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
        while (checkIfAllZero(grid)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int minValue = findMinInMatrix(grid);
                    if (grid[i][j] == minValue) {
                        if (supply[i] <= demand[j]) {
                            ans += supply[i] * minValue;
                            plan[i][j] = supply[i];
                            demand[j] -= supply[i];
                            supply[i] = 0;
                        } else {
                            ans += demand[j] * minValue;
                            plan[i][j] = demand[j];
                            supply[i] -= demand[j];
                            demand[j] = 0;

                        }
                    }
                    if (supply[i] == 0) makeAllZeroInRow(i);
                    if (demand[j] == 0) makeAllZeroInColumn(j);
                }
            }
        }
    }

    private int findMinInMatrix(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > 0 && anInt < min) {
                    min = anInt;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private boolean checkIfAllZero(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt != 0) return true;
            }

        }
        return false;
    }

    private void makeAllZeroInRow(int n) {
        Arrays.fill(grid[n], 0);
    }

    private void makeAllZeroInColumn(int n) {
        for (int i = 0; i < grid.length; i++) {
            grid[i][n] = 0;
        }
    }

    public void showResults() {
        calculate();
        System.out.println(ans);
        System.out.println(Arrays.deepToString(plan));
    }
}
