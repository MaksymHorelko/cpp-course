package laba3.task1;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//DOM
public class XMLParser {
    public static List<DataPoint> parseXML(String fileName) {
        List<DataPoint> dataPoints = new ArrayList<>();
        try {
            File file = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("point");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    double x = Double.parseDouble(element.getElementsByTagName("x").item(0).getTextContent());
                    double y = Double.parseDouble(element.getElementsByTagName("y").item(0).getTextContent());
                    Date date = dateFormat.parse(element.getElementsByTagName("date").item(0).getTextContent());
                    dataPoints.add(new DataPoint(x, y, date));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }

    
}
