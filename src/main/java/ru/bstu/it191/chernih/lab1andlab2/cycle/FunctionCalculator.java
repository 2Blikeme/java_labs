package ru.bstu.it191.chernih.lab1andlab2.cycle;

import java.util.function.Function;

public class FunctionCalculator {

    /**
     * Выводит значения функции в консоль на отрезке [a, b]
     * @param func      вычисляемая функция
     * @param a         начало отрезка
     * @param b         конец отрезка
     * @param n         шаг
     */
    public static void printFunctionTableWithWhileCycle(Function<Double, Double> func,
                                                        double a, double b, double n) {
        var x = a;
        while (b <= x) {
            System.out.printf("F(%s) = %s%n", x, func.apply(x));
            x += n;
        }
    }

    /**
     * Выводит значения функции в консоль на отрезке [a, b]
     * @param func      вычисляемая функция
     * @param a         начало отрезка
     * @param b         конец отрезка
     * @param n         шаг
     */
    public static void printFunctionTableWithForCycle(Function<Double, Double> func,
                                                      double a, double b, double n) {
        for (double x = a; x <= b; x += n) {
            System.out.printf("F(%s) = %s%n", x, func.apply(x));
        }
    }
}
