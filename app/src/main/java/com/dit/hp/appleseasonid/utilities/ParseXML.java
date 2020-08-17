package com.dit.hp.appleseasonid.utilities;

import com.dit.hp.appleseasonid.Modal.VehicleDetailsObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    public static VehicleDetailsObject parseXml(String dataServer) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(dataServer)));

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VehicleDetails");
        List<VehicleDetailsObject> vehicleDetails = new ArrayList<>();

        //loop all available student nodes
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                VehicleDetailsObject data = new VehicleDetailsObject();
                data.setRcChassisNo(elem.getElementsByTagName("rc_chasi_no").item(0).getTextContent());
                data.setRcEngineNumber(elem.getElementsByTagName("rc_eng_no").item(0).getTextContent());
                data.setRcFitUpto(elem.getElementsByTagName("rc_fit_upto").item(0).getTextContent());
                data.setRcRegisteredAt(elem.getElementsByTagName("rc_registered_at").item(0).getTextContent());
                data.setRcStatus(elem.getElementsByTagName("rc_status").item(0).getTextContent());
                data.setRcRegistrationNo(elem.getElementsByTagName("rc_regn_no").item(0).getTextContent());
                data.setRcStatusAsOn(elem.getElementsByTagName("rc_status_as_on").item(0).getTextContent());
                data.setRcOwnerName(elem.getElementsByTagName("rc_owner_name").item(0).getTextContent());
                System.out.println(data.toString());

                vehicleDetails.add(data);
            }
        }
        return vehicleDetails.get(0);
    }
}
