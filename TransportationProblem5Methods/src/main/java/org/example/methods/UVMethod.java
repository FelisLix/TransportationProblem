package org.example.methods;

import java.util.*;

public class UVMethod {

    private final int[][] grid;
    private final int[][] plan;
    private final int[][] C;
    private final int[] V;
    private final int[] U;
    int ans;


    public UVMethod(int plan[][]) {
        grid = new int[][]{
                {5, 4, 6, 5, 0},
                {4, 2, 2, 4, 0},
                {3, 5, 6, 7, 0},
                {5, 6, 2, 5, 0}
        };
        this.plan = plan;
        C = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        ans = 0;
        this.U = new int[grid.length];
        this.V = new int[grid[0].length];
    }

    private void calculateUV() {
        setUV();
        for (int i = 0; i < plan.length; i++) {
            for (int j = 0; j < plan[i].length; j++) {
                if (plan[i][j] != 0) {
                    if (U[i] != Integer.MAX_VALUE) {
                        V[j] = grid[i][j] - U[i];
                    } else if (V[j] != Integer.MAX_VALUE) {
                        U[i] = grid[i][j] - V[j];
                    } else if ((U[i] == Integer.MAX_VALUE) && (V[j] == Integer.MAX_VALUE)) {
                        V[j] = grid[i][j];
                        U[i] = 0;
                    }
                }
            }
        }
    }


    public boolean isOptimal() {
        calculateUV();
        setCMatrix();
        for (int[] ints : C) {
            for (int anInt : ints) {
                if (anInt > 0) return false;
            }
        }
        return true;
    }

    private void setCMatrix() {
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                C[i][j] = (V[j] + U[i]) - grid[i][j];
            }
        }
    }

    private void setUV() {
        Arrays.fill(U, Integer.MAX_VALUE);
        Arrays.fill(V, Integer.MAX_VALUE);
        U[0] = 0;
    }

}
