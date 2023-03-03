package ru.bstu.it191.chernih.lab5.repository;

import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;

public interface DataProvider {

    Vehicle findById(Long id);

    void insert(Vehicle vehicle);

    void deleteById(Long id);

    void update(Long id, String column, String newValue);
}
