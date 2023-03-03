package ru.bstu.it191.chernih.lab3.entity;

import java.util.Scanner;

public class GasStove extends Device {

    @Override
    public void init(Scanner scanner) {
        System.out.println("Firm: ");
        this.firm = scanner.nextLine();
        System.out.println("Cost: ");
        this.cost = scanner.nextLine();
    }

    @Override
    public String getCost() {
        return this.cost;
    }
}
