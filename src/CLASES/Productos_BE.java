package CLASES;

public class Productos_BE {
	
	int cod, cant;
	String nombre;
	double price;
	
	public Productos_BE(int cod, int cant, String nombre, double price) {
		this.cod = cod;
		this.cant = cant;
		this.nombre = nombre;
		this.price = price;
	}

	public int getCod() {
		return cod;
	}

	public int getCant() {
		return cant;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrice() {
		return price;
	} 
	
	
}
	
	

