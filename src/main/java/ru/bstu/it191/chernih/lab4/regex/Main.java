package ru.bstu.it191.chernih.lab4.regex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final String LINK = "";

    private static String readData(Scanner scanner) {
        var builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        return builder.toString();
    }

    private static void consoleUI() {
        try {
            var fileScanner = new Scanner(new FileReader(LINK));
            while (fileScanner.hasNextLine()) {

            }
        } catch (RuntimeException | FileNotFoundException e) {
            System.out.println("Corrupted data");
        }
    }

    public static void main(String[] args) {

    }

}
