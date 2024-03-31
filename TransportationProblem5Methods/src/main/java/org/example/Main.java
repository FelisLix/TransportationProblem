package org.example;


import org.example.methods.*;

public class Main {
    public static void main(String[] args) {

        //North-West Corner Method result
        int[][] plan1 = new int[][]{
                {50, 0, 0, 0, 0},
                {20, 5, 0, 0, 0},
                {0, 20, 25, 0, 0},
                {0, 0, 0, 20, 10}
        };
        //Least Cost Cell Method result
        int[][] plan2 = new int[][]{
                {25, 0, 0, 20, 5},
                {-1, 25, 0, 0, 0},
                {45, 0, 0, 0, 0},
                {0, 0, 25, 0, 5}
        };
        UVMethod check1 = new UVMethod(plan1);
        UVMethod check2 = new UVMethod(plan2);

        System.out.println("------------------------------------------------------------------");
        System.out.println("""
                Умова транспортної задачі:\s
                Тарифи:
                5 4 6 5 0
                4 2 2 4 0
                3 5 6 7 0
                5 6 2 5 0
                Запаси: 50 25 45 30
                Потреба: 70 25 25 20 10
                """);

        System.out.println("------------------------------------------------------------------");
        NorthWestCornerMethod method1 = new NorthWestCornerMethod();
        System.out.println("Вартість перевезень використовуючи метод Північно-Західного Кута: ");
        method1.showResults();

        System.out.println("------------------------------------------------------------------");

        LeastCostCellMethod method2 = new LeastCostCellMethod();
        System.out.println("Вартість перевезень використовуючи метод Мінімального Елемента: ");
        method2.showResults();

        System.out.println("------------------------------------------------------------------");

        DualPreferenceMethod method3 = new DualPreferenceMethod();
        System.out.println("Вартість перевезень використовуючи метод Подвійної Переваги: ");
        method3.showResults();

        System.out.println("------------------------------------------------------------------");

        VogelsApproximationMethod method4 = new VogelsApproximationMethod();
        System.out.println("Вартість перевезень використовуючи метод Фогеля: ");
        method4.showResults();

        System.out.println("------------------------------------------------------------------");

        System.out.println("Перевірка плану на оптимальність за допомогою методу потенціалів.");

        System.out.println("План 1:");
        System.out.println("""
                50 0  0  0  0
                20 5  0  0  0
                0  20 25 0  0
                0  0  0  20 10
                """);
        if (check1.isOptimal()) {
            System.out.println("План оптимальний");
        } else {
            System.out.println("План не оптимальний та потребує покращення");
        }
        System.out.println();
        System.out.println("План 2:");
        System.out.println("""
                25 0  0  20 5
                -1 25 0  0  0
                45 0  0  0  0
                0  0  25 0  5
                 """);
        if (check2.isOptimal()) {
            System.out.println("План оптимальний");
        } else {
            System.out.println("План не оптимальний та потребує покращення");
        }
        System.out.println("------------------------------------------------------------------");
    }
}
