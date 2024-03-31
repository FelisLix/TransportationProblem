package org.example.methods;

import java.util.Arrays;

public class NorthWestCornerMethod {

    private final int[][] grid;
    private final int[] supply;
    private final int[] demand;
    private int[][] plan;
    int startR = 0;
    int startC = 0;
    int ans;

    public NorthWestCornerMethod() {
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
        while (startR != grid.length
                && startC != grid[0].length) {

            if (supply[startR] <= demand[startC]) {
                ans += supply[startR] * grid[startR][startC];
                plan[startR][startC] = supply[startR];
                demand[startC] -= supply[startR];
                startR++;
            } else {
                ans += demand[startC] * grid[startR][startC];
                plan[startR][startC] = demand[startC];
                supply[startR] -= demand[startC];
                startC++;
            }
        }
    }

    public void showResults() {
        calculate();
        System.out.println(ans);
        System.out.println(Arrays.deepToString(plan));
    }
}

