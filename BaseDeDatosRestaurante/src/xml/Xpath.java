package xml;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Xpath {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    	//Construir el DOM
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	factory.setNamespaceAware(true);
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document doc = builder.parse("./xml/Empleado.xml");
    	XPathFactory xpathfactory = XPathFactory.newInstance();
    	XPath xpath = xpathfactory.newXPath();
    	String xPathtxt = "//Empleado[sueldo>500]";
    	XPathExpression expr = xpath.compile(xPathtxt); 
    	Object result = expr.evaluate(doc, XPathConstants.NODESET);
    	NodeList nodes = (NodeList) result; 
    	for (int i = 0; i < nodes.getLength(); i++) {
    	    System.out.println(processNode(nodes.item(i)));
    	}

    }
    
    public static String processNode(Node node) {
        String data = "";
        if(node.hasChildNodes()) {
            data += node.getNodeName() + ": ";
            NodeList nodes = node.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                data += processNode(nodes.item(i));}
        } else {
            data += node.getNodeValue();}
        return data;
    }

}
