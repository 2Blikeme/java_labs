package ru.bstu.it191.chernih.lab1andlab2.conditional;

public class NumberUtils {

    /**
     * Возвращает строковое представления передаваемой цифры на английском языке
     */
    public static String stringValue(int num) {
        switch (num) {
            case 0: return "zero";
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
            default: return null;
        }
    }
}
