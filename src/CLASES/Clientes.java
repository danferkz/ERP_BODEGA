package CLASES;

public class Clientes implements Entidades {
	String nombre;
	int dni;
		
	public String getname() {
		return nombre;
	}

	public void setname(String nombre) {
		this.nombre = nombre;
	}
	
	public int getID() {
		return dni;
	}
	
	public void setID(int dni) {
		this.dni = dni;
	}
}