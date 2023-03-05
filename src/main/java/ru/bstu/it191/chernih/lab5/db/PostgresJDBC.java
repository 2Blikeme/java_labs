package ru.bstu.it191.chernih.lab5.db;

import lombok.SneakyThrows;
import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;
import ru.bstu.it191.chernih.lab5.repository.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PostgresJDBC implements DataProvider {

    private static final String DB_DRIVER;
    private static final String DB_CONNECTION;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
            prop.load(fis);

            // взятие свойства и преобразование в необходимую кодировку
            DB_DRIVER = new String(prop.getProperty("database_driver").getBytes("ISO8859-1"));
            DB_CONNECTION = new String(prop.getProperty("database_url").getBytes("ISO8859-1"));
            DB_USER = new String(prop.getProperty("database_user").getBytes("ISO8859-1"));
            DB_PASSWORD = new String(prop.getProperty("database_password").getBytes("ISO8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @SneakyThrows
    public Vehicle findById(Long id) {
        var selectTableSQL = "select * from vehicles.public.vehicle where id = " + id;

        Statement statement;
        try(Connection dbConnection = getDBConnection()) {
            assert dbConnection != null;
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);
            rs.next();

            // И если что то было получено то цикл while сработает
            var vehicleId = rs.getLong("id");
            var vehicleBrand = rs.getString("brand");
            var vehicleModel = rs.getString("model");
            var vehicleColor = rs.getString("color");
            var vehicleGovernmentNumber = rs.getString("governmentnumber");
            var vehicleOwnerFirstName = rs.getString("ownerfirstname");
            var vehicleOwnerSecondName = rs.getString("ownersecondname");
            var vehicleOwnerSurnameName = rs.getString("ownersurname");

            return Vehicle.builder()
                    .id(vehicleId)
                    .brand(vehicleBrand)
                    .model(vehicleModel)
                    .color(vehicleColor)
                    .governmentNumber(vehicleGovernmentNumber)
                    .ownerFirstName(vehicleOwnerFirstName)
                    .ownerSecondName(vehicleOwnerSecondName)
                    .ownerSurname(vehicleOwnerSurnameName)
                    .build();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void insert(Vehicle vehicle) {
        var insertTableSQL = "insert into " +
                "vehicles.public.vehicle(id, brand, model, color, " +
                "governmentnumber, ownerfirstname, ownersecondname, ownersurname) VALUES (" +
                "" + vehicle.getId() + ", '" + vehicle.getBrand() + "', '" + vehicle.getModel() +
                "', '" + vehicle.getColor() + "', '" + vehicle.getGovernmentNumber() + "', '" + vehicle.getOwnerFirstName() +
                "', '" + vehicle.getOwnerSecondName() + "', '" + vehicle.getOwnerSurname() + "')";

        Statement statement;
        try(Connection dbConnection = getDBConnection()) {
            assert dbConnection != null;
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(insertTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        String deleteTableSQL = "delete from vehicles.public.vehicle v where v.id = " + id;

        try (Connection dbConnection = getDBConnection()) {
            assert dbConnection != null;
            try (Statement statement = dbConnection.createStatement()) {
                // выполняем запрос delete SQL
                statement.execute(deleteTableSQL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    @SneakyThrows
    public void update(Long id, String column, String newValue) {
        var updateTableSQL = "update vehicles.public.vehicle set " + column + " = '" + newValue + "' where id = " + id;

        try(Connection dbConnection = getDBConnection()) {
            assert dbConnection != null;
            var statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
