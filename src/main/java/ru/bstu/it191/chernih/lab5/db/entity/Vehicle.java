package ru.bstu.it191.chernih.lab5.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Long id;

    private String brand;

    private String model;

    private String color;

    private String governmentNumber;

    private String ownerFirstName;

    private String ownerSecondName;

    private String ownerSurname;

}
