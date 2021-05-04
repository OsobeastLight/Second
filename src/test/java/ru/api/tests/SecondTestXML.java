package ru.api.tests;

import com.jayway.restassured.RestAssured;
import jdk.internal.org.xml.sax.SAXException;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SecondTestXML {

    public void getJson() throws IOException, ParserConfigurationException, SAXException {
        String xml = RestAssured.get("https://status.encoding.com/status.php?format=xml").asString();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xml);
        ByteArrayInputStream input =  new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = dBuilder.parse(input);
        Element root = doc.getDocumentElement();
        // для простоты сразу берем message
        Element message = (Element) root.getElementsByTagName("status").item(0);
        String textContent = message.getTextContent();
        System.out.println(textContent);
        Element lastYear = (Element) root.getElementsByTagName("lastYear").item(0);
        String textContentlastYear = lastYear.getTextContent();
        System.out.println(textContentlastYear);
        Element uptime = (Element) root.getElementsByTagName("uptime").item(0);

        String textContentlastYearuptime = uptime.getTextContent();
        System.out.println(textContentlastYearuptime);
        Assert.assertTrue(status == "ok");

    }
}




