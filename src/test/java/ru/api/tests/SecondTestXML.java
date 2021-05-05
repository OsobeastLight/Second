package ru.api.tests;

import com.jayway.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SecondTestXML {
    @Test

    public void getXml() throws IOException, ParserConfigurationException, org.xml.sax.SAXException {
        String xml = RestAssured.get("https://status.encoding.com/status.php?format=xml").asString();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        ByteArrayInputStream input =  new ByteArrayInputStream(
                xml.getBytes(StandardCharsets.UTF_8));
        Document doc = dBuilder.parse(input);
        Element root = doc.getDocumentElement();
        Element message = (Element) root.getElementsByTagName("status").item(0);
        String textContent = message.getTextContent();
        Element lastYear = (Element) root.getElementsByTagName("lastYear").item(0);
        int textContentLastYear = Integer.parseInt(lastYear.getTextContent());
        Element uptime = (Element) root.getElementsByTagName("uptime").item(0);
        int textContentUptime = Integer.parseInt(uptime.getTextContent());

        Assert.assertEquals(textContent, "Ok");
        Assert.assertTrue(textContentLastYear<10);
        Assert.assertTrue(textContentUptime>86400);

    }
}




