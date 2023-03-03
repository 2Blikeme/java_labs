package ru.bstu.it191.chernih.lab3.entity;

import java.util.Scanner;

public class ElectronicDevice extends Device {

    protected double powerConsumption;

    @Override
    public void init(Scanner scanner) {
        System.out.println("Firm: ");
        this.firm = scanner.nextLine();
        System.out.println("Power Consumption: ");
        this.powerConsumption = scanner.nextDouble();
        System.out.println("Cost: ");
        this.cost = scanner.nextLine();
    }

    @Override
    public String getCost() {
    return this.cost;
    }
}
