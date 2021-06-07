package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;

import pojos.Cargos;
import pojos.Empleados;

public class XML {
	
	public static void marshallingEmpleados() throws JAXBException{
		
		Empleados empleado = new Empleados(1,"Alejandro",1000,1);
		JAXBContext jaxbC = JAXBContext.newInstance(Empleados.class);
		Marshaller jaxbM = jaxbC.createMarshaller();
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		//no añado el dtd porque sino no funcionaria el unmarshall de empleados
		File XMLfile = new File("./xml/Empleado.xml");
		jaxbM.marshal(empleado, XMLfile);
		jaxbM.marshal(empleado, System.out);
	}
	
	public static void unmarshallingEmpleados() throws JAXBException {
		JAXBContext jaxbC = JAXBContext.newInstance(Empleados.class);
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		File XMLfile2 = new File("./xml/Empleado.xml");
		Empleados empleado2 = (Empleados)jaxbU.unmarshal(XMLfile2);
		System.out.println(empleado2);
	}
	
	public static void marshallingCargos() throws JAXBException{
		Cargos cargo = new Cargos(1,"Informatico",1);
		JAXBContext jaxbC = JAXBContext.newInstance(Cargos.class);
		Marshaller jaxbM = jaxbC.createMarshaller();
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE cargo SYSTEM \"cargo.dtd\">"); 
		//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		//si añado el dtd el unmarshalling deja de funcionar
		File XMLfile = new File("./xml/Cargo.xml");
		jaxbM.marshal(cargo, XMLfile);
		jaxbM.marshal(cargo, System.out);
	}
	
public static void unmarshallingCargos() throws JAXBException {
		JAXBContext jaxbC = JAXBContext.newInstance(Cargos.class);
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		File XMLfile2 = new File("./xml/Cargo.xml");
		Cargos cargo2 = (Cargos)jaxbU.unmarshal(XMLfile2);
		System.out.println(cargo2);
	}

}
