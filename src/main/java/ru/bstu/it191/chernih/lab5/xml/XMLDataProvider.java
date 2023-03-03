package ru.bstu.it191.chernih.lab5.xml;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.bstu.it191.chernih.lab5.db.entity.Vehicle;
import ru.bstu.it191.chernih.lab5.repository.DataProvider;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class XMLDataProvider implements DataProvider {

    private final static String FILENAME = "lab5Data.xml";
    private final static String FORMAT_XSLT = "src/main/resources/vehicles-format.xslt";

    @Override
    public Vehicle findById(Long id) {
        MySAXParser.read();
        for (Vehicle v : MySAXParser.getVehicles()) {
            if (Objects.equals(v.getId(), id)) {
                return v;
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void insert(Vehicle vehicle) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(FILENAME);
        Element root = document.getDocumentElement();

        var nodes = document.getElementsByTagName("VehicleList");

        Element newVehicle = document.createElement("Vehicle");

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(String.valueOf(vehicle.getId())));
        newVehicle.appendChild(id);

        Element brand = document.createElement("brand");
        brand.appendChild(document.createTextNode(vehicle.getBrand()));
        newVehicle.appendChild(brand);

        Element model = document.createElement("model");
        model.appendChild(document.createTextNode(vehicle.getModel()));
        newVehicle.appendChild(model);

        Element color = document.createElement("color");
        color.appendChild(document.createTextNode(vehicle.getBrand()));
        newVehicle.appendChild(color);

        Element governmentNumber = document.createElement("governmentNumber");
        governmentNumber.appendChild(document.createTextNode(vehicle.getGovernmentNumber()));
        newVehicle.appendChild(governmentNumber);

        Element ownerFirstName = document.createElement("ownerFirstName");
        ownerFirstName.appendChild(document.createTextNode(vehicle.getOwnerFirstName()));
        newVehicle.appendChild(ownerFirstName);

        Element ownerSecondName = document.createElement("ownerSecondName");
        ownerSecondName.appendChild(document.createTextNode(vehicle.getOwnerSecondName()));
        newVehicle.appendChild(ownerSecondName);

        Element ownerSurname = document.createElement("ownerSurname");
        brand.appendChild(document.createTextNode(vehicle.getOwnerSurname()));
        newVehicle.appendChild(ownerSurname);

        var node = nodes.item(0);
        node.appendChild(newVehicle);


        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(FILENAME);
        transformer.transform(source, result);
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(FILENAME);

        NodeList nodes = document.getElementsByTagName("Vehicle");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element person = (Element) nodes.item(i);
            Element name = (Element)person.getElementsByTagName("id").item(0);
            String pName = name.getTextContent();
            if (pName.equals(String.valueOf(id))) {
                person.getParentNode().removeChild(person);
            }
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(FILENAME);
        transformer.transform(source, result);
    }

    @Override
    @SneakyThrows
    public void update(Long id, String column, String newValue) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList listOfStaff = doc.getElementsByTagName("Vehicle");

            for (int i = 0; i < listOfStaff.getLength(); i++) {
                Node staff = listOfStaff.item(i);
                if (staff.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodes = staff.getChildNodes();
                    boolean flag = false;
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                            if (item.getNodeName().equalsIgnoreCase("id")
                                    && item.getTextContent().equals(String.valueOf(id))) {
                                flag = true;
                            }
                        }
                        if (flag && column.equalsIgnoreCase(item.getNodeName())) {
                            item.setTextContent(newValue);
                            flag = false;
                        }
                    }
                }
            }

            try (FileOutputStream output = new FileOutputStream(FILENAME)) {
                writeXml(doc, output);
            }
        } catch (ParserConfigurationException | SAXException
                 | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void writeXml(Document doc, OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(FORMAT_XSLT)));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
