package xml;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLT {
	
	public static void xsltEmpleado() {
		
		String sourcePath = "./xml/Empleado.xml";
		String xsltPath = "./xml/Empleado.xslt";
		String resultDir = "./xml/Empleado.html";
	
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void xsltCargo() {
		
		String sourcePath = "./xml/Cargo.xml";
		String xsltPath = "./xml/Cargo.xslt";
		String resultDir = "./xml/Cargo.html";
	
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

