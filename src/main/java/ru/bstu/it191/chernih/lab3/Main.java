package ru.bstu.it191.chernih.lab3;

import ru.bstu.it191.chernih.lab3.entity.Device;
import ru.bstu.it191.chernih.lab3.entity.GasStove;
import ru.bstu.it191.chernih.lab3.entity.Microwave;
import ru.bstu.it191.chernih.lab3.entity.Washer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static int getFirstNumFromString(String string) {
        Matcher matcher = Pattern.compile("\\d+").matcher(string);
        return matcher.find() ? Integer.parseInt(matcher.group()) : -1;
    }


    private static void consoleUI() {
        try {
            var scanner = new Scanner(System.in);
            System.out.print("Enter num: ");
            var num = getFirstNumFromString(scanner.nextLine());
            if (num < 0) {
                throw new IllegalArgumentException();
            }
            List<Device> devices = new ArrayList<>(num);
            for (int i = 0; i < num; i++) {
                System.out.print("Enter entity type: ");
                Device device = null;
                switch (scanner.nextLine()) {
                    case "Gas stove": {
                        device = new GasStove();
                        break;
                    }
                    case "Washer": {
                        device = new Washer();
                        break;
                    }
                    case "Microwave": {
                        device = new Microwave();
                        break;
                    }
                    default: {
                        System.out.println("Unknown type >_<");
                    }
                }
                if (device != null) {
                    devices.add(device);
                }
            }
            var mostExpensiveDevice = devices
                    .stream()
                    .max(Comparator.comparing(Device::getCost))
                    .orElseThrow(NoSuchElementException::new);
            System.out.println("Most expensive device: " + mostExpensiveDevice);
        } catch (RuntimeException e) {
            System.out.println("Corrupted data");
        }
    }


    public static void main(String[] args) {
        consoleUI();
    }

}
