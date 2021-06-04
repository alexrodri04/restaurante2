package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "empleado")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "nombre", "salario", "cargoId" })
public class Empleados  implements Serializable{
	
	private static final long serialVersionUID = 8348912392169638115L;
	@XmlAttribute
	private int id;
	@XmlElement
	private String nombre;
	@XmlElement
	private int salario;
	@XmlElement
	private int cargoId;
	
	public Empleados() {
		super();
	}
	public Empleados(String nombre, int salario, int cargoId) {
		super();
		this.nombre=nombre;
		this.salario=salario;
		this.cargoId=cargoId;
				
	}
	
	public Empleados(int id, String nombre, int salario, int cargoId) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.salario=salario;
		this.cargoId=cargoId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public int getSalario() {
		return salario;		
	}
	public void setSalario(int salario) {
			this.salario = salario;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + id;
	        return result;
	    }
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Empleados other = (Empleados) obj;
	        if (id != other.id)
	            return false;
	        return true;
	    }
	public String toString() {
		String string ="ID: "+ getId()+ "  Nombre: "+ getNombre()+   "   Sueldo: "+getSalario() + " Cargo_id: " + getCargoId();
		return string; 
	}
	
}
