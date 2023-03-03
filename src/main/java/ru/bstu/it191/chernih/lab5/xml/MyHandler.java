package ru.bstu.it191.chernih.lab5.xml;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    @Getter
    private List<Vehicle> vehicles = null;
    private Vehicle vehicle;
    private StringBuilder data;

    private boolean bID = false;
    private boolean bBrand = false;
    private boolean bModel = false;
    private boolean bColor = false;
    private boolean bGover = false;
    private boolean bFirstName = false;
    private boolean bSecondName = false;
    private boolean bSurname = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("Vehicle")) {
            vehicle = new Vehicle();
            if (vehicles == null)
                vehicles = new ArrayList<>();
        }

        else if (qName.equalsIgnoreCase("id"))
            bID = true;
        else if (qName.equalsIgnoreCase("brand"))
            bBrand = true;
        else if (qName.equalsIgnoreCase("model"))
            bModel = true;
        else if (qName.equalsIgnoreCase("color"))
            bColor = true;
        else if (qName.equalsIgnoreCase("governmentNumber"))
            bGover = true;
        else if (qName.equalsIgnoreCase("ownerFirstName"))
            bFirstName = true;
        else if (qName.equalsIgnoreCase("ownerSecondName"))
            bSecondName = true;
        else if (qName.equalsIgnoreCase("ownerSurname"))
            bSurname = true;

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bID) {
            vehicle.setId(Long.parseLong(String.valueOf(data)));
            bID = false;
        }
        else if (bBrand) {
            vehicle.setBrand(String.valueOf(data));
            bBrand = false;
        }
        else if (bModel) {
            vehicle.setModel(String.valueOf(data));
            bModel = false;
        }
        else if (bColor) {
            vehicle.setColor(String.valueOf(data));
            bColor = false;
        }
        else if (bGover) {
            vehicle.setGovernmentNumber(String.valueOf(data));
            bGover = false;
        }
        else if (bFirstName) {
            vehicle.setOwnerFirstName(String.valueOf(data));
            bFirstName = false;
        }
        else if (bSecondName) {
            vehicle.setOwnerSecondName(String.valueOf(data));
            bSecondName = false;
        }
        else if (bSurname) {
            vehicle.setOwnerSurname(String.valueOf(data));
            bSurname = false;
        }

        if (qName.equalsIgnoreCase("Vehicle")) {
            vehicles.add(vehicle);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}
