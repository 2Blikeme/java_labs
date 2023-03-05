package ru.bstu.it191.chernih.lab3.entity;

import lombok.ToString;

public class Microwave extends ElectronicDevice {

    @Override
    public String toString() {
        return "Microwave{" +
                "powerConsumption=" + powerConsumption +
                ", firm='" + firm + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
