package ru.bstu.it191.chernih.lab3.entity;

import java.util.Scanner;

public abstract class Device {

    protected String firm;

    protected String cost;

    public abstract void init(Scanner scanner);

    public abstract String getCost();

}
