package ru.bstu.it191.chernih.lab4.regex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    private static final String FILENAME = "htmltags.txt";

    private static void consoleUI() {
        try {
            var fileScanner = new Scanner(new FileReader(FILENAME));
            var htmlParser = new HTMLParser();
            while (fileScanner.hasNextLine()) {
                var stringToWork = fileScanner.nextLine();
                if (!stringToWork.isEmpty()) {
                    System.out.println(htmlParser.parse(stringToWork));
                }
            }
        } catch (RuntimeException | FileNotFoundException e) {
            System.out.println("Corrupted data");
        }
    }

    public static void main(String[] args) {
        consoleUI();
    }
}
