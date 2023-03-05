package ru.bstu.it191.chernih.lab3.entity;

public class Washer extends ElectronicDevice {

    @Override
    public String toString() {
        return "Washer{" +
                "powerConsumption=" + powerConsumption +
                ", firm='" + firm + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
