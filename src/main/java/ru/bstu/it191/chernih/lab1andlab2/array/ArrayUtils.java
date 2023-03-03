package ru.bstu.it191.chernih.lab1andlab2.array;

import java.util.Arrays;

public class ArrayUtils {


    /**
     * Класс для хранения массивов
     */
    private static class TwoArrays {
        public int[] positives;
        public int[] negatives;

        @Override
        public String toString() {
            return "positives=" + Arrays.toString(positives) +
                    ", negatives=" + Arrays.toString(negatives) + '}';
        }
    }


    /**
     * Возвращает {@link TwoArrays} в котором
     * positives - элементы передаваемого массива с четными номерами
     * negatives - элементы передаваемого массива с нечетными номерами
     */
    public static TwoArrays filterPositivesNegatives(int[] arr) {
        var result = new TwoArrays();

        var positivesArrayLength = arr.length / 2;
        var negativesArrayLength = arr.length / 2;
        if (arr.length % 2 != 0) {
            positivesArrayLength++;
        }

        result.positives = new int[positivesArrayLength];
        result.negatives = new int[negativesArrayLength];

        var pos = 0;
        var neg = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                result.positives[pos] = arr[i];
                pos++;
            } else {
                result.negatives[neg] = arr[i];
                neg++;
            }
        }

        return result;
    }
}
