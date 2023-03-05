package ru.bstu.it191.chernih.lab5;

import ru.bstu.it191.chernih.lab5.db.PostgresJDBC;
import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;
import ru.bstu.it191.chernih.lab5.repository.DataProvider;
import ru.bstu.it191.chernih.lab5.xml.XMLDataProvider;

import java.util.Scanner;

public class Main {

    public static void consoleUI() {
        System.out.println("1 - xml\n2 - database");
        var scanner = new Scanner(System.in);
        try {
            DataProvider dataProvider;
            var mode = scanner.nextInt();
            if (mode == 1)
                dataProvider = new XMLDataProvider();
            else if (mode == 2) {
                dataProvider = new PostgresJDBC();
            }
            else throw new IllegalArgumentException();
            System.out.println("1 - delete by id\n2 - find by id\n3 - update record\n4 - insert new record");
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Enter id: ");
                    var id = scanner.nextLong();
                    dataProvider.deleteById(id);
                    System.out.println("Record was deleted");
                    break;
                }
                case 2: {
                    System.out.println("Enter id: ");
                    var id = scanner.nextLong();
                    System.out.println(dataProvider.findById(id));
                    break;
                }
                case 3: {
                    System.out.println("Enter record id to update: ");
                    var id = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Enter column to change: ");
                    var column = scanner.nextLine();
                    System.out.println("Enter new value: ");
                    var newValue = scanner.nextLine();

                    dataProvider.update(id, column, newValue);
                    System.out.println("Record was updated");
                    break;
                }
                case 4: {
                    System.out.println("Enter new record: ");

                    System.out.println("Id: ");
                    var id = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Brand: ");
                    var brand = scanner.nextLine();

                    System.out.println("Model: ");
                    var model = scanner.nextLine();

                    System.out.println("Color: ");
                    var color = scanner.nextLine();

                    System.out.println("Government Number: ");
                    var govNumber = scanner.nextLine();

                    System.out.println("Owner First Name: ");
                    var firstName = scanner.nextLine();

                    System.out.println("Owner Second Name: ");
                    var secondName = scanner.nextLine();

                    System.out.println("Owner Surname: ");
                    var surname = scanner.nextLine();

                    dataProvider.insert(Vehicle.builder()
                            .id(id)
                            .brand(brand)
                            .color(color)
                            .model(model)
                            .governmentNumber(govNumber)
                            .ownerFirstName(firstName)
                            .ownerSecondName(secondName)
                            .ownerSurname(surname)
                            .build()
                    );
                    System.out.println("Record was added");
                    break;
                }
                default: throw new IllegalArgumentException();
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        consoleUI();
    }

}
