package org.example.methods;

import java.util.Arrays;

public class DualPreferenceMethod {
    private final int[][] grid;
    private final int[][] plan;
    private final int[][] gridOneFlag;
    private final int[][] gridTwoFlags;
    private final int[] supply;
    private final int[] demand;
    int ans;


    public DualPreferenceMethod() {
        this.grid = new int[][]{
                {5, 4, 6, 5, 0},
                {4, 2, 2, 4, 0},
                {3, 5, 6, 7, 0},
                {5, 6, 2, 5, 0}
        };
        this.gridOneFlag = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        this.gridTwoFlags = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
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
        setFlags();
        //All double marked elements
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (gridTwoFlags[i][j] != 0) {
                    if (supply[i] <= demand[j]) {
                        ans += supply[i] * gridTwoFlags[i][j];
                        plan[i][j] = supply[i];
                        demand[j] -= supply[i];
                        supply[i] = 0;
                        gridOneFlag[i][j] = 0;
                    } else {
                        ans += demand[j] * gridTwoFlags[i][j];
                        plan[i][j] = demand[j];
                        supply[i] -= demand[j];
                        demand[j] = 0;
                        gridOneFlag[i][j] = 0;
                    }
                    if (supply[i] == 0) makeAllZeroInRow(i);
                    if (demand[j] == 0) makeAllZeroInColumn(j);
                }
            }
        }

        //All once marked elements
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (gridOneFlag[i][j] != 0) {
                    if (supply[i] <= demand[j]) {
                        ans += supply[i] * gridTwoFlags[i][j];
                        plan[i][j] = supply[i];
                        demand[j] -= supply[i];
                        supply[i] = 0;
                        gridOneFlag[i][j] = 0;
                    } else {
                        ans += demand[j] * gridTwoFlags[i][j];
                        plan[i][j] = demand[j];
                        supply[i] -= demand[j];
                        demand[j] = 0;
                        gridOneFlag[i][j] = 0;
                    }
                    if (supply[i] == 0) makeAllZeroInRow(i);
                    if (demand[j] == 0) makeAllZeroInColumn(j);
                }
            }
        }

        //Elements left
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

    private void setFlags() {
        //set mim in rows
        int[] minInRow = findMinInRows(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == minInRow[i]) {
                    gridOneFlag[i][j] = minInRow[i];
                    break;
                }
            }

        }

        //set min in columns
        int[] minInColumn = findMinInColumns(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ((grid[i][j] == minInColumn[j]) && (gridOneFlag[i][j] == minInColumn[j])) {
                    gridTwoFlags[i][j] = minInColumn[j];
                } else if (grid[i][j] == minInColumn[j]) {
                    gridOneFlag[i][j] = minInColumn[j];
                }
            }
        }
    }

    public static int[] findMinInRows(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] minValues = new int[rows];

        Arrays.fill(minValues, Integer.MAX_VALUE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > 0 && matrix[i][j] < minValues[i]) {
                    minValues[i] = matrix[i][j];
                }
            }
        }

        return minValues;
    }

    public static int[] findMinInColumns(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] minValues = new int[cols];

        Arrays.fill(minValues, Integer.MAX_VALUE);

        for (int[] ints : matrix) {
            for (int j = 0; j < cols; j++) {
                if (ints[j] > 0 && ints[j] < minValues[j]) {
                    minValues[j] = ints[j];
                }
            }
        }

        return minValues;
    }

    private void makeAllZeroInRow(int n) {
        Arrays.fill(grid[n], 0);
        Arrays.fill(gridOneFlag[n], 0);
    }

    private void makeAllZeroInColumn(int n) {
        for (int i = 0; i < grid.length; i++) {
            grid[i][n] = 0;
        }
        for (int i = 0; i < grid.length; i++) {
            gridOneFlag[i][n] = 0;
        }
    }

    public void showResults() {
        calculate();
        System.out.println(ans);
        System.out.println(Arrays.deepToString(plan));
    }

}
