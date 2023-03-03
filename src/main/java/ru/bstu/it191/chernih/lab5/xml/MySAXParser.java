package ru.bstu.it191.chernih.lab5.xml;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class MySAXParser {

    @Getter
    private static List<Vehicle> vehicles;

    public static void read() {
        String filepath = "lab5Data.xml";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            var myHandler = new MyHandler();
            saxParser.parse(filepath, myHandler);
            vehicles = myHandler.getVehicles();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
